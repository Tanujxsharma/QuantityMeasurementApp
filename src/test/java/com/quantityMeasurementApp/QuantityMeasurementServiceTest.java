package com.quantityMeasurementApp;

import com.quantityMeasurementApp.entity.QuantityDTO;
import com.quantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.quantityMeasurementApp.repository.QuantityMeasurementCacheRepository;
import com.quantityMeasurementApp.service.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementServiceTest {

    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    void setup(){

        service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance()
                );
    }

    @Test
    void testAddition_Length(){

        QuantityDTO q1 =
                new QuantityDTO(1,"FEET","LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(12,"INCH","LENGTH");

        QuantityMeasurementEntity result =
                service.add(q1,q2);

        assertFalse(result.hasError());
    }

    @Test
    void testSubtraction_Length(){

        QuantityDTO q1 =
                new QuantityDTO(10,"FEET","LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(6,"INCH","LENGTH");

        QuantityMeasurementEntity result =
                service.subtract(q1,q2);

        assertFalse(result.hasError());
    }

    @Test
    void testDivision_Length(){

        QuantityDTO q1 =
                new QuantityDTO(10,"FEET","LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(2,"FEET","LENGTH");

        QuantityMeasurementEntity result =
                service.divide(q1,q2);

        assertFalse(result.hasError());
    }

    @Test
    void testDivision_ByZero(){

        QuantityDTO q1 =
                new QuantityDTO(10,"FEET","LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(0,"FEET","LENGTH");

        QuantityMeasurementEntity result =
                service.divide(q1,q2);

        assertTrue(result.hasError());
    }

    @Test
    void testCrossCategory_Add(){

        QuantityDTO length =
                new QuantityDTO(10,"FEET","LENGTH");

        QuantityDTO weight =
                new QuantityDTO(5,"KILOGRAM","WEIGHT");

        QuantityMeasurementEntity result =
                service.add(length,weight);

        assertTrue(result.hasError());
    }

    @Test
    void testConvert_Length(){

        QuantityDTO q =
                new QuantityDTO(1,"FEET","LENGTH");

        QuantityMeasurementEntity result =
                service.convert(q,"INCH");

        assertFalse(result.hasError());
    }

    @Test
    void testCompare_Equal(){

        QuantityDTO q1 =
                new QuantityDTO(1,"FEET","LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(12,"INCH","LENGTH");

        QuantityMeasurementEntity result =
                service.compare(q1,q2);

        assertFalse(result.hasError());
    }

    @Test
    void testTemperature_Add_NotSupported(){

        QuantityDTO t1 =
                new QuantityDTO(100,"CELSIUS","TEMPERATURE");

        QuantityDTO t2 =
                new QuantityDTO(50,"CELSIUS","TEMPERATURE");

        QuantityMeasurementEntity result =
                service.add(t1,t2);

        assertTrue(result.hasError());
    }

}