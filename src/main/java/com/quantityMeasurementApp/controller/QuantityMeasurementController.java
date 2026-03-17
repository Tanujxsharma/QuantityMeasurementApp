package com.quantityMeasurementApp.controller;

import com.quantityMeasurementApp.entity.QuantityDTO;
import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.quantityMeasurementApp.service.IQuantityMeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementController {

    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementController.class);
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
        logger.info("QuantityMeasurementController initialized.");
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity e = service.add(q1, q2);
        logger.info("Addition: {}", e);
    }

    public void performSubtraction(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity e = service.subtract(q1, q2);
        logger.info("Subtraction: {}", e);
    }

    public void performDivision(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity e = service.divide(q1, q2);
        logger.info("Division: {}", e);
    }

    public void performConversion(QuantityDTO q, String targetUnit) {
        QuantityMeasurementEntity e = service.convert(q, targetUnit);
        logger.info("Conversion: {}", e);
    }

    public void performComparison(QuantityDTO q1, QuantityDTO q2) {
        QuantityMeasurementEntity e = service.compare(q1, q2);
        logger.info("Comparison: {}", e);
    }
}