package com.quantityMeasurementApp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
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

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Value must be finite");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException(
                    "Different measurement categories");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException(
                    "Target unit cannot be null");
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        return operation.compute(base1, base2);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }


    public Quantity<U> add(Quantity<U> other) {

        unit.validateOperationSupport("addition");

        validateArithmeticOperands(other, null, false);

        double resultBase =
                performBaseArithmetic(other,
                        ArithmeticOperation.ADD);

        double result =
                unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), unit);
    }

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit) {

        unit.validateOperationSupport("addition");

        validateArithmeticOperands(other,
                targetUnit, true);

        double resultBase =
                performBaseArithmetic(other,
                        ArithmeticOperation.ADD);

        double result =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }


    public Quantity<U> subtract(Quantity<U> other) {

        unit.validateOperationSupport("subtraction");

        validateArithmeticOperands(other, null, false);

        double resultBase =
                performBaseArithmetic(other,
                        ArithmeticOperation.SUBTRACT);

        double result =
                unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), unit);
    }

    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit) {

        unit.validateOperationSupport("subtraction");

        validateArithmeticOperands(other,
                targetUnit, true);

        double resultBase =
                performBaseArithmetic(other,
                        ArithmeticOperation.SUBTRACT);

        double result =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }


    public double divide(Quantity<U> other) {

        unit.validateOperationSupport("division");

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other,
                ArithmeticOperation.DIVIDE);
    }



    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException(
                    "Target unit cannot be null");

        double base = toBaseUnit();

        double result =
                targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(result), targetUnit);
    }



    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass())
            return false;

        double thisBase = this.toBaseUnit();
        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Double.compare(
                round(thisBase),
                round(otherBase)) == 0;
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