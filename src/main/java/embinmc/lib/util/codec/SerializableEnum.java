package embinmc.lib.util.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.util.Arrays;

@SuppressWarnings({"unused"})
public interface SerializableEnum<T extends Enum<T>> {
    String asString();
    Codec<T> codec();

    default boolean isString(String string) {
        return this.asString().equals(string);
    }

    static <T extends Enum<T> & SerializableEnum<T>> Codec<T> getCodec(T[] values) {
        if (values.length == 0) throw new IllegalStateException("Can't serialize enum with no values!");
        return Codec.STRING.comapFlatMap(s -> validate(s, values), T::asString);
    }

    static <T extends Enum<T> & SerializableEnum<T>> Codec<T> getCodec(Class<T> clazz) {
        return SerializableEnum.getCodec(clazz.getEnumConstants());
    }

    private static <T extends Enum<T> & SerializableEnum<T>> DataResult<T> validate(String value, final T[] enumConstants) {
        if (enumConstants.length == 0) return DataResult.error(() -> "Can't serialize enum with no values!");
        for (T enumValue : enumConstants) {
            if (enumValue.isString(value)) return DataResult.success(enumValue);
        }
        return DataResult.error(() -> "\"%s\" is not a valid %s!".formatted(value, getNameOrThrow(enumConstants)));
    }

    private static <T> String getNameOrThrow(final T[] values) {
        return Arrays.stream(values).findAny().orElseThrow().getClass().getSimpleName();
    }
}
