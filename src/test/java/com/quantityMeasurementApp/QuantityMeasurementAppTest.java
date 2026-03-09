package com.quantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityVolumeTest {

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0,LengthUnit.FEET);
        assertEquals(new Quantity<>(5.0,LengthUnit.FEET),length1.subtract(length2));
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre(){
        Quantity<VolumeUnit> v1 = new Quantity<>(10.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(3.0,VolumeUnit.LITRE);
        assertEquals(new Quantity<>(7.0,VolumeUnit.LITRE),v1.subtract(v2));
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0,LengthUnit.INCH);
        assertEquals(new Quantity<>(9.5,LengthUnit.FEET),length1.subtract(length2));
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet(){
        Quantity<LengthUnit> length1 = new Quantity<>(120.0,LengthUnit.INCH);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0,LengthUnit.FEET);
        assertEquals(new Quantity<>(60.0,LengthUnit.INCH),length1.subtract(length2));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0,LengthUnit.INCH);
        assertEquals(new Quantity<>(9.5,LengthUnit.FEET),
                length1.subtract(length2));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0,LengthUnit.INCH);
        assertEquals(new Quantity<>(114.0,LengthUnit.INCH),
                length1.subtract(length2));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre(){
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2.0,VolumeUnit.LITRE);
        assertEquals(new Quantity<>(3000.0,VolumeUnit.MILLILITRE),
                v1.subtract(v2));
    }

    @Test
    void testSubtraction_ResultingInNegative(){
        Quantity<LengthUnit> length1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(10.0,LengthUnit.FEET);
        assertEquals(new Quantity<>(-5.0,LengthUnit.FEET),
                length1.subtract(length2));
    }

    @Test
    void testSubtraction_ResultingInZero(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(120.0,LengthUnit.INCH);
        assertEquals(new Quantity<>(0.0,LengthUnit.FEET),
                length1.subtract(length2));
    }

    @Test
    void testSubtraction_WithZeroOperand(){
        Quantity<LengthUnit> length1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(0.0,LengthUnit.INCH);
        assertEquals(new Quantity<>(5.0,LengthUnit.FEET),
                length1.subtract(length2));
    }

    @Test
    void testSubtraction_WithNegativeValues(){
        Quantity<LengthUnit> length1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(-2.0,LengthUnit.FEET);
        assertEquals(new Quantity<>(7.0,LengthUnit.FEET),
                length1.subtract(length2));
    }

    @Test
    void testSubtraction_NonCommutative(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0,LengthUnit.FEET);

        Quantity<LengthUnit> result1 = length1.subtract(length2);
        Quantity<LengthUnit> result2 = length2.subtract(length1);

        assertEquals(new Quantity<>(5.0,LengthUnit.FEET),result1);
        assertEquals(new Quantity<>(-5.0,LengthUnit.FEET),result2);
    }

    @Test
    void testSubtraction_NullOperand(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,
                () -> length1.subtract(null));
    }

    @Test
    void testDivision_SameUnit_FeetDividedByFeet(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(2.0,LengthUnit.FEET);
        assertEquals(5.0,length1.divide(length2));
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre(){
        Quantity<VolumeUnit> v1 = new Quantity<>(10.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(5.0,VolumeUnit.LITRE);
        assertEquals(2.0,v1.divide(v2));
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches(){
        Quantity<LengthUnit> length1 = new Quantity<>(24.0,LengthUnit.INCH);
        Quantity<LengthUnit> length2 = new Quantity<>(2.0,LengthUnit.FEET);
        assertEquals(1.0,length1.divide(length2));
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram(){
        Quantity<WeightUnit> w1 = new Quantity<>(2.0,WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(2000.0,WeightUnit.GRAM);
        assertEquals(1.0,w1.divide(w2));
    }

    @Test
    void testDivision_RatioGreaterThanOne(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(2.0,LengthUnit.FEET);
        assertEquals(5.0,length1.divide(length2));
    }

    @Test
    void testDivision_RatioLessThanOne(){
        Quantity<LengthUnit> length1 = new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(10.0,LengthUnit.FEET);
        assertEquals(0.5,length1.divide(length2));
    }

    @Test
    void testDivision_RatioEqualToOne(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(10.0,LengthUnit.FEET);
        assertEquals(1.0,length1.divide(length2));
    }

    @Test
    void testDivision_NonCommutative(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0,LengthUnit.FEET);

        double r1 = length1.divide(length2);
        double r2 = length2.divide(length1);

        assertEquals(2.0,r1);
        assertEquals(0.5,r2);
    }

    @Test
    void testDivision_ByZero(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(0.0,LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> length1.divide(length2));
    }

    @Test
    void testDivision_NullOperand(){
        Quantity<LengthUnit> length1 = new Quantity<>(10.0,LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,
                () -> length1.divide(null));
    }
}