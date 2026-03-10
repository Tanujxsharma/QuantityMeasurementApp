package com.quantityMeasurementApp;

import java.util.function.DoubleBinaryOperator;

enum ArithmeticOperation {

    ADD((a,b) -> a + b),

    SUBTRACT((a,b) -> a - b),

    DIVIDE((a,b) -> {
        if(b == 0)
        {throw new ArithmeticException("Division by zero");}
        return a / b;
    });

    private final DoubleBinaryOperator operator;

    ArithmeticOperation(DoubleBinaryOperator operator){
        this.operator = operator;
    }

    public double compute(double a,double b){
        return operator.applyAsDouble(a,b);
    }
}