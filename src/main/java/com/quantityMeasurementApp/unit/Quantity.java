package com.quantityMeasurementApp.unit;

public class Quantity<U extends IMeasurable> {
    private double value;
    private U unit;

    public Quantity(double value, U unit) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!targetUnit.getClass().equals(unit.getClass()))
            throw new IllegalArgumentException("Target unit should belong to same class");
        double baseValue = unit.convertToBaseUnit(value);
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseValue)), targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Quantity<?> other = (Quantity<?>) o;
        if (!this.unit.getClass().equals(other.unit.getClass())) return false;
        return Double.compare(unit.convertToBaseUnit(value), other.unit.convertToBaseUnit(other.value)) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    private enum ArithmeticOperation {
        ADD { @Override public double compute(double a, double b) { return a + b; } },
        SUBTRACT { @Override public double compute(double a, double b) { return a - b; } },
        DIVIDE {
            @Override public double compute(double a, double b) {
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return a / b;
            }
        };
        public abstract double compute(double a, double b);
    }

    public Quantity<U> add(Quantity<U> other) { return add(other, unit); }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        return buildQuantityFromBase(performBaseArithmetic(other, targetUnit, ArithmeticOperation.ADD, true), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) { return subtract(other, unit); }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        return buildQuantityFromBase(performBaseArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT, true), targetUnit);
    }

    public double divide(Quantity<U> other) {
        return performBaseArithmetic(other, null, ArithmeticOperation.DIVIDE, false);
    }

    private double performBaseArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation op, boolean targetUnitRequired) {
        validateArithmeticOperands(other, targetUnit, targetUnitRequired);
        return op.compute(unit.convertToBaseUnit(value), other.unit.convertToBaseUnit(other.value));
    }

    private void validateArithmeticOperands(Quantity<U> quantity, U targetUnit, boolean targetUnitRequired) {
        if (quantity == null) throw new IllegalArgumentException("Operand cannot be null");
        if (!Double.isFinite(this.value) || !Double.isFinite(quantity.value))
            throw new IllegalArgumentException("Values must be finite");
        if (!this.unit.getClass().equals(quantity.unit.getClass()))
            throw new IllegalArgumentException("Cross category operation not allowed");
        if (targetUnitRequired) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            if (!this.unit.getClass().equals(targetUnit.getClass()))
                throw new IllegalArgumentException("Invalid target unit category");
        }
    }

    private Quantity<U> buildQuantityFromBase(double baseValue, U targetUnit) {
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseValue)), targetUnit);
    }

    private double round(double value) { return (double) Math.round(value * 100) / 100; }

    @Override
    public String toString() { return String.format("%.2f %s", value, unit); }
}