package com.wyw.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void keep2Decimal() {
        assertEquals(StringUtils.keep2Decimal(1.5523), "1.55");
        assertEquals(StringUtils.keep2Decimal(1.5553), "1.56");
        assertEquals(StringUtils.keep2Decimal(1.5583), "1.56");
    }

    @Test
    void keepNDecimalTrim() {
        assertEquals(StringUtils.keepNDecimalFloor(1.5523, 2), "1.55");
        assertEquals(StringUtils.keepNDecimalFloor(1.5553, 2), "1.55");
        assertEquals(StringUtils.keepNDecimalFloor(1.5583, 2), "1.55");
    }

    @Test
    void formatStringWithColor() {
        for (StringUtils.Color color : StringUtils.Color.values()) {
            System.out.println(StringUtils.formatWithColor("hello world!", color));
        }
    }
}