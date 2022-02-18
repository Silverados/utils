package com.wyw.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void toTimestamp() {
    }

    @Test
    void testToTimestamp() {
        assertEquals(DateTimeUtils.toMilliTimestamp(LocalDateTime.parse("2022-01-07 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), 1641484800000L);
    }

    @Test
    void toLocalDateTime() {
        assertEquals(DateTimeUtils.toLocalDateTime("2022-02-02 11:12:23"), LocalDateTime.of(2022, 2, 2, 11, 12, 23));
    }

    @Test
    void currentTimeMillis() {
        System.out.println(DateTimeUtils.currentTimeMillis());
        System.out.println(DateTimeUtils.toMilliTimestamp(LocalDateTime.now()));
    }

    @Test
    void duration() {
    }

    @Test
    void getStartOfTodayTimestamp() {
        assertEquals(DateTimeUtils.toMilliTimestamp(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)),
                DateTimeUtils.getStartOfTodayTimestamp());
    }

    @Test
    void getStartOfTomorrowTimestamp() {
    }

    @Test
    void getStartOfYesterdayTimeStamp() {
    }

    @Test
    void testToLocalDateTime() {
        assertEquals(DateTimeUtils.toLocalDateTime(1643771543000L), LocalDateTime.of(2022, 2, 2, 11, 12, 23));
    }

    @Test
    void testToLocalDateTime1() {
        assertEquals(DateTimeUtils.toLocalDateTime(1643771543), LocalDateTime.of(2022, 2, 2, 11, 12, 23));
    }

    @Test
    void toFormatString() {
        assertEquals(DateTimeUtils.toFormatString(1643771543000L), "2022-02-02 11:12:23");
    }

    @Test
    void isSameDay() {
        assertTrue(DateTimeUtils.isSameDay(1643771543000L, 1643771583000L));
    }

    @Test
    void testIsSameDay() {
        assertTrue(DateTimeUtils.isSameDay(LocalDateTime.of(2022, 2, 1, 11, 11, 11, 102342),
                LocalDateTime.of(2022, 2, 1, 11, 11, 11, 2342332)));
    }

    @Test
    void weekOfYear() {
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 1, 0, 0, 0)), 52);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 2, 0, 0, 0)), 52);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 3, 0, 0, 0)), 1);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 4, 0, 0, 0)), 1);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 5, 0, 0, 0)), 1);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 6, 0, 0, 0)), 1);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 7, 0, 0, 0)), 1);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 8, 0, 0, 0)), 1);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 9, 0, 0, 0)), 1);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 10, 0, 0, 0)), 2);
        assertEquals(DateTimeUtils.weekOfYear(LocalDateTime.of(2022, 1, 11, 0, 0, 0)), 2);
    }
}