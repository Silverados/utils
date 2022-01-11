package com.wyw.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * like org.commons.lang3.ArrayUtils
 */
public class ArrayUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

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

    // toArray
    //-----------------------------------------------------------------------
    @SafeVarargs
    public static <T> T[] toArray(T... items) {
        return items;
    }

    public static int[] toArray(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return EMPTY_INT_ARRAY;
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static int[] toArray(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return EMPTY_INT_ARRAY;
        }

        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // isSameLength
    //-----------------------------------------------------------------------
    public static boolean isSameLength(Object[] a, Object[] b) {
        return getLength(a) == getLength(b);
    }

    public static boolean isSameLength(int[] a, int[] b) {
        return getLength(a) == getLength(b);
    }

    // isEquals
    //-----------------------------------------------------------------------
    public static boolean isEquals(int[] a, int[] b) {
        if (!isSameLength(a, b)) {
            return false;
        }

        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

}
