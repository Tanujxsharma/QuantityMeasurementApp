package com.quantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityArithmeticTest {

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        assertEquals(new Quantity<>(5.0,LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre(){
        Quantity<VolumeUnit> q1 = new Quantity<>(10.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(3.0,VolumeUnit.LITRE);

        assertEquals(new Quantity<>(7.0,VolumeUnit.LITRE), q1.subtract(q2));
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(9.5,LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet(){
        Quantity<LengthUnit> q1 = new Quantity<>(120.0,LengthUnit.INCH);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        assertEquals(new Quantity<>(60.0,LengthUnit.INCH), q1.subtract(q2));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(9.5,LengthUnit.FEET),
                q1.subtract(q2,LengthUnit.FEET));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(114.0,LengthUnit.INCH),
                q1.subtract(q2,LengthUnit.INCH));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre(){
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0,VolumeUnit.LITRE);

        assertEquals(new Quantity<>(3000.0,VolumeUnit.MILLILITRE),
                q1.subtract(q2,VolumeUnit.MILLILITRE));
    }

    @Test
    void testSubtraction_ResultingInNegative(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0,LengthUnit.FEET);

        assertEquals(new Quantity<>(-5.0,LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_ResultingInZero(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(0.0,LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_WithZeroOperand(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0,LengthUnit.INCH);

        assertEquals(new Quantity<>(5.0,LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_WithNegativeValues(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(-2.0,LengthUnit.FEET);

        assertEquals(new Quantity<>(7.0,LengthUnit.FEET), q1.subtract(q2));
    }

    @Test
    void testSubtraction_NonCommutative(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0,LengthUnit.FEET);

        assertEquals(new Quantity<>(5.0,LengthUnit.FEET), q1.subtract(q2));
        assertEquals(new Quantity<>(-5.0,LengthUnit.FEET), q2.subtract(q1));
    }

    @Test
    void testSubtraction_NullOperand(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.subtract(null));
    }

    @Test
    void testDivision_SameUnit_FeetDividedByFeet(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0,LengthUnit.FEET);

        assertEquals(5.0,q1.divide(q2));
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre(){
        Quantity<VolumeUnit> q1 = new Quantity<>(10.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(5.0,VolumeUnit.LITRE);

        assertEquals(2.0,q1.divide(q2));
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches(){
        Quantity<LengthUnit> q1 = new Quantity<>(24.0,LengthUnit.INCH);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0,LengthUnit.FEET);

        assertEquals(1.0,q1.divide(q2));
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram(){
        Quantity<WeightUnit> q1 = new Quantity<>(2.0,WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(2000.0,WeightUnit.GRAM);

        assertEquals(1.0,q1.divide(q2));
    }

    @Test
    void testDivision_RatioLessThanOne(){
        Quantity<LengthUnit> q1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0,LengthUnit.FEET);

        assertEquals(0.5,q1.divide(q2));
    }

    @Test
    void testDivision_RatioEqualToOne(){
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
    void testDivision_NullOperand(){
        Quantity<LengthUnit> q1 = new Quantity<>(10.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.divide(null));
    }

}