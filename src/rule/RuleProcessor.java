package rule;

import java.util.List;
import java.util.Optional;

public class RuleProcessor {
    public static <IN, OUT> void process(List<IN> data, List<Rule<IN, OUT>> rules) {
        data.forEach(input -> {
            System.out.printf("Input: %s", input);
            rules.forEach(rule -> {
                Optional<OUT> result = rule.apply(input);
                result.ifPresent(r -> System.out.printf("Result: %s", r));
                if (result.isEmpty()) {
                    System.out.println("Condition not met");
                }
            });
        });
    }
}
