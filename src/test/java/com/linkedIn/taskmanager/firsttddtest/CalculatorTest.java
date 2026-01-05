package com.linkedIn.taskmanager.firsttddtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    @Test
    void testDivideTwoPositiveNumbers() {
        //arrange
        Calculator calculator = new Calculator();

        //act
        double result = calculator.divide(10, 5);

        // assert
        assertEquals(2, result);
    }

    @Test
    void testDivideByZero() {

        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
        //assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0), "Cannot divide by zero")

    }
    @Test
    void testDivideNegativeNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.divide(-10, -5);
        assertEquals(2, result);
    }
}
