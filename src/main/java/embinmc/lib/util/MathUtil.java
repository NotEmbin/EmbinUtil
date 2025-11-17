package embinmc.lib.util;

import java.util.Random;
import java.util.random.RandomGenerator;

@SuppressWarnings({"unused"})
public final class MathUtil {
    public static final Random RANDOM = Random.from(RandomGenerator.getDefault());
    private static final float[] SINE_TABLE = Util.create(new float[65536], table -> {
        for (int i = 0; i < table.length; i++) {
            table[i] = (float) Math.sin(i * Math.PI * 2 / 65536);
        }
    });

    public static boolean isIntBetween(int num, int min, int max) {
        return (num >= min) && (num <= max);
    }

    public static int powInt(int a, int b) {
        final int[] base = {1};
        Util.repeat(b, _ -> base[0] = base[0] * a);
        return base[0];
    }

    /**
     * @param min Inclusive
     * @param max Inclusive
     */
    public static int randomInt(int min, int max) {
        return MathUtil.RANDOM.nextInt(min, max + 1);
    }

    public static double lerp(double amount, double min, double max) {
        return ((max - min) * amount) + min;
    }

    public static double lerpOf(double value, double min, double max) {
        return (value - min) / (max - min);
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }

    public static boolean isNegative(int value) {
        return value < 0;
    }

    public static boolean isPositive(double value) {
        return value > 0.0D;
    }

    public static boolean isNegative(double value) {
        return value < 0.0D;
    }

    public static int modulo(int value, int mod) {
        int modded = value % mod;
        if (modded < 0) modded = modded + mod;
        return modded;
    }

    // http://graphics.stanford.edu/~seander/bithacks.html#RoundUpPowerOf2
    public static int smallestEncompassingPowerOf2(int value) {
        int v = value - 1;
        v |= v >> 1;
        v |= v >> 2;
        v |= v >> 4;
        v |= v >> 8;
        v |= v >> 16;
        return v + 1;
    }

    public static int floor(final float value) {
        int i = (int)value;
        return value < (float)i ? i - 1 : i;
    }

    public static int floor(final double value) {
        int i = (int)value;
        return value < (double)i ? i - 1 : i;
    }

    public static float sin(float value) {
        return MathUtil.SINE_TABLE[(int) (value * 10430.378f) & 65535];
    }
}
