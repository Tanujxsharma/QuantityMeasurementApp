package com.quantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testTemperatureEquality_CelsiusToCelsius(){
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        assertEquals(t1, t2);
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit(){
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(t1, t2);
    }

    @Test
    void testTemperatureEquality_Negative40(){
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(t1, t2);
    }


    @Test
    void testTemperatureConversion_CelsiusToFahrenheit(){
        Quantity<TemperatureUnit> t =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                t.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(
                new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT),
                result);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius(){
        Quantity<TemperatureUnit> t =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> result =
                t.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS),
                result);
    }


    @Test
    void testTemperatureUnsupported_Add(){
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.add(t2));
    }

    @Test
    void testTemperatureUnsupported_Subtract(){
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.subtract(t2));
    }

    @Test
    void testTemperatureUnsupported_Divide(){
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.divide(t2));
    }


    @Test
    void testTemperatureVsLength(){
        Quantity<TemperatureUnit> temp =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> length =
                new Quantity<>(100.0, LengthUnit.FEET);

        assertFalse(temp.equals(length));
    }



    @Test
    void testLengthSupportsArithmetic(){
        Quantity<LengthUnit> l1 =
                new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(
                new Quantity<>(15.0, LengthUnit.FEET),
                l1.add(l2));
    }



    @Test
    void testTemperatureImmutability(){
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        try {
            t1.add(t2);
        } catch (Exception ignored){}

        assertEquals(
                new Quantity<>(100.0, TemperatureUnit.CELSIUS),
                t1);
    }


    @Test
    void testTemperatureNullUnit(){
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, null));
    }

}