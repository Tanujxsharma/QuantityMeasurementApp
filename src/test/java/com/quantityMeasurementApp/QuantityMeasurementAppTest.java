package com.quantityMeasurementApp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.INCH);

        assertEquals(24.0, result.getValue());
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.YARD);

        assertEquals(0.666666, result.getValue());
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength length2 = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue());
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        QuantityLength length1 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength length2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.YARD);

        assertEquals(3.0, result.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        QuantityLength length1 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength length2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.FEET);

        assertEquals(9.0, result.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result1 =
                QuantityLength.add(length1, length2, LengthUnit.YARD);

        QuantityLength result2 =
                QuantityLength.add(length2, length1, LengthUnit.YARD);

        assertEquals(result1.getValue(), result2.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        QuantityLength length1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.YARD);

        assertEquals(1.666666, result.getValue()); // assuming rounding
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        QuantityLength length1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.INCH);

        assertEquals(36.0, result.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.add(length1, length2, null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        QuantityLength length1 = new QuantityLength(1000.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(500.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.INCH);

        assertEquals(18000.0, result.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        QuantityLength length1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(length1, length2, LengthUnit.YARD);

        assertEquals(0.666666, result.getValue());
    }
}
