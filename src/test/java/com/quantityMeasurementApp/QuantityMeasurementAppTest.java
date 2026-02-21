package com.quantityMeasurementApp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.INCH)));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(12.0, LengthUnit.INCH)));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertTrue(new QuantityLength(12.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.INCH)
                .equals(new QuantityLength(2.0, LengthUnit.INCH)));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }
    @Test
    void testEquality_YardToYard_SameValue(){
        QuantityLength lengthOne = new QuantityLength(3.0, LengthUnit.YARD);
        QuantityLength lengthSecond = new QuantityLength(3.0,LengthUnit.YARD);
        assertTrue(lengthOne.equals(lengthSecond)
        );
    }
    @Test
    void testEquality_YardToYard_differentValue(){
        QuantityLength lengthOne = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength lengthSecond = new QuantityLength(3.0,LengthUnit.YARD);
        assertFalse(lengthOne.equals(lengthSecond));
    }
    @Test
    void testEquality_FeetToYard_EquivalentValue(){
        QuantityLength lengthOne = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength lengthSecond = new QuantityLength(1.0,LengthUnit.YARD);
        assertTrue(lengthOne.equals(lengthSecond));
    }
    @Test
    void testEquality_YardToFeet_EquivalentValue(){
        QuantityLength lengthOne = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength lengthSecond = new QuantityLength(3.0,LengthUnit.FEET);
        assertTrue(lengthOne.equals(lengthSecond));
    }

    @Test
    void testEquality_YardToInch_EquivalentValue(){
        QuantityLength lengthOne = new QuantityLength(1.0,LengthUnit.YARD);
        QuantityLength lengthSecond = new QuantityLength(36.0,LengthUnit.INCH);
        assertTrue(lengthOne.equals(lengthSecond));
    }
   @Test
    void testEquality_InchToYard_EquivalentValue(){
       QuantityLength lengthOne = new QuantityLength(36.0,LengthUnit.INCH);
       QuantityLength lengthSecond = new QuantityLength(1.0,LengthUnit.YARD);
       assertTrue(lengthOne.equals(lengthSecond));
   }
    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(0.393701, LengthUnit.INCH)));
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength c = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testEquality_YardSameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.YARD);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_YardNullComparison() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.YARD);
        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_CentimetersWithNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testEquality_CentimetersSameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {

        QuantityLength yards = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(72.0, LengthUnit.INCH);

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }
    @Test
    void testConversion_FeetToInches(){
        double result = QuantityLength.convert(1.0,LengthUnit.FEET,LengthUnit.INCH);
        assertEquals(12,result);
    }
    @Test
    void testConversion_InchesToFeet(){
        double result = QuantityLength.convert(24.0,LengthUnit.INCH,LengthUnit.FEET);
        assertEquals(2.0,result);
    }
    @Test
    void testConversion_YardsToInches(){
        double result = QuantityLength.convert(1.0,LengthUnit.YARD,LengthUnit.INCH);
        assertEquals(36.0,result);
    }
    @Test
    void testConversion_InchesToYards() {
        double result = QuantityLength.convert(72.0, LengthUnit.INCH, LengthUnit.YARD);
        assertEquals(2.0, result);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARD);
        assertEquals(2.0, result);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(0.0, result);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(-12.0, result);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityLength.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH);
        assertEquals(1.0, result);   // may need EPS if precision issue
    }

    @Test
    void testConversion_SameUnit() {
        double result = QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET);
        assertEquals(5.0, result);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));
    }

    @Test
    void testConversion_Infinity_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
    }

}
