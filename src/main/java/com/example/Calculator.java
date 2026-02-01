package main.java.com.example;

public class Calculator {

    public int calculate(int a, int b, String op) {
        switch (op) {
            case "add", "add-again":
                return add(a, b);

            case "sub", "sub-again":
                return a - b;

            case "mul":
                return a * b;

            case "div":
                return b == 0 ? 0 : a / b;

            case "mod":
                return a % b;

            case "pow":
                return power(a, b);

            default:
                return 0;
        }
    }

    private int add(int a, int b) {
        return a + b;
    }

    private int power(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }
}