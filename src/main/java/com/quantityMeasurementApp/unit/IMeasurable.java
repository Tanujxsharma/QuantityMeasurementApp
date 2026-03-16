package com.quantityMeasurementApp.unit;


@FunctionalInterface
interface SupportsArithmetic{
    boolean isSupported();
}

public interface IMeasurable {

    SupportsArithmetic supportsArithmetic = () -> true;


    double getConversionValue();


    double convertToBaseUnit(double value);


    double convertFromBaseUnit(double baseValue);

    String getUnitName();


    String getMeasurementType();

    IMeasurable getUnitInstance(String unitName);

    default boolean supportArithmetic(){return supportsArithmetic.isSupported();}

    default void validOperationSupport(String operation){}

    default double add(double a, double b){throw new UnsupportedOperationException("Addition is not supported for unit: " + getUnitName());}
    default double subtract(double a, double b){throw new UnsupportedOperationException("Subtraction is not supported for unit: " + getUnitName());}
    default double divide(double a, double b){throw new UnsupportedOperationException("Division is not supported for unit: " + getUnitName());}


    public static void main(String[] args) {
        System.out.println("IMeasurable Interface");
    }
}