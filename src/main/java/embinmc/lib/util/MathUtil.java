package embinmc.lib.util;

import com.mojang.datafixers.DataFixUtils;

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
     * @param minInclusive Inclusive
     * @param maxInclusive Inclusive
     */
    public static int randomInt(int minInclusive, int maxInclusive) {
        return MathUtil.RANDOM.nextInt(minInclusive, maxInclusive + 1);
    }

    /**
     * @param minInclusive Inclusive
     * @param maxExclusive Exclusive
     */
    public static float randomFloat(float minInclusive, float maxExclusive) {
        return MathUtil.RANDOM.nextFloat(minInclusive, maxExclusive);
    }

    public static double lerp(double amount, double min, double max) {
        return ((max - min) * amount) + min;
    }

    public static double lerpOf(double input, double min, double max) {
        return (input - min) / (max - min);
    }

    public static boolean isPositive(int input) {
        return input > 0;
    }

    public static boolean isNegative(int input) {
        return input < 0;
    }

    public static boolean isPositive(double input) {
        return input > 0.0D;
    }

    public static boolean isNegative(double input) {
        return input < 0.0D;
    }

    public static boolean isPositive(float input) {
        return input > 0f;
    }

    public static boolean isNegative(float input) {
        return input < 0f;
    }

    public static int modulo(int input, int mod) {
        int modded = input % mod;
        if (modded < 0) modded = modded + mod;
        return modded;
    }

    public static int smallestEncompassingPowerOf2(int input) {
        return DataFixUtils.smallestEncompassingPowerOfTwo(input);
    }

    public static int floor(final float input) {
        int i = (int)input;
        return input < (float)i ? i - 1 : i;
    }

    public static int floor(final double input) {
        int i = (int)input;
        return input < (double)i ? i - 1 : i;
    }

    public static float sin(float input) {
        return MathUtil.SINE_TABLE[(int) (input * 10430.378f) & 65535];
    }
}
