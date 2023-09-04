package calculator;

import java.util.Scanner;

public class CalculatorTest {

    public static char getAnswer(String line) {
        if (line.trim().length() > 0) {
            return Character.toLowerCase(line.charAt(0));
        } else return '?';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Calculator calculator = new Calculator();
            System.out.println(calculator.init());

            while (true) {
                String line = scanner.nextLine();
                char choice = getAnswer(line);
                if (choice == 'r') {
                    System.out.printf("final result = %f%n", calculator.getResult());
                    break;
                }

                String[] parts = line.split("\\s+");
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

            System.out.println("(y/n)?");
            String line = scanner.nextLine();
            char choice = getAnswer(line);
            if (choice == 'n') break;
        }
    }
}
