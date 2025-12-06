package embinmc.lib.util.function.preset;

import embinmc.lib.util.function.ByteSupplier;
import embinmc.lib.util.function.FloatSupplier;
import embinmc.lib.util.function.ShortSupplier;

import java.util.function.*;

public interface PrimitiveSuppliers {
    BooleanSupplier ALWAYS = () -> true;
    BooleanSupplier NEVER = () -> false;
    BooleanSupplier TRUE = PrimitiveSuppliers.ALWAYS;
    BooleanSupplier FALSE = PrimitiveSuppliers.NEVER;

    IntSupplier INT_ZERO = () -> 0;
    IntSupplier INT_MAX = () -> Integer.MAX_VALUE;
    IntSupplier INT_MIN = () -> Integer.MIN_VALUE;

    DoubleSupplier DOUBLE_ZERO = () -> 0.0D;
    DoubleSupplier DOUBLE_MAX = () -> Double.MAX_VALUE;
    DoubleSupplier DOUBLE_MIN = () -> Double.MIN_VALUE;

    FloatSupplier FLOAT_ZERO = () -> 0.0f;
    FloatSupplier FLOAT_MAX = () -> Float.MAX_VALUE;
    FloatSupplier FLOAT_MIN = () -> Float.MIN_VALUE;

    ByteSupplier BYTE_ZERO = () -> (byte) 0;
    ByteSupplier BYTE_MAX = () -> Byte.MAX_VALUE;
    ByteSupplier BYTE_MIN = () -> Byte.MIN_VALUE;

    ShortSupplier SHORT_ZERO = () -> (short) 0;
    ShortSupplier SHORT_MAX = () -> Short.MAX_VALUE;
    ShortSupplier SHORT_MIN = () -> Short.MIN_VALUE;

    LongSupplier LONG_ZERO = () -> 0L;
    LongSupplier LONG_MAX = () -> Long.MAX_VALUE;
    LongSupplier LONG_MIN = () -> Long.MIN_VALUE;
}
