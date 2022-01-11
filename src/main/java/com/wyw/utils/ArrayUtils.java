package com.wyw.utils;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * like org.commons.lang3.ArrayUtils
 */
public class ArrayUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";

    public static final int INDEX_NOT_FOUND = -1;

    private ArrayUtils() {
        throw new UnsupportedOperationException(INVALID_CONSTRUCT);
    }

    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }

        return Array.getLength(array);
    }

    // isEmpty
    //-----------------------------------------------------------------------
    public static boolean isEmpty(final int[] arr) {
        return getLength(arr) == 0;
    }
    public static boolean isEmpty(final double[] arr) {
        return getLength(arr) == 0;
    }
    public static boolean isEmpty(final float[] arr) {
        return getLength(arr) == 0;
    }
    public static boolean isEmpty(final byte[] arr) {
        return getLength(arr) == 0;
    }
    public static boolean isEmpty(final char[] arr) {
        return getLength(arr) == 0;
    }
    public static boolean isEmpty(final Object[] arr) {
        return getLength(arr) == 0;
    }

    // IndexOf
    //-----------------------------------------------------------------------
    public static int indexOf(final int[] arr, final int value) {
        return indexOf(arr, value, 0);
    }

    public static int indexOf(final int[] arr, final int value, int startIndex) {
        if (isEmpty(arr)) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = startIndex; i < arr.length; i++) {
            if (value == arr[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }


    // LastIndexOf
    //-----------------------------------------------------------------------
    public static int lastIndexOf(final int[] arr, final int value) {
        return lastIndexOf(arr, value, 0);
    }

    public static int lastIndexOf(final int[] arr, final int value, int startIndex) {
        if (isEmpty(arr)) {
            return INDEX_NOT_FOUND;
        }

        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        for (int i = startIndex; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    // shuffle
    //-----------------------------------------------------------------------
    public static void shuffle(final int[] arr) {
        Random random = new Random();
        for (int i = arr.length; i > 1; i--) {
            swap(arr, i - 1, random.nextInt(i));
        }
    }

    // swap
    //-----------------------------------------------------------------------
    public static void swap(final int[] arr, final int a, final int b) {
        int value = arr[a];
        arr[a] = arr[b];
        arr[a] = value;
    }


    public static void swap(final int[] arr, int a, int b, int len) {
        if (isEmpty(arr) || a < 0 || b < 0 || a >= arr.length || b >= arr.length) {
            return;
        }

        len = Math.min(Math.min(len, arr.length - a), arr.length - b);
        for (int i = 0; i < len; i++, a++, b++) {
            final int aux = arr[a];
            arr[a] = arr[b];
            arr[b] = aux;
        }
    }

    // min
    //-----------------------------------------------------------------------
    public static int min(int[] arr) {
        if (isEmpty(arr)) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(arr[i], min);
        }
        return min;
    }

    // max
    //-----------------------------------------------------------------------
    public static int max(int[] arr) {
        if (isEmpty(arr)) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }
}
