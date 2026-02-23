package com.quantityMeasurementApp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    private static final double EPS = 1e-6;

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_DifferentValues() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        assertFalse(w1.equals(w2));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertFalse(w1.equals(null));
    }


    @Test
    void testConversion_KilogramToGram() {
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_PoundToKilogram() {
        QuantityWeight w = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight result = w.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_RoundTrip() {
        QuantityWeight w = new QuantityWeight(1.5, WeightUnit.KILOGRAM);

        QuantityWeight gram = w.convertTo(WeightUnit.GRAM);
        QuantityWeight back = gram.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, back.getValue(), EPS);
    }



    @Test
    void testAddition_SameUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_WithZero() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_NegativeValue() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(-2000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_NullSecondOperand() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> w1.add(null));
    }
}
