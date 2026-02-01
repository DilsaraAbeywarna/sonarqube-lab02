package main.java.com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals(15, calculator.calculate(10, 5, "add"));
    }

    @Test
    void testAddAgain() {
        assertEquals(15, calculator.calculate(10, 5, "add-again"));
    }

    @Test
    void testSubtraction() {
        assertEquals(5, calculator.calculate(10, 5, "sub"));
    }

    @Test
    void testSubAgain() {
        assertEquals(5, calculator.calculate(10, 5, "sub-again"));
    }

    @Test
    void testMultiplication() {
        assertEquals(50, calculator.calculate(10, 5, "mul"));
    }

    @Test
    void testDivision() {
        assertEquals(2, calculator.calculate(10, 5, "div"));
    }

    @Test
    void testDivisionByZero() {
        assertEquals(0, calculator.calculate(10, 0, "div"));
    }

    @Test
    void testModulo() {
        assertEquals(0, calculator.calculate(10, 5, "mod"));
    }

    @Test
    void testPower() {
        assertEquals(100000, calculator.calculate(10, 5, "pow"));
    }

    @Test
    void testDefaultCase() {
        assertEquals(0, calculator.calculate(10, 5, "invalid"));
    }
}