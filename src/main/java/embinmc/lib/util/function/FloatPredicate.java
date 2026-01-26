package embinmc.lib.util.function;

import embinmc.lib.util.annotation.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface FloatPredicate {
    boolean test(float input);

    default FloatPredicate and(@NotNull FloatPredicate predicate) {
        return input -> test(input) && Objects.requireNonNull(predicate).test(input);
    }

    default FloatPredicate negate() {
        return input -> !test(input);
    }

    default FloatPredicate or(@NotNull FloatPredicate predicate) {
        return input -> test(input) || Objects.requireNonNull(predicate).test(input);
    }
}
