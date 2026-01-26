package embinmc.lib.util.function;

import embinmc.lib.util.annotation.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface BytePredicate {
    boolean test(byte input);

    default BytePredicate and(@NotNull BytePredicate predicate) {
        return input -> test(input) && Objects.requireNonNull(predicate).test(input);
    }

    default BytePredicate negate() {
        return input -> !test(input);
    }

    default BytePredicate or(@NotNull BytePredicate predicate) {
        return input -> test(input) || Objects.requireNonNull(predicate).test(input);
    }
}
