package com.quantityMeasurementApp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
    private static ApplicationConfig instance;
    private final Properties properties = new Properties();

    private ApplicationConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
                logger.info("application.properties loaded.");
            } else {
                logger.warn("application.properties not found, using defaults.");
            }
        } catch (IOException e) {
            logger.error("Failed to load properties: {}", e.getMessage());
        }
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) instance = new ApplicationConfig();
        return instance;
    }

    public String get(String key, String defaultValue) {
        String sysProp = System.getProperty(key);
        if (sysProp != null) return sysProp;
        return properties.getProperty(key, defaultValue);
    }

    public String getDbUrl() {
        return get("db.url", "jdbc:h2:mem:quantitydb;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:db/schema.sql'");
    }

    public String getDbUsername() { return get("db.username", "sa"); }
    public String getDbPassword() { return get("db.password", ""); }
    public String getDbDriver() { return get("db.driver", "org.h2.Driver"); }
    public int getPoolSize() { return Integer.parseInt(get("db.pool.size", "5")); }
    public String getRepositoryType() { return get("repository.type", "cache"); }
}