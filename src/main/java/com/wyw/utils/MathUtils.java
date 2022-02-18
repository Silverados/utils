package com.wyw.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtils {

    public static final String INVALID_PARAM = "Param must be positive!";
    public static final String INVALID_ARRAY = "Array is empty!";

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

    // avg
    // ----------------------------------------------------------------------
    public static double avg(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return sum(arr) / arr.length;
    }

    // avg
    // ----------------------------------------------------------------------
    public static double sum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        double sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    // max
    // ----------------------------------------------------------------------
    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("");
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    // min
    // ----------------------------------------------------------------------
    public static int min(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("");
        }

        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    // distance
    // ----------------------------------------------------------------------
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
