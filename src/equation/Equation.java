package equation;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Equation<IN, OUT> {
    private Supplier<IN> supplier;
    private Function<IN, OUT> function;

    public Equation(Supplier<IN> supplier, Function<IN, OUT> function) {
        this.supplier = supplier;
        this.function = function;
    }

    public Optional<OUT> calculate() {
        return Optional.of(function.apply(supplier.get()));
    }
}
