package com.quantityMeasurementApp.util;

import com.quantityMeasurementApp.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;

    private final BlockingQueue<Connection> pool;
    private final List<Connection> allConnections = new ArrayList<>();
    private final ApplicationConfig config;
    private final int poolSize;

    private ConnectionPool() {
        this.config = ApplicationConfig.getInstance();
        this.poolSize = config.getPoolSize();
        this.pool = new ArrayBlockingQueue<>(poolSize);
        initializePool();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) instance = new ConnectionPool();
            }
        }
        return instance;
    }

    private void initializePool() {
        try {
            Class.forName(config.getDbDriver());
            for (int i = 0; i < poolSize; i++) {
                Connection conn = createConnection();
                pool.offer(conn);
                allConnections.add(conn);
            }
            logger.info("Connection pool initialized with {} connections.", poolSize);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseException("Failed to initialize pool: " + e.getMessage(), e);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(config.getDbUrl(), config.getDbUsername(), config.getDbPassword());
    }

    public Connection acquireConnection() {
        try {
            Connection conn = pool.poll(5, TimeUnit.SECONDS);
            if (conn == null) throw new DatabaseException("Connection pool exhausted.");
            if (!conn.isValid(2)) conn = createConnection();
            logger.debug("Connection acquired. Available: {}", pool.size());
            return conn;
        } catch (InterruptedException | SQLException e) {
            throw new DatabaseException("Failed to acquire connection: " + e.getMessage(), e);
        }
    }

    public void releaseConnection(Connection conn) {
        if (conn != null) {
            pool.offer(conn);
            logger.debug("Connection released. Available: {}", pool.size());
        }
    }

    public void closeAllConnections() {
        for (Connection conn : allConnections) {
            try {
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: {}", e.getMessage());
            }
        }
        pool.clear();
        logger.info("All connections closed.");
    }

    public String getStatistics() {
        return String.format("Pool size: %d | Available: %d | In use: %d",
                poolSize, pool.size(), poolSize - pool.size());
    }
}