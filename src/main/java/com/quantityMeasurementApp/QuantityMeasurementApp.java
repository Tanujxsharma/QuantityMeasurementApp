package com.quantityMeasurementApp;
public class QuantityMeasurementApp {

    public static void LengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {

        double result = QuantityLength.convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") → " + result);
    }
    public static void LengthEquality(QuantityLength a,
                                                 QuantityLength b) {

        System.out.println(a + " equals " + b + " ? " + a.equals(b));
    }

    public static void main(String[] args) {

        LengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        LengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);
        LengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);
        LengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

        LengthEquality(
                new QuantityLength(1.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET)
        );
    }
}