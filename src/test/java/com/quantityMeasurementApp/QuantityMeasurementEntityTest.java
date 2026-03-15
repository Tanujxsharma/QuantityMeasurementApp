package com.quantityMeasurementApp;

import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementEntityTest {

    @Test
    void testSuccessEntity(){

        QuantityMeasurementEntity e =
                new QuantityMeasurementEntity("ADD","Quantity(2,FEET)");

        assertFalse(e.hasError());
    }

    @Test
    void testErrorEntity(){

        QuantityMeasurementEntity e =
                new QuantityMeasurementEntity("Invalid");

        assertTrue(e.hasError());
    }
}