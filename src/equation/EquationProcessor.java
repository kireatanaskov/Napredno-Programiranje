package equation;

import java.util.List;
import java.util.Optional;

public class EquationProcessor {
    public static <IN, OUT> void process(List<IN> inputs, List<Equation<IN, OUT>> equations) {
        inputs.forEach(input -> {
            System.out.printf("Input: %s%n", input);
            equations.forEach(equation -> {
                Optional<OUT> result = equation.calculate();
                if (input.equals(inputs.get(inputs.size() - 1)))
                    result.ifPresent(r -> System.out.printf("Result: %s\n", result.get()));
                if (result.isEmpty())
                    System.out.println("An error happened");
            });
        });
    }
}