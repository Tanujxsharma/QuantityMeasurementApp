package com.quantityMeasurementApp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = length1.add(length2);

        assertEquals(3.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        QuantityLength length1 = new QuantityLength(6.0, LengthUnit.INCH);
        QuantityLength length2 = new QuantityLength(6.0, LengthUnit.INCH);

        QuantityLength result = length1.add(length2);

        assertEquals(12.0, result.getValue());
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = length1.add(length2);

        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        QuantityLength length1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength length2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = length1.add(length2);

        assertEquals(24.0, result.getValue());
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength length2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = length1.add(length2);

        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {

        QuantityLength length1 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength length2 = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result = length1.add(length2);

        assertEquals(5.08, result.getValue()); // cm precision
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testAddition_WithZero() {

        QuantityLength length1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result = length1.add(length2);

        assertEquals(5.0, result.getValue());
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityLength length1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = length1.add(length2);

        assertEquals(3.0, result.getValue());
    }

    @Test
    void testAddition_NullSecondOperand() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> length1.add(null));
    }

    @Test
    void testAddition_LargeValues() {

        QuantityLength length1 = new QuantityLength(1e6, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(1e6, LengthUnit.FEET);

        QuantityLength result = length1.add(length2);

        assertEquals(2e6, result.getValue());
    }

    @Test
    void testAddition_SmallValues() {

        QuantityLength length1 = new QuantityLength(0.001, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(0.002, LengthUnit.FEET);

        QuantityLength result = length1.add(length2);

        assertEquals(0.003, result.getValue());
    }

}
