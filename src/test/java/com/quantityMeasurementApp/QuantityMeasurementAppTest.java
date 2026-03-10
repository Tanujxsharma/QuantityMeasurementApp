package com.quantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testAddition_SameUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        assertEquals(new Quantity<>(15.0,LengthUnit.FEET),q1.add(q2));
    }

    @Test
    void testAddition_CrossUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(2.0,LengthUnit.FEET),q1.add(q2));
    }

    @Test
    void testAddition_ExplicitTargetUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(1.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(24.0,LengthUnit.INCH),
                q1.add(q2,LengthUnit.INCH));
    }

    @Test
    void testSubtraction_SameUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        assertEquals(new Quantity<>(5.0,LengthUnit.FEET),q1.subtract(q2));
    }

    @Test
    void testSubtraction_CrossUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(9.5,LengthUnit.FEET),q1.subtract(q2));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(114.0,LengthUnit.INCH),
                q1.subtract(q2,LengthUnit.INCH));
    }

    @Test
    void testSubtraction_ResultNegative(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0,LengthUnit.FEET);

        assertEquals(new Quantity<>(-5.0,LengthUnit.FEET),q1.subtract(q2));
    }

    @Test
    void testDivision_SameUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0,LengthUnit.FEET);

        assertEquals(5.0,q1.divide(q2));
    }

    @Test
    void testDivision_CrossUnit(){
        Quantity<LengthUnit> q1 = new Quantity<>(24.0,LengthUnit.INCH);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0,LengthUnit.FEET);

        assertEquals(1.0,q1.divide(q2));
    }

    @Test
    void testDivision_RatioLessThanOne(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0,LengthUnit.FEET);

        assertEquals(0.5,q1.divide(q2));
    }

    @Test
    void testDivision_RatioEqualOne(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0,LengthUnit.FEET);

        assertEquals(1.0,q1.divide(q2));
    }

    @Test
    void testDivision_ByZero(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0,LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> q1.divide(q2));
    }

    @Test
    void testNullOperand_Add(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(null));
    }

    @Test
    void testNullOperand_Subtract(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.subtract(null));
    }

    @Test
    void testNullOperand_Divide(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.divide(null));
    }

    @Test
    void testCrossCategory_Add(){
        Quantity<LengthUnit> length = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0,WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.add((Quantity)weight));
    }

    @Test
    void testCrossCategory_Subtract(){
        Quantity<LengthUnit> length = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0,WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.subtract((Quantity)weight));
    }

    @Test
    void testCrossCategory_Divide(){
        Quantity<LengthUnit> length = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0,WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.divide((Quantity)weight));
    }

    @Test
    void testImmutability_AfterAddition(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        q1.add(q2);

        assertEquals(new Quantity<>(10.0,LengthUnit.FEET),q1);
        assertEquals(new Quantity<>(5.0,LengthUnit.FEET),q2);
    }

    @Test
    void testImmutability_AfterSubtraction(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(new Quantity<>(10.0,LengthUnit.FEET),q1);
        assertEquals(new Quantity<>(5.0,LengthUnit.FEET),q2);
    }

    @Test
    void testImmutability_AfterDivision(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        q1.divide(q2);

        assertEquals(new Quantity<>(10.0,LengthUnit.FEET),q1);
        assertEquals(new Quantity<>(5.0,LengthUnit.FEET),q2);
    }

}