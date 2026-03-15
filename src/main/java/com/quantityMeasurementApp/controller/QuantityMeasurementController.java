package com.quantityMeasurementApp.controller;

import com.quantityMeasurementApp.dto.QuantityDTO;
import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.quantityMeasurementApp.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service){
        this.service = service;
    }

    public void performAddition(QuantityDTO q1,QuantityDTO q2){
        QuantityMeasurementEntity e = service.add(q1,q2);
        System.out.println(e);
    }
}