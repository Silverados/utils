package com.wyw.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {

    @Test
    void getLength() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void testIsEmpty() {
    }

    @Test
    void testIsEmpty1() {
    }

    @Test
    void testIsEmpty2() {
    }

    @Test
    void testIsEmpty3() {
    }

    @Test
    void testIsEmpty4() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void testIndexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void testLastIndexOf() {
    }

    @Test
    void shuffle() {
    }

    @Test
    void swap() {
    }

    @Test
    void testSwap() {
    }

    @Test
    void min() {
        assertEquals(ArrayUtils.min(new int[]{1, 2, 3, 4, 5, 6, 6, 4}), 1);
    }

    @Test
    void max() {
        assertEquals(ArrayUtils.max(new int[]{1, 2, 3, 4, 5, 6, 6, 4}), 6);
    }

    @Test
    void toArray() {
        assertTrue(ArrayUtils.isEquals(ArrayUtils.toArray(List.of(1, 2, 3, 4, 5)), new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testToArray() {
        assertTrue(ArrayUtils.isEquals(ArrayUtils.toArray(ArrayUtils.toArray(1, 2, 3, 4, 5)), new int[]{1, 2, 3, 4, 5}));
    }
}