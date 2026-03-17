package com.quantityMeasurementApp.repository;

import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.quantityMeasurementApp.exception.DatabaseException;
import com.quantityMeasurementApp.util.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementDatabaseRepository.class);
    private final ConnectionPool connectionPool;

    public QuantityMeasurementDatabaseRepository() {
        this.connectionPool = ConnectionPool.getInstance();
        logger.info("QuantityMeasurementDatabaseRepository initialized.");
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO quantity_measurement_entity (operation, result, measurement_type, is_error, created_at) VALUES (?, ?, ?, ?, ?)";
        Connection conn = connectionPool.acquireConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getOperation());
            stmt.setString(2, entity.getResult());
            stmt.setString(3, entity.getMeasurementType());
            stmt.setBoolean(4, entity.hasError());
            stmt.setTimestamp(5, Timestamp.valueOf(entity.getCreatedAt()));
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) entity.setId(keys.getLong(1));
            logger.info("Saved entity to DB: {}", entity);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to save entity: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        String sql = "SELECT * FROM quantity_measurement_entity ORDER BY created_at DESC";
        Connection conn = connectionPool.acquireConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            return mapResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve all: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findByOperation(String operation) {
        String sql = "SELECT * FROM quantity_measurement_entity WHERE operation = ? ORDER BY created_at DESC";
        Connection conn = connectionPool.acquireConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, operation);
            return mapResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseException("Failed to query by operation: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findByMeasurementType(String measurementType) {
        String sql = "SELECT * FROM quantity_measurement_entity WHERE measurement_type = ? ORDER BY created_at DESC";
        Connection conn = connectionPool.acquireConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, measurementType);
            return mapResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseException("Failed to query by type: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM quantity_measurement_entity";
        Connection conn = connectionPool.acquireConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get count: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM quantity_measurement_entity";
        Connection conn = connectionPool.acquireConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            logger.info("All entities deleted from DB.");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete all: " + e.getMessage(), e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    @Override
    public String getPoolStatistics() {
        return connectionPool.getStatistics();
    }

    @Override
    public void releaseResources() {
        connectionPool.closeAllConnections();
        logger.info("Database resources released.");
    }

    private List<QuantityMeasurementEntity> mapResultSet(ResultSet rs) throws SQLException {
        List<QuantityMeasurementEntity> list = new ArrayList<>();
        while (rs.next()) {
            String operation = rs.getString("operation");
            String result = rs.getString("result");
            String measurementType = rs.getString("measurement_type");
            boolean isError = rs.getBoolean("is_error");
            QuantityMeasurementEntity entity = isError
                    ? new QuantityMeasurementEntity(result)
                    : new QuantityMeasurementEntity(operation, result, measurementType);
            entity.setId(rs.getLong("id"));
            list.add(entity);
        }
        return list;
    }
}