package calculator;

public class Calculator {
    private double result;
    private Strategy strategy;

    public Calculator() {
        this.result = 0.0;
    }

    public String execute(char operator, double num) throws UnknownOperatorException {
        if (operator == '+') {
            strategy = new Addition();
        } else if (operator == '-') {
            strategy = new Subtraction();
        } else if (operator == '*') {
            strategy = new Multiplication();
        } else if (operator == '/') {
            strategy = new Division();
        } else throw new UnknownOperatorException(operator);

        result = strategy.calculate(this.result, num);

        return String.format("result %c %.2f = %.2f", operator, num, result);
    }

    public double getResult() {
        return result;
    }

    public String init() {
        return String.format("Result = %.2f", result);
    }

    @Override
    public String toString() {
        return String.format("updated result = %.2f", result);
    }
}
