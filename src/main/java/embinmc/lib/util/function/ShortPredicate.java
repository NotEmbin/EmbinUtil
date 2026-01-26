package embinmc.lib.util.function;

import embinmc.lib.util.annotation.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface ShortPredicate {
    boolean test(short input);

    default ShortPredicate and(@NotNull ShortPredicate predicate) {
        return input -> test(input) && Objects.requireNonNull(predicate).test(input);
    }

    default ShortPredicate negate() {
        return input -> !test(input);
    }

    default ShortPredicate or(@NotNull ShortPredicate predicate) {
        return input -> test(input) || Objects.requireNonNull(predicate).test(input);
    }
}
