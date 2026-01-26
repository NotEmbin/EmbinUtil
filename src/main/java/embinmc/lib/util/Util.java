package embinmc.lib.util;

import embinmc.lib.util.annotation.NotNull;
import embinmc.lib.util.annotation.Nullable;
import embinmc.lib.util.annotation.UseAsLambda;
import embinmc.lib.util.exception.NotNullException;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public final class Util {
    private Util() {
        Util.enforceNoInstance(Util.class);
    }

    /**
     * <p>Repeats a method a specified amount of times.</p>
     * <p>The supplied integer is the index.</p>
     * <p>Index starts at 1.</p>
     */
    public static void repeat(int amount, IntConsumer action) {
        for (int index = 1; index <= amount; index++) {
            action.accept(index);
        }
    }

    public static <Z> void ifNotNull(@Nullable Z thing, Consumer<Z> function) {
        if (thing != null) {
            function.accept(thing);
        }
    }

    public static <Z, Q> Optional<Q> ifNotNullReturn(@Nullable Z thing, Function<@NotNull Z, @Nullable Q> function) {
        if (thing != null) {
            return Optional.ofNullable(function.apply(thing));
        }
        return Optional.empty();
    }

    public static <T> void requireNull(@Nullable T thing, String message) {
        if (thing != null) throw new NotNullException(message);
    }

    public static <T> T create(T thing, Consumer<T> consumer) {
        consumer.accept(thing);
        return thing;
    }

    @Deprecated
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

    public static void enforceNoInstance(Class<?> clazz) {
        throw new UnsupportedOperationException("Can't create instance of " + clazz.getName());
    }

    public static void enforceNoInstance() {
        throw new UnsupportedOperationException("Attempted to create instance of a class that does not allow instances");
    }

    public static <T> @NotNull T orElse(@Nullable T input, @NotNull T orElse) {
        return input != null ? input : orElse;
    }
}
