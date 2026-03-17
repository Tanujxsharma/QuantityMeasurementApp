package com.quantityMeasurementApp.service;

import com.quantityMeasurementApp.entity.QuantityDTO;
import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.quantityMeasurementApp.repository.IQuantityMeasurementRepository;
import com.quantityMeasurementApp.unit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);
    private final IQuantityMeasurementRepository repo;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repo = repo;
        logger.info("QuantityMeasurementServiceImpl initialized with: {}", repo.getClass().getSimpleName());
    }

    private IMeasurable resolveUnit(QuantityDTO dto) {
        switch (dto.getMeasurementType()) {
            case "LENGTH":      return LengthUnit.valueOf(dto.getUnit());
            case "WEIGHT":      return WeightUnit.valueOf(dto.getUnit());
            case "VOLUME":      return VolumeUnit.valueOf(dto.getUnit());
            case "TEMPERATURE": return TemperatureUnit.valueOf(dto.getUnit());
            default: throw new IllegalArgumentException("Invalid measurement type: " + dto.getMeasurementType());
        }
    }

    @Override
    public QuantityMeasurementEntity add(QuantityDTO d1, QuantityDTO d2) {
        try {
            IMeasurable u1 = resolveUnit(d1);
            IMeasurable u2 = resolveUnit(d2);
            u1.validOperationSupport("ADD");
            Quantity q1 = new Quantity(d1.getValue(), u1);
            Quantity q2 = new Quantity(d2.getValue(), u2);
            Quantity result = q1.add(q2);
            QuantityMeasurementEntity e = new QuantityMeasurementEntity("ADD", result.toString(), d1.getMeasurementType());
            repo.save(e);
            logger.info("ADD: {}", e);
            return e;
        } catch (Exception ex) {
            logger.error("ADD failed: {}", ex.getMessage());
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }

    @Override
    public QuantityMeasurementEntity subtract(QuantityDTO d1, QuantityDTO d2) {
        try {
            IMeasurable u1 = resolveUnit(d1);
            IMeasurable u2 = resolveUnit(d2);
            u1.validOperationSupport("SUBTRACT");
            Quantity q1 = new Quantity(d1.getValue(), u1);
            Quantity q2 = new Quantity(d2.getValue(), u2);
            Quantity result = q1.subtract(q2);
            QuantityMeasurementEntity e = new QuantityMeasurementEntity("SUBTRACT", result.toString(), d1.getMeasurementType());
            repo.save(e);
            logger.info("SUBTRACT: {}", e);
            return e;
        } catch (Exception ex) {
            logger.error("SUBTRACT failed: {}", ex.getMessage());
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }

    @Override
    public QuantityMeasurementEntity divide(QuantityDTO d1, QuantityDTO d2) {
        try {
            IMeasurable u1 = resolveUnit(d1);
            IMeasurable u2 = resolveUnit(d2);
            u1.validOperationSupport("DIVIDE");
            Quantity q1 = new Quantity(d1.getValue(), u1);
            Quantity q2 = new Quantity(d2.getValue(), u2);
            double result = q1.divide(q2);
            QuantityMeasurementEntity e = new QuantityMeasurementEntity("DIVIDE", String.valueOf(result), d1.getMeasurementType());
            repo.save(e);
            logger.info("DIVIDE: {}", e);
            return e;
        } catch (Exception ex) {
            logger.error("DIVIDE failed: {}", ex.getMessage());
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }

    @Override
    public QuantityMeasurementEntity convert(QuantityDTO d1, String targetUnitStr) {
        try {
            IMeasurable sourceUnit = resolveUnit(d1);
            IMeasurable targetUnit = resolveUnit(new QuantityDTO(0, targetUnitStr, d1.getMeasurementType()));
            Quantity q = new Quantity(d1.getValue(), sourceUnit);
            Quantity result = q.convertTo(targetUnit);
            QuantityMeasurementEntity e = new QuantityMeasurementEntity("CONVERT", result.toString(), d1.getMeasurementType());
            repo.save(e);
            logger.info("CONVERT: {}", e);
            return e;
        } catch (Exception ex) {
            logger.error("CONVERT failed: {}", ex.getMessage());
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }

    @Override
    public QuantityMeasurementEntity compare(QuantityDTO d1, QuantityDTO d2) {
        try {
            Quantity q1 = new Quantity(d1.getValue(), resolveUnit(d1));
            Quantity q2 = new Quantity(d2.getValue(), resolveUnit(d2));
            boolean res = q1.equals(q2);
            QuantityMeasurementEntity e = new QuantityMeasurementEntity("COMPARE", String.valueOf(res), d1.getMeasurementType());
            repo.save(e);
            logger.info("COMPARE: {}", e);
            return e;
        } catch (Exception ex) {
            logger.error("COMPARE failed: {}", ex.getMessage());
            return new QuantityMeasurementEntity(ex.getMessage());
        }
    }
}