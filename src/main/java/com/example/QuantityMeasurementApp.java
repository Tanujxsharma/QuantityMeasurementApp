package com.example;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            double value1 = sc.nextDouble();


            LengthUnit unit1 = LengthUnit.valueOf(sc.next().toUpperCase());


            double value2 = sc.nextDouble();


            LengthUnit unit2 = LengthUnit.valueOf(sc.next().toUpperCase());

            QuantityLength q1 = new QuantityLength(value1, unit1);
            QuantityLength q2 = new QuantityLength(value2, unit2);

            System.out.println("Input: " + q1 + " and " + q2);

            System.out.println("Equal (" + q1.equals(q2) + ")");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid unit entered. Please use FEET or INCH.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter numeric values.");
        }
    }
}
