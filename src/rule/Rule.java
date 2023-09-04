package rule;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Rule<IN, OUT> {
    private Predicate<IN> condition;
    private Function<IN, OUT> mapper;

    public Rule(Predicate<IN> condition, Function<IN, OUT> mapper) {
        this.condition = condition;
        this.mapper = mapper;
    }

    public Optional<OUT> apply (IN input) {
        if (condition.test(input)) {
            return Optional.of(mapper.apply(input));
        } else {
            return Optional.empty();
        }
    }
}
