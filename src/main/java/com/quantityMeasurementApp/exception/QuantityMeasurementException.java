package com.quantityMeasurementApp.exception;

public class QuantityMeasurementException extends RuntimeException {
    public QuantityMeasurementException(String msg)
    { super(msg);
    }
    public QuantityMeasurementException(String msg, Throwable cause)
    { super(msg, cause);
    }
}