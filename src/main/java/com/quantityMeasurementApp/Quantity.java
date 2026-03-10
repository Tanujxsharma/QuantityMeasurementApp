package com.quantityMeasurementApp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if(unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public Quantity<U> convertTo(U targetUnit) {

        if(targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        if(other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if(this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        double resultBase = base1 + base2;

        double result = this.unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if(other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if(targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if(this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        double resultBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {

        if(other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if(this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        double resultBase = base1 - base2;

        double result = this.unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if(other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if(targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if(this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        double resultBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<U> other) {

        if(other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if(this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        if(base2 == 0)
            throw new ArithmeticException("Division by zero");

        return base1 / base2;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if(this.unit.getClass() != other.unit.getClass())
            return false;

        double base1 = this.toBaseUnit();
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Double.compare(round(base1), round(base2)) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round(toBaseUnit()));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}