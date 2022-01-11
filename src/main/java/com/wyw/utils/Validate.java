package com.wyw.utils;

import java.util.Objects;

/**
 * like org.apache.commons.lang3.Validate
 */
public class Validate {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";
    public static final String DEFAULT_NOT_NAN_EX_MESSAGE =
            "The validated value is not a number";
    public static final String DEFAULT_FINITE_EX_MESSAGE =
            "The value is invalid: %f";
    public static final String DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE =
            "The value %s is not in the specified exclusive range of %s to %s";
    public static final String DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE =
            "The value %s is not in the specified inclusive range of %s to %s";
    public static final String DEFAULT_MATCHES_PATTERN_EX = "The string %s does not match the pattern %s";
    public static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";
    public static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";
    public static final String DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE =
            "The validated array contains null element at index: %d";
    public static final String DEFAULT_NO_NULL_ELEMENTS_COLLECTION_EX_MESSAGE =
            "The validated collection contains null element at index: %d";
    public static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";
    public static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "The validated array is empty";
    public static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE =
            "The validated character sequence is empty";
    public static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "The validated collection is empty";
    public static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "The validated map is empty";
    public static final String DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE = "The validated array index is invalid: %d";
    public static final String DEFAULT_VALID_INDEX_CHAR_SEQUENCE_EX_MESSAGE =
            "The validated character sequence index is invalid: %d";
    public static final String DEFAULT_VALID_INDEX_COLLECTION_EX_MESSAGE =
            "The validated collection index is invalid: %d";
    public static final String DEFAULT_VALID_STATE_EX_MESSAGE = "The validated state is false";
    public static final String DEFAULT_IS_ASSIGNABLE_EX_MESSAGE = "Cannot assign a %s to a %s";
    public static final String DEFAULT_IS_INSTANCE_OF_EX_MESSAGE = "Expected type: %s, actual: %s";


    private Validate() {
        throw new IllegalArgumentException(INVALID_CONSTRUCT);
    }

    public static void isTrue(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> T notNull(final T object) {
        return notNull(object, DEFAULT_IS_NULL_EX_MESSAGE);
    }

    public static <T> T notNull(T object, String message, final Object... values) {
        return Objects.requireNonNull(object, () -> String.format(message, values));
    }

    public static <T> T[] validIndex(final T[] array, final int index, final String message, final Object... values) {
        notNull(array);
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException(String.format(message, values));
        }
        return array;
    }

    public static <T> T[] validIndex(final T[] array, final int index) {
        return validIndex(array, index, DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE, index);
    }

    public static void validState(final boolean expression, final String message, final Object... values) {
        if (!expression) {
            throw new IllegalStateException(String.format(message, values));
        }
    }

    public static void validState(final boolean expression) {
        validState(expression, DEFAULT_VALID_STATE_EX_MESSAGE);
    }

    public static void inclusiveBetween(final double start, final double end, final double value, final String message) {
        if (value < start || value > end) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void inclusiveBetween(final double start, final double end, final double value) {
        inclusiveBetween(start, end, value, DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE);
    }
}