package embinmc.lib.util.function.preset;

import java.util.function.Supplier;

public interface Suppliers {
    Supplier<Boolean> ALWAYS = () -> true;
    Supplier<Boolean> NEVER = () -> false;
    Supplier<Boolean> TRUE = Suppliers.ALWAYS;
    Supplier<Boolean> FALSE = Suppliers.NEVER;

    Supplier<Integer> INT_ZERO = () -> 0;
    Supplier<Integer> INT_MAX = () -> Integer.MAX_VALUE;
    Supplier<Integer> INT_MIN = () -> Integer.MIN_VALUE;

    Supplier<Double> DOUBLE_ZERO = () -> 0.0D;
    Supplier<Double> DOUBLE_MAX = () -> Double.MAX_VALUE;
    Supplier<Double> DOUBLE_MIN = () -> Double.MIN_VALUE;

    Supplier<Float> FLOAT_ZERO = () -> 0.0f;
    Supplier<Float> FLOAT_MAX = () -> Float.MAX_VALUE;
    Supplier<Float> FLOAT_MIN = () -> Float.MIN_VALUE;

    Supplier<Byte> BYTE_ZERO = () -> (byte) 0;
    Supplier<Byte> BYTE_MAX = () -> Byte.MAX_VALUE;
    Supplier<Byte> BYTE_MIN = () -> Byte.MIN_VALUE;

    Supplier<Short> SHORT_ZERO = () -> (short) 0;
    Supplier<Short> SHORT_MAX = () -> Short.MAX_VALUE;
    Supplier<Short> SHORT_MIN = () -> Short.MIN_VALUE;

    Supplier<Long> LONG_ZERO = () -> 0L;
    Supplier<Long> LONG_MAX = () -> Long.MAX_VALUE;
    Supplier<Long> LONG_MIN = () -> Long.MIN_VALUE;
}
