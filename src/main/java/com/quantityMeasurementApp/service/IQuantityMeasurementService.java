package com.quantityMeasurementApp.service;

import com.quantityMeasurementApp.entity.QuantityDTO;
import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity add(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementEntity subtract(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementEntity divide(QuantityDTO q1, QuantityDTO q2);

    QuantityMeasurementEntity convert(QuantityDTO q1,String targetUnit);

    QuantityMeasurementEntity compare(QuantityDTO q1, QuantityDTO q2);
}