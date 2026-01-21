package embinmc.lib.util.codec;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import embinmc.lib.util.annotation.Nullable;

import java.util.function.Supplier;

public class EnumCodec<T extends SerializableEnum<T>> implements PrimitiveCodec<T> {
    protected final T[] values;
    protected final String name;

    EnumCodec(String name, T[] valueArray) {
        this.values = valueArray;
        this.name = name;
    }

    private DataResult<T> fromStringResult(String string) {
        if (this.values.length == 0) return DataResult.error(() -> "Can't serialize enum with no values!");
        for (T enumValue : this.values) {
            if (enumValue.isString(string)) return DataResult.success(enumValue);
        }
        return DataResult.error(() -> "\"%s\" is not a valid %s!".formatted(string, this.name));
    }

    @Override
    public <O> DataResult<T> read(DynamicOps<O> ops, O input) {
        return ops.getStringValue(input).flatMap(this::fromStringResult);
    }

    @Override
    public <O> O write(DynamicOps<O> ops, T value) {
        return ops.createString(value.asString());
    }

    @Nullable
    public T fromString(String string) {
        for (T enumValue : this.values) {
            if (enumValue.isString(string)) return enumValue;
        }
        return null;
    }

    public T fromString(String string, T defaultValue) {
        final T result = this.fromString(string);
        return result != null ? result : defaultValue;
    }

    public static <T extends SerializableEnum<T>> EnumCodec<T> of(String name, Supplier<T[]> values) {
        return new EnumCodec<>(name, values.get());
    }
}
