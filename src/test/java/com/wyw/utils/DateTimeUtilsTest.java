package com.wyw.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
    void weekOfWeekBaseYear() {
        assertEquals(52, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2021, 12, 31, 0, 0, 0)));
        assertEquals(52, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 1, 0, 0, 0)));
        assertEquals(52, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 2, 0, 0, 0)));
        assertEquals(1, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 3, 0, 0, 0)));
        assertEquals(1, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 4, 0, 0, 0)));
        assertEquals(1, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 5, 0, 0, 0)));
        assertEquals(1, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 6, 0, 0, 0)));
        assertEquals(1, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 7, 0, 0, 0)));
        assertEquals(1, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 8, 0, 0, 0)));
        assertEquals(1, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 9, 0, 0, 0)));
        assertEquals(2, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 10, 0, 0, 0)));
        assertEquals(2, DateTimeUtils.weekOfWeekBaseYear(LocalDateTime.of(2022, 1, 11, 0, 0, 0)));

        assertEquals(2021, DateTimeUtils.weekBasedYear(LocalDateTime.of(2021, 12, 31, 0, 0, 0)));
        assertEquals(2021, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 1, 0, 0, 0)));
        assertEquals(2021, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 2, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 3, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 4, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 5, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 6, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 7, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 8, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 9, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 10, 0, 0, 0)));
        assertEquals(2022, DateTimeUtils.weekBasedYear(LocalDateTime.of(2022, 1, 11, 0, 0, 0)));
    }

    @Test
    void isSameWeek() {
        // 同一周的情况
        assertTrue(DateTimeUtils.isSameWeek(toMillis("2024-01-15T00:00:00"), toMillis("2024-01-21T23:59:59"))); // 同一周的不同日期
        assertTrue(DateTimeUtils.isSameWeek(toMillis("2024-01-15T00:00:00"), toMillis("2024-01-15T23:59:59"))); // 同一周的同一天

        // 不同周的情况
        assertFalse(DateTimeUtils.isSameWeek(toMillis("2024-01-15T00:00:00"), toMillis("2024-01-29T23:59:59"))); // 不同周的不同日期
        assertFalse(DateTimeUtils.isSameWeek(toMillis("2024-01-15T00:00:00"), toMillis("2024-01-30T23:59:59"))); // 不同周的同一天

        // 跨年的情况
        assertFalse(DateTimeUtils.isSameWeek(toMillis("2023-12-31T00:00:00"), toMillis("2024-01-01T00:00:00"))); // 跨年的不同周
        assertTrue(DateTimeUtils.isSameWeek(toMillis("2022-12-31T00:00:00"), toMillis("2023-01-01T23:59:59"))); // 跨年的同一周

        // 不同年 非跨年的情况
        assertFalse(DateTimeUtils.isSameWeek(toMillis("2024-01-15T00:00:00"), toMillis("2025-01-15T23:59:59")));

        // 无效输入的情况
        assertFalse(DateTimeUtils.isSameWeek(0L, toMillis("2024-01-22T23:59:59"))); // 无效的cmpTimeMillis
        assertFalse(DateTimeUtils.isSameWeek(toMillis("2024-01-15T00:00:00"), 0L)); // 无效的nowTimeMillis
        assertTrue(DateTimeUtils.isSameWeek(0L, 0L)); // 无效的cmpTimeMillis和nowTimeMillis
    }

    private static long toMillis(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    @Test
    void durationDays() {
        assertEquals(DateTimeUtils.durationDays(LocalDate.of(2022, 2, 15), LocalDate.of(2022, 2, 10)), -5);
    }

    @Test
    void week() {
        System.out.println(DateTimeUtils.weekDisplayName(LocalDate.now()));
    }

    @Test
    void getMilliSecondInDuration() {
        long currentTimeMillis = System.currentTimeMillis();
//        long currentTimeMillis = 1699239241591L;
        System.out.println(DateTimeUtils.getMilliSecondFixedHour(currentTimeMillis, Arrays.asList(3, 6, 10, 12, 17, 0)));
    }

    @Test
    void testGetMilliSecondInDuration() {
//        long currentTimeMillis = System.currentTimeMillis();
//        long currentTimeMillis = 1699239241591L;

        int[] duration = {9, 15, 18, 20, 22, 0};
//        System.out.println(DateTimeUtils.getMilliSecondInDuration(currentTimeMillis, duration, 10000));

        var m = 60 * 1000;
        var hour = 60 * m;
        int interval = 30 * m;
        LocalDateTime now = LocalDateTime.now();
        assertEquals(9 * hour, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(0).withMinute(0).withSecond(0).withNano(0)), duration, interval));
        assertEquals(hour, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(8).withMinute(0).withSecond(0).withNano(0)), duration, interval));
        assertEquals(interval, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(10).withMinute(0).withSecond(0).withNano(0)), duration, interval));
        assertEquals(3 * hour, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(15).withMinute(0).withSecond(0).withNano(0)), duration, interval));
        assertEquals(hour, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(17).withMinute(0).withSecond(0).withNano(0)), duration, interval));
        assertEquals(2 * hour, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(20).withMinute(0).withSecond(0).withNano(0)), duration, interval));
        assertEquals(interval, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(23).withMinute(0).withSecond(0).withNano(0)), duration, interval));
        assertEquals(hour, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(21).withMinute(0).withSecond(0).withNano(0)), duration, interval));
        assertEquals(5 * m, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(8).withMinute(55).withSecond(0).withNano(0)), duration, interval));
        assertEquals(interval, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(14).withMinute(29).withSecond(0).withNano(0)), duration, interval));
        assertEquals(3 * hour + 1 * m, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(14).withMinute(59).withSecond(0).withNano(0)), duration, interval));
        assertEquals(1 * m, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(21).withMinute(59).withSecond(0).withNano(0)), duration, interval));
        assertEquals(9 * hour + 1*m, DateTimeUtils.getMilliSecondInDuration(DateTimeUtils.toMilliTimestamp(now.withHour(23).withMinute(59).withSecond(0).withNano(0)), duration, interval));
    }


}