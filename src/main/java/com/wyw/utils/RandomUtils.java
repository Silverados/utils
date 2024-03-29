package com.wyw.utils;

import java.util.*;

public class RandomUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";
    public static final String BAD_BOUND = "bound must be positive";

    private RandomUtils() {
        throw new UnsupportedOperationException(INVALID_CONSTRUCT);
    }

    /**
     * like Math.random()
     *
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

    public static int[] nextIntArray(int arrayLength, int bound) {
        int[] res = new int[arrayLength];
        for (int i = 0; i < res.length; i++) {
            res[i] = nextInt(bound);
        }
        return res;
    }

    public static int[] nextIntArray(int arrayLength, int origin, int bound) {
        int[] res = new int[arrayLength];
        for (int i = 0; i < res.length; i++) {
            res[i] = nextInt(origin, bound);
        }
        return res;
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
        if (origin == bound) {
            return origin;
        }
        if (origin < bound) {
            return nextInt(bound - origin) + origin;
        } else {
            return nextInt(origin - bound) + bound;
        }
    }

    /**
     * @param weights - each key map to a weight, such as: {1:2, 2:3, 3:5}
     * @param <T>     - generic class
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

    /**
     * 等概率返回一个元素
     *
     * @param list
     * @return
     */
    public static <T> T randomSameWeight(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }

        int size = list.size();
        return list.get(nextInt(size));
    }

    /**
     * 等概率返回若个元素
     * 会对原list发生修改
     * @param list
     * @param count 数量
     * @return
     */
    public static <T> List<T> randomSameWeight2(List<T> list, int count) {
        if (list.isEmpty()) {
            return new ArrayList<>(0);
        }

        if (count >= list.size()) {
            return new ArrayList<>(list);
        }

        Collections.shuffle(list);
        return list.subList(0, count);
    }

    /**
     * 等概率返回若个元素
     * 可以确保不会对原list发生修改
     * @param list
     * @param count 数量
     * @return
     */
    public static <T> List<T> randomSameWeight(List<T> list, int count) {
        if (list.isEmpty()) {
            return new ArrayList<>(0);
        }

        if (count >= list.size()) {
            return new ArrayList<>(list);
        }

        var temp = new ArrayList<>(list);
        Collections.shuffle(temp);
        var res = new ArrayList<T>(count);
        for (int i = 0; i < count; i++) {
            res.add(temp.get(i));
        }

        return res;
    }

    // lazy-load
    private static final class RandomNumberGeneratorHolder {
        static final Random randomNumberGenerator = new Random();
    }
}
