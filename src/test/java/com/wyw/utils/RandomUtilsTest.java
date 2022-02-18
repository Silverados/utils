package com.wyw.utils;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RandomUtilsTest {

    @Test
    void nextDouble() {
    }

    @Test
    void nextInt() {
    }

    @RepeatedTest(10000)
    void testNextInt() {
        int num = RandomUtils.nextInt(10);
        assertTrue(num >= 0 && num < 10);

        assertThrows(IllegalArgumentException.class, () -> {
            RandomUtils.nextInt(-5);
        });
    }

    @RepeatedTest(10000)
    void testNextInt1() {
        int num = RandomUtils.nextInt(10, 100);
        assertTrue(num >= 10 && num < 100);

        num = RandomUtils.nextInt(100, 100);
        assertEquals(100, num);

        num = RandomUtils.nextInt(100, 10);
        assertTrue(num >= 10 && num < 100);

        num = RandomUtils.nextInt(-100, 0);
        assertTrue(num >= -100 && num < 0);
    }

    @Test
    void randomByWeight() {
        Map<Integer, Integer> map = Map.of(1, 3, 2, 2, 3, 5);
        int[] arr = new int[3];
        for (int i = 0; i < 100000000; i++) {
            Integer num = RandomUtils.randomByWeight(map);
            arr[num - 1]++;
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void nextIntArray() {
//        System.out.println(Arrays.toString(RandomUtils.nextIntArray(100, 100)));
        System.out.println(Arrays.toString(RandomUtils.nextIntArray(20, -10, 1)));
    }
}