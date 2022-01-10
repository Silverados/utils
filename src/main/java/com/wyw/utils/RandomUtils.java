package com.wyw.utils;

import java.util.Map;
import java.util.Random;

public class RandomUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";
    public static final String BAD_BOUND = "bound must be positive";

    private RandomUtils() {
        throw new UnsupportedOperationException(INVALID_CONSTRUCT);
    }

    // lazy-load
    private static final class RandomNumberGeneratorHolder {
        static final Random randomNumberGenerator = new Random();
    }


    /**
     * like Math.random()
     * @return a random double value
     */
    public static double nextDouble() {
        return RandomUtils.RandomNumberGeneratorHolder.randomNumberGenerator.nextDouble();
    }

    /**
     * @return a random int value, may be negative
     */
    public static int nextInt() {
        return RandomNumberGeneratorHolder.randomNumberGenerator.nextInt();
    }

    /**
     * @param bound - the upper bound(exclusive), must be >= 0.
     * @return a random int value in [0, bound)
     * @throws IllegalArgumentException - if bound is not positive
     */
    public static int nextInt(int bound) {
        if (bound < 0) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        return RandomNumberGeneratorHolder.randomNumberGenerator.nextInt(bound);
    }

    /**
     * @return a random value in [origin, bound) or [bound, origin)
     */
    public static int nextInt(int origin, int bound) {
        if (origin < 0 || bound < 0) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        if (origin == bound) {
            return origin;
        }
        if (origin < bound) {
            return nextInt(bound - origin) + origin;
        } else {
            return  nextInt(origin - bound) + bound;
        }
    }

    /**
     * @param weights - each key map to a weight, such as: {1:2, 2:3, 3:5}
     * @param <T> - generic class
     * @return a random key by weight
     */
    public static <T> T randomByWeight(Map<T, Integer> weights) {
        if (weights.isEmpty()) {
            return null;
        }

        int sum = 0;
        for (Integer value : weights.values()) {
            sum += value;
        }

        int rand = nextInt(sum);
        int factor = 0;
        for (Map.Entry<T, Integer> entry : weights.entrySet()) {
            factor += entry.getValue();
            if (factor > rand) {
                return entry.getKey();
            }
        }
        return null;
    }
}
