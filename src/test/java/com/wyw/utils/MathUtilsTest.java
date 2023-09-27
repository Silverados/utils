package com.wyw.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void cellDiv() {
        assertEquals(MathUtils.ceilDiv(4, 3), 2);
        assertEquals(MathUtils.ceilDiv(5, 3), 2);
        assertEquals(MathUtils.ceilDiv(6, 3), 2);
        assertEquals(MathUtils.ceilDiv(7, 3), 3);
    }
}