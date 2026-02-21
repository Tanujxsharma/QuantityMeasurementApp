package com.quantityMeasurementApp;
import java.util.*;
public class QuantityLength {
    private final double value;
    private final LengthUnit unit ;
    public QuantityLength(double value, LengthUnit unit) {
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("the value must be finite ");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }
    private double toFeet(){
        return unit.toFeet(value);
    }
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {

        return unit;
    }
    public static double convert(double value,LengthUnit source,LengthUnit target){
        if(!Double.isFinite(value)){
            throw new IllegalArgumentException("the value must be finite");
        }
        if(source == null || target == null){
            throw new IllegalArgumentException("Unit cannot be null");
        }
        double valueToFeet = source.toFeet(value);

        return target.fromFeet(valueToFeet);
    }

    public QuantityLength convertTo(LengthUnit target) {
        double converted = convert(this.value, this.unit, target);
        return new QuantityLength(converted, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        QuantityLength other = (QuantityLength) obj;

        return Double.compare(this.toFeet(), other.toFeet()) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(toFeet());
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
