package com.quantityMeasurementApp.unit;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    // ✅ Lambda seedha constructor mein diya — this.name() wali problem khatam
    CELSIUS(v -> v, v -> v),
    FAHRENHEIT(v -> (v - 32) * 5.0 / 9.0, v -> (v * 9.0 / 5.0) + 32),
    KELVIN(v -> v - 273.15, v -> v + 273.15);

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    // ✅ SupportsArithmetic ka simple boolean field — FunctionalInterface overkill tha
    private final boolean arithmeticSupported = false;

    TemperatureUnit(Function<Double, Double> toBase, Function<Double, Double> fromBase){
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public double getConversionValue() {
        return 1;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBase.apply(baseValue);
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
        throw new IllegalArgumentException("Invalid temperature unit: " + unitName);
    }

    // ✅ Temperature arithmetic support nahi karta
    @Override
    public boolean supportArithmetic(){
        return arithmeticSupported;
    }

    @Override
    public void validOperationSupport(String operation) {
        if (!arithmeticSupported) {
            throw new UnsupportedOperationException(
                    this.name() + " does not support " + operation + " operations."
            );
        }
    }

    public double convertTo(double value, TemperatureUnit targetUnit) {
        double baseValue = this.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    @Override
    public String toString() {
        return this.name();
    }
}