package OOP.AuditoryExercises;

import java.util.Scanner;

class Calculator {
    private double result;

    public Calculator(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

interface CalculatorStrategy {
    double calculate(double a, double b);
}

class Plus implements CalculatorStrategy {
    @Override
    public double calculate(double a, double b) {
        return a+b;
    }
}

class Minus implements CalculatorStrategy {
    @Override
    public double calculate(double a, double b) {
        return a-b;
    }
}

class Divide implements CalculatorStrategy {
    @Override
    public double calculate(double a, double b) {
        return a/b;
    }
}

class Multiply implements CalculatorStrategy {
    @Override
    public double calculate(double a, double b) {
        return a*b;
    }
}

public class CalculatorTest {

    public static void main(String[] args) {
        Calculator calculator = new Calculator(0);
        Scanner input = new Scanner(System.in);

        while (true) {
            String line = input.nextLine();

            if (line.toLowerCase().startsWith("n")) {
                System.out.println("Final Result: " + calculator.getResult());
                break;
            } else if (line.toLowerCase().startsWith("y")) {
                calculator.setResult(0);
                System.out.println("Result: " + calculator.getResult());
                continue;
            } else if (line.toLowerCase().startsWith("r")) {
                System.out.println("Final Result: " + calculator.getResult());
                continue;
            } else if (!line.toLowerCase().matches("[+\\-*/]\\s*\\d+")) {
                System.out.println("Invalid Input");
                continue;
            }

            String[] tokens = line.split("\\s+");
            char operation = tokens[0].charAt(0);
            double amount = Double.parseDouble(tokens[1]);

            CalculatorStrategy strategy = null;

            if (operation == '+') {
                strategy = new Plus();
            } else if (operation == '-') {
                strategy = new Minus();
            } else if (operation == '*') {
                strategy = new Multiply();
            } else if (operation == '/') {
                strategy = new Divide();
            } else {
                System.out.println("Invalid operation!");
            }
            calculator.setResult(strategy.calculate(calculator.getResult(), amount));
            System.out.println("Result: " + calculator.getResult());
        }
    }
}
