package com.wyw.utils;

import lombok.Getter;
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
        int[] arr = new int[]{1, 2, 3, 5, 4};
        ArrayUtils.swap(arr, 3, 4);
        assertTrue(ArrayUtils.isEquals(new int[]{1, 2, 3, 4, 5}, arr));
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

    @Test
    void containsAllDigits() {
//        int[] arr = new int[] {1, 2, 3, 4, 5};
        int[] arr = new int[] {1, 2, 2, 4, 5};
        for (int i = 0; i < 10000; i++) {
            ArrayUtils.shuffle(arr);
//            assertTrue(ArrayUtils.containsAllDigits(arr));
            assertFalse(ArrayUtils.containsAllDigits(arr));
        }
    }

    @Test
    void testConcat() {
        String[] arr = new String[] {"a", "b", "c"};
        assertEquals(ArrayUtils.concat(arr, "_"), "a_b_c");
    }

    @Test
    void test() {
        String[] arr = {"10", "10,10,123"};
        for (String s : arr) {
            System.out.println(Arrays.toString(s.split(",")));
        }
    }
}