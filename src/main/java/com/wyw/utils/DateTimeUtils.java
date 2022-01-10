package com.wyw.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Timestamp -> second timestamp
 * MilliTimestamp -> millisecond(1/1000 second) timestamp (default)
 * NanoSecond -> 1/1000,000,000 second
 */
public class DateTimeUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_TIME;
    public static final ZoneOffset ZONE_OFFSET = ZoneOffset.of("+8");
    private DateTimeUtils() {
        throw new UnsupportedOperationException(INVALID_CONSTRUCT);
    }

    private static long toMilliTimestamp(LocalDateTime dateTime, ZoneOffset zoneOffset) {
        return dateTime.toInstant(zoneOffset).toEpochMilli();
    }

    /**
     * 返回时间戳 (ms)
     * @param dateTime - time need to convert
     * @return timestamp
     */
    public static long toMilliTimestamp(LocalDateTime dateTime) {
        return toMilliTimestamp(dateTime, ZONE_OFFSET);
    }

    /**
     * @param datetime a string value like: "2022-02-02 11:12:23"
     * @return a LocalDateTime format value
     */
    public static LocalDateTime toLocalDateTime(String datetime) {
        return LocalDateTime.parse(datetime, DATE_TIME_FORMATTER);
    }

    public static LocalDateTime toLocalDateTime(long milliTimestamp) {
        return LocalDateTime.ofEpochSecond(milliTimestamp / 1000, 0, ZONE_OFFSET);
    }

    /**
     * careful : 2147483647 (Integer.MAX_VALUE) -> 2038-01-19T11:14:07
     * @param timestamp unix timestamp
     * @return a formatted string like use default formatter
     */
    public static LocalDateTime toLocalDateTime(int timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZONE_OFFSET);
    }

    /**
     * @return milliseconds form 1970-01-01 00:00:00
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * @param millis last timestamp
     * @return duration between now and last timestamp
     */
    public static long duration(long millis) {
        return Math.abs(currentTimeMillis() - millis);
    }

    /**
     * @return  get start of today timestamp
     */
    public static long getStartOfTodayTimestamp() {
        return toMilliTimestamp(LocalDate.now().atStartOfDay());
    }

    /**
     * @return get start of tomorrow timestamp
     */
    public static long getStartOfTomorrowTimestamp() {
        return toMilliTimestamp(LocalDate.now().plusDays(1).atStartOfDay());
    }

    /**
     * @return get start of yesterday timestamp
     */
    public static long getStartOfYesterdayTimeStamp() {
        return toMilliTimestamp(LocalDate.now().minusDays(1).atStartOfDay());
    }

    public static String toFormatString(long milliTimestamp) {
        return toLocalDateTime(milliTimestamp).format(DATE_TIME_FORMATTER);
    }
}
