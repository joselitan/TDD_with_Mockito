package com.linkedIn.taskmanager.firsttddtest;

public class Calculator {
    public double divide(double a, double b) {
        double result = 0;
        if(b != 0){
            result = a / b;
        } else {
            throw new ArithmeticException("Cannot divide by zero");
        }
        //double result = a / b;
        return result;
    }

}
