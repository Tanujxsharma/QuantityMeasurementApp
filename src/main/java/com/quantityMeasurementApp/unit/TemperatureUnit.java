package com.quantityMeasurementApp.unit;
import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {


    CELSIUS(false),
    FAHRENHEIT(true),
    KELVIN(false);


    final Function<Double, Double> FAHRENHEIT_TO_CELSIUS = (fahrenheit) -> (fahrenheit - 32)*5/9;

    final Function<Double, Double> CELSIUS_TO_CELSIUS = (celsius) -> celsius;

    final Function<Double, Double> KELVIN_TO_CELSIUS = k -> k - 273.15;

    Function<Double, Double> conversionValue;

    SupportsArithmetic supportsArithmetic = ()->false;

    TemperatureUnit(boolean isFahrenheit){
        if (this.name().equals("FAHRENHEIT")) {
            this.conversionValue = FAHRENHEIT_TO_CELSIUS;
        } else if (this.name().equals("KELVIN")) {
            this.conversionValue = KELVIN_TO_CELSIUS;
        } else {
            this.conversionValue = CELSIUS_TO_CELSIUS;
        }
    }


    @Override
    public double getConversionValue() {
        return 1;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return conversionValue.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        switch (this) {
            case FAHRENHEIT:
                return (baseValue * 9 / 5) + 32;
            case KELVIN:
                return baseValue + 273.15;
            default:
                return baseValue; // CELSIUS
        }
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType(){
        return this.getClass().getSimpleName();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName){
        for(TemperatureUnit unit : TemperatureUnit.values()){
            if(unit.getUnitName().equalsIgnoreCase(unitName)){
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid temperature unit:" + unitName);
    }

    public double convertTo(double value, TemperatureUnit targetUnit) {
        double baseValue = this.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validOperationSupport(String operation) {
        if (!supportsArithmetic.isSupported()) {
            String message = this.name() + " does not support " + operation + " operations.";
            throw new UnsupportedOperationException(message);
        }
    }

    @Override
    public String toString() {
        return this.name();
    }

    public static void main(String[] args) {
        System.out.println("TemperatureUnit Enum");

        for (TemperatureUnit unit : TemperatureUnit.values()) {
            System.out.println(unit + " supports arithmetic: " + unit.supportsArithmetic());
        }

        System.out.println("0C to F = " +
                TemperatureUnit.CELSIUS.convertTo(0, TemperatureUnit.FAHRENHEIT));

        System.out.println("32F to C = " +
                TemperatureUnit.FAHRENHEIT.convertTo(32, TemperatureUnit.CELSIUS));
    }
}