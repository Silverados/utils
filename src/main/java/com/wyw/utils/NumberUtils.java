package com.wyw.utils;

public class NumberUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";

    private NumberUtils() {
        throw new UnsupportedOperationException(INVALID_CONSTRUCT);
    }

    public static int min(final int a, final int b, final int c) {
        return Math.min(a, Math.min(b, c));
    }
}
