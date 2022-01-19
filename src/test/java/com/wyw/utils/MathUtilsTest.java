package com.wyw.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void cellDiv() {
        assertEquals(MathUtils.cellDiv(4, 3), 2);
        assertEquals(MathUtils.cellDiv(5, 3), 2);
        assertEquals(MathUtils.cellDiv(6, 3), 2);
        assertEquals(MathUtils.cellDiv(7, 3), 3);
    }
}