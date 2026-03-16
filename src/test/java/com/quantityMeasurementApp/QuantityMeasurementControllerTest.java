package com.quantityMeasurementApp;

import com.quantityMeasurementApp.controller.QuantityMeasurementController;
import com.quantityMeasurementApp.entity.QuantityDTO;
import com.quantityMeasurementApp.repository.QuantityMeasurementCacheRepository;
import com.quantityMeasurementApp.service.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.Test;

class QuantityMeasurementControllerTest {

    @Test
    void testController_Addition(){

        QuantityMeasurementController controller =
                new QuantityMeasurementController(
                        new QuantityMeasurementServiceImpl(
                                QuantityMeasurementCacheRepository.getInstance()
                        )
                );

        QuantityDTO q1 =
                new QuantityDTO(1,"FEET","LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(12,"INCH","LENGTH");

        controller.performAddition(q1,q2);
    }
}