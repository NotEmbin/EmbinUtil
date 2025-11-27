package embinmc.lib.util.codec;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings({"unused"})
public final class CodecUtil {
    public static final Codec<Empty> EMPTY = Codec.unit(Empty.INSTANCE);
    public static Codec<Integer> POSITIVE_INT = rangedInt(1, Integer.MAX_VALUE, v -> "Value \"" + v + "\" must be positive");
    public static Codec<Integer> NON_NEGATIVE_INT = rangedInt(0, Integer.MAX_VALUE, v -> "Value \"" + v + "\" cannot be negative");

    public static Codec<Integer> rangedInt(int min, int max, Function<Integer, String> failMessage) {
        return Codec.INT.validate(
                value -> value >= min && value <= max ?
                        DataResult.success(value) :
                        DataResult.error(() -> failMessage.apply(value))
        );
    }

    public static Codec<Integer> rangedInt(int min, int max) {
        return rangedInt(min, max, value -> "Value must be within range [" + min + ";" + max + "], got: " + value);
    }

    public static <T> Codec<List<T>> listOrSingle(Codec<T> entryCodec) {
        return Codec.either(entryCodec.listOf(), entryCodec).xmap(
            either -> either.map(list -> list, List::of),
            list -> list.size() == 1 ? Either.right(list.getFirst()) : Either.left(list)
        );
    }

    public static <T> MapCodec<T> withEitherKey(Codec<T> codec, String mainKey, String secondaryKey) {
        return Codec.mapEither(codec.fieldOf(secondaryKey), codec.fieldOf(mainKey)).xmap(
                either -> either.map(t -> t, t2 -> t2),
                Either::right
        );
    }

    public static <T> MapCodec<T> withEitherOptionalKey(Codec<T> codec, String mainKey, String secondaryKey, T defaultValue) {
        return CodecUtil.withEitherKey(codec, mainKey, secondaryKey).orElseGet(() -> defaultValue);
    }

    public static <T> MapCodec<Optional<T>> withEitherOptionalKey(Codec<T> codec, String mainKey, String secondaryKey) {
        return CodecUtil.withEitherKey(codec, mainKey, secondaryKey)
                .flatXmap(v -> DataResult.success(Optional.of(v)), CodecUtil::validateExistence)
                .orElseGet(Optional::empty);
    }

    private static <T> DataResult<T> validateExistence(Optional<T> value) {
        if (value.isPresent()) return DataResult.success(value.orElseThrow());
        return DataResult.error(() -> "No value");
    }

    public enum Empty {
        INSTANCE
    }
}
