package Lambda.AuditoryExercises;

import java.util.Scanner;

interface Operation2 {
    double apply(double a, double b);
}

class UnknownOperatorException extends Exception {
    public UnknownOperatorException(char operator)
    {
        super(String.format("The character you used ( %c ) is not an operation", operator));
    }
}

class OperationFactory {
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = ':';

    private static final Operation2 ADDITION = (a, b) -> a + b;
    private static final Operation2 SUBTRACTION = (a, b) -> a - b;
    private static final Operation2 MULTIPLICATION = (a, b) -> a * b;
    private static final Operation2 DIVISION = (a, b) -> a / b;

    public static Operation2 getOperation(char operator) throws UnknownOperatorException {
        if (operator == PLUS)
        {
            return ADDITION;
        }
        else if (operator == MINUS)
        {
            return SUBTRACTION;
        }
        else if (operator == MULTIPLY)
        {
            return MULTIPLICATION;
        }
        else if(operator == DIVIDE)
        {
            return DIVISION;
        }
        else throw new UnknownOperatorException(operator);
    }
}

class Calculator2 {
    private double result;

    public Calculator2() {
        result = 0;
    }

    public String init() {
        return String.format("result = %f", result);
    }

    public double getResult() {
        return result;
    }

    public String execute(char operator, double value) throws UnknownOperatorException {
        Operation2 op = OperationFactory.getOperation(operator);
        result = op.apply(result, value);
        return String.format("result %c %f = %f", operator, value, result);
    }

    @Override
    public String toString() {
        return String.format("updated result = %f", result);
    }
}

public class CalculatorTest2 {
    static final char RESULT = 'r';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Calculator2 calculator = new Calculator2();
            System.out.println(calculator.init());

            while (true) {
                String line = scanner.nextLine();
                char choice = getCharLower(line);

                if (choice == RESULT) {
                    System.out.printf("final result = %f%n", calculator.getResult());
                    break;
                }

                String[] parts = line.split("\\s+");
                if (parts.length < 2) {
                    System.out.println("Please enter: <operator> <number>");
                    continue;
                }

                char operator = parts[0].charAt(0);
                double value = Double.parseDouble(parts[1]);

                try {
                    String result = calculator.execute(operator, value);
                    System.out.println(result);
                    System.out.println(calculator);
                } catch (UnknownOperatorException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("(Y/N)");
            String again = scanner.nextLine();
            char choice2 = getCharLower(again);
            if (choice2 == 'n') {
                break;
            }
        }
    }

    static char getCharLower(String line) {
        if (line.trim().length() > 0) {
            return Character.toLowerCase(line.charAt(0));
        }
        return '?';
    }
}
