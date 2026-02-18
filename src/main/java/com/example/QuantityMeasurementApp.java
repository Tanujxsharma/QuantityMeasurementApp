package com.example;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inputOne = sc.nextInt();
        int inputTwo = sc.nextInt();
        Feet feet1 = new Feet(inputOne);
        Feet feet2 = new Feet(inputTwo);

        System.out.println("Equal (" + feet1.equals(feet2) + ")");
    }
}
