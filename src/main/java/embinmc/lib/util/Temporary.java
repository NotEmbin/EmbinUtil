package embinmc.lib.util;

import embinmc.lib.util.annotation.NotNull;
import embinmc.lib.util.annotation.Nullable;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@link Optional}-like class.
 */
@SuppressWarnings({"UnusedReturnValue", "unused", "Convert2Diamond"})
public final class Temporary<T> {
    @Nullable private T value;

    private Temporary(@Nullable T value) {
        this.value = value;
    }

    public static <T> Temporary<T> empty() {
        return new Temporary<T>(null);
    }

    public static <T> Temporary<T> with(@NotNull T value) {
        return new Temporary<T>(Objects.requireNonNull(value));
    }

    public static <T> Temporary<T> withNullable(@Nullable T value) {
        return new Temporary<T>(value);
    }

    public Temporary<T> clear() {
        this.value = null;
        return this;
    }

    public Temporary<T> setNewValue(@NotNull T newValue) {
        this.value = newValue;
        return this;
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public void ifPresent(Consumer<? super T> action) {
        if (this.isPresent()) {
            action.accept(this.value);
        }
    }

    public void ifPresentThenClear(Consumer<? super T> action) {
        if (this.isPresent()) {
            action.accept(this.value);
            this.clear();
        }
    }

    /**
     * Clears the value being held after use.
     */
    public void ifPresentOrElse(Consumer<? super T> action, Runnable elseAction) {
        if (this.isPresent()) {
            action.accept(this.value);
            this.clear();
        } else {
            elseAction.run();
        }
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(this.value);
    }

    /**
     * Identical to {@link Temporary#getValue()}
     */
    public Optional<T> toOptional() {
        return this.getValue();
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        return this.getValue().map(mapper);
    }

    public @Nullable T orElseNull() {
        return this.value;
    }

    public T orElseThrow() {
        if (this.value == null) throw new NoSuchElementException("Temporary holds no value");
        return Objects.requireNonNull(this.value);
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return this.getValue().orElseGet(supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Temporary<?> temporary) {
            return Objects.equals(this.value, temporary.value);
        }
        return false;
    }
}
