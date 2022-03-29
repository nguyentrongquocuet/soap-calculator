package com.quoc.application.service;

public class CalculatorService {
    public static double add(double m, double n) {
       return m + n;
    }

    public static double subtract(double m, double n) {
        return m - n;
    }

    public static double divide(double m, double n) {
        return m / n;
    }

    public static double multiply(double m, double n) {
        return m * n;
    }

    public static double pow(double m, double n) {
        return Math.pow(m, n);
    }

    public static double performOperation(String m, String op, String n) {
        int mNum = Integer.valueOf(m);
        int nNum = Integer.valueOf(n);

        switch (op) {
            case "+":
                return add(mNum, nNum);
            case "-":
                return subtract(mNum, nNum);
            case "*":
                return multiply(mNum, nNum);
            case "/":
                return divide(mNum, nNum);
            case "**":
                return pow(mNum, nNum);
        }

        throw new Error("Operation not supported: " + op);
    }
}
