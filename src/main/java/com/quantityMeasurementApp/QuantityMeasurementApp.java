package com.quantityMeasurementApp;

import com.quantityMeasurementApp.controller.QuantityMeasurementController;
import com.quantityMeasurementApp.entity.QuantityDTO;
import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.quantityMeasurementApp.repository.IQuantityMeasurementRepository;
import com.quantityMeasurementApp.repository.QuantityMeasurementCacheRepository;
import com.quantityMeasurementApp.repository.QuantityMeasurementDatabaseRepository;
import com.quantityMeasurementApp.service.QuantityMeasurementServiceImpl;
import com.quantityMeasurementApp.util.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QuantityMeasurementApp {

    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementApp.class);

    private final IQuantityMeasurementRepository repo;
    private final QuantityMeasurementController controller;

    public QuantityMeasurementApp() {
        ApplicationConfig config = ApplicationConfig.getInstance();
        String repoType = config.getRepositoryType();

        if ("database".equalsIgnoreCase(repoType)) {
            this.repo = new QuantityMeasurementDatabaseRepository();
            logger.info("Using DatabaseRepository.");
        } else {
            this.repo = QuantityMeasurementCacheRepository.getInstance();
            logger.info("Using CacheRepository.");
        }

        QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repo);
        this.controller = new QuantityMeasurementController(service);
    }

    public void closeResources() {
        repo.releaseResources();
        logger.info("Resources released.");
    }

    public void deleteAllMeasurements() {
        repo.deleteAll();
        logger.info("All measurements deleted.");
    }

    public static void main(String[] args) {
        QuantityMeasurementApp app = new QuantityMeasurementApp();

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCH", "LENGTH");

        app.controller.performAddition(q1, q2);
        app.controller.performSubtraction(q1, q2);
        app.controller.performDivision(q1, q2);
        app.controller.performConversion(q1, "INCH");
        app.controller.performComparison(q1, q2);

        List<QuantityMeasurementEntity> all = app.repo.findAll();
        logger.info("Total measurements stored: {}", all.size());
        all.forEach(e -> logger.info(" -> {}", e));
        logger.info("Pool stats: {}", app.repo.getPoolStatistics());

        app.deleteAllMeasurements();
        app.closeResources();
    }
}