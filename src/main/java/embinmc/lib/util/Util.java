package embinmc.lib.util;

import embinmc.lib.util.annotation.UseAsLambda;
import embinmc.lib.util.exception.NotNullException;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public final class Util {

    /**
     * <p>Repeats a method a specified amount of times.</p>
     * <p>The supplied integer is the index.</p>
     * <p>Index starts at 1.</p>
     */
    public static void repeat(int amount, Consumer<Integer> action) {
        for (int index = 1; index <= amount; index++) {
            action.accept(index);
        }
    }

    public static <Z> void ifNotNull(Z thing, Consumer<Z> function) {
        if (thing != null) {
            function.accept(thing);
        }
    }

    public static <Z, Q> Optional<Q> ifNotNullReturn(Z thing, Function<Z, Q> function) {
        if (thing != null) {
            return Optional.ofNullable(function.apply(thing));
        }
        return Optional.empty();
    }

    public static <T> void requireNull(T thing, String message) {
        if (thing != null) throw new NotNullException(message);
    }

    public static <T> T create(T thing, Consumer<T> consumer) {
        consumer.accept(thing);
        return thing;
    }

    public static <T> T create(Supplier<T> supplier) {
        return supplier.get();
    }

    @UseAsLambda
    public static <T> boolean always(T unused) {
        return true;
    }

    @UseAsLambda
    public static <T> boolean never(T unused) {
        return false;
    }

    @UseAsLambda
    public static boolean always() {
        return true;
    }

    @UseAsLambda
    public static boolean never() {
        return false;
    }

    @UseAsLambda
    public static <T> T itself(T thing) {
        return thing;
    }
}
