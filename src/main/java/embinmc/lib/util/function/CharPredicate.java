package embinmc.lib.util.function;

import embinmc.lib.util.annotation.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface CharPredicate {
    boolean test(char input);

    default CharPredicate and(@NotNull CharPredicate predicate) {
        return input -> test(input) && Objects.requireNonNull(predicate).test(input);
    }

    default CharPredicate negate() {
        return input -> !test(input);
    }

    default CharPredicate or(@NotNull CharPredicate predicate) {
        return input -> test(input) || Objects.requireNonNull(predicate).test(input);
    }
}
