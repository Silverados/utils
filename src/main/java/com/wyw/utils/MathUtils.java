package com.wyw.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtils {

    public static final String INVALID_PARAM = "Param must be positive!";

    /**
     * 3/3 --> 1
     * 4/3 --> 2
     * 6/3 --> 2
     * 7/3 --> 3
     * @param x
     * @param y
     * @return
     */
    public static int cellDiv(int x, int y) {
        if (x < 0 || y <= 0) {
            throw new IllegalArgumentException(INVALID_PARAM);
        }
        return x % y == 0 ? x / y : x / y + 1;
    }

    // abs
    // ----------------------------------------------------------------------
    public static int absMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = absGreater(arr[i], arr[i - 1]) ? arr[i] : arr[i - 1];
        }
        return max;
    }

    public static int absMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = absLess(arr[i], arr[i - 1]) ? arr[i] : arr[i - 1];
        }
        return min;
    }

    public static int absCompareTo(int a, int b) {
        int res;
        res = Math.abs(a) > Math.abs(b) ? 1 : -1;
        res = Math.abs(a) == Math.abs(b) ? 0 : res;
        return res;
    }

    public static boolean absGreater(int a, int b) {
        return absCompareTo(a, b) > 0;
    }

    public static boolean absLess(int a, int b) {
        return absCompareTo(a, b) < 0;
    }

    public static boolean absEquals(int a, int b) {
        return absCompareTo(a, b) == 0;
    }

    public static int abs(int value) {
        return value < 0 ? -value : value;
    }


    // gcd
    // ----------------------------------------------------------------------
    public static int gcd(int a, int b) {
        if (a < 0 || b < 0) {
            throw new ArithmeticException();
        }

        if (a == 0 || b == 0) {
            return Math.abs(a - b);
        }

        return a % b == 0 ? b : gcd(b, a % b);
    }
}
