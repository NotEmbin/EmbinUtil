package embinmc.lib.util.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;

import java.util.Arrays;
import java.util.stream.Stream;

@SuppressWarnings({"unused"})
public interface SerializableEnum<T> {
    String asString();
    Codec<T> codec();

    default boolean isString(String string) {
        return this.asString().equals(string);
    }

    static <T extends SerializableEnum<T>> EnumCodec<T> getCodec(String name, T[] values) {
        return new EnumCodec<>(name, values);
    }

    static <T extends SerializableEnum<T>> EnumCodec<T> getCodec(T[] values) {
        return SerializableEnum.getCodec(getNameOrThrow(values), values);
    }

    static <T extends Enum<T> & SerializableEnum<T>> EnumCodec<T> getCodec(Class<T> clazz) {
        return new EnumCodec<>(clazz.getSimpleName(), clazz.getEnumConstants());
    }

    private static <T> String getNameOrThrow(final T[] values) {
        return Arrays.stream(values).findAny().orElseThrow().getClass().getSimpleName();
    }

    static <T> Keyable asKeyable(SerializableEnum<T>[] values) {
        return new Keyable() {
            @Override
            public <D> Stream<D> keys(DynamicOps<D> ops) {
                return Arrays.stream(values).map(SerializableEnum::asString).map(ops::createString);
            }
        };
    }
}
