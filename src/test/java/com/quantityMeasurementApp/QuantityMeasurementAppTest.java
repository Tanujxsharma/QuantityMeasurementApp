package com.quantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityVolumeTest {

    @Test
    void testEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_LitreToGallon() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_DifferentValue() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        assertFalse(v1.equals(v2));
    }


    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> v =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                v.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue());
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> v =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result =
                v.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue());
    }

    @Test
    void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> v =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                v.convertTo(VolumeUnit.GALLON);

        assertEquals(1.0, result.getValue());
    }


    @Test
    void testAddition_SameUnit() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(3.0, result.getValue());
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(2.0, result.getValue());
    }

    @Test
    void testAddition_ExplicitTarget_Millilitre() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                v1.add(v2, VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue());
    }

    @Test
    void testAddition_GallonPlusLitre() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(2.0, result.getValue());
    }



    @Test
    void testVolumeVsLength_Incompatible() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(volume.equals(length));
    }

    @Test
    void testVolumeVsWeight_Incompatible() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(volume.equals(weight));
    }


    @Test
    void testZeroValue() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(0.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(0.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testNegativeValue() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(-1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testLargeValue() {
        Quantity<VolumeUnit> v1 =
                new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.LITRE);

        assertTrue(v1.equals(v2));
    }
}