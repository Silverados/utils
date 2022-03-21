package com.wyw.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Timestamp -> second timestamp
 * MilliTimestamp -> millisecond(1/1000 second) timestamp (default)
 * NanoSecond -> 1/1000,000,000 second
 */
public class DateTimeUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";

    /**
     * 每秒有1000毫秒
     */
    public static final int MILLISECOND_PER_SECOND = 1000;
    /**
     * 每分钟有60秒
     */
    public static final int SECOND_PER_MINUTE = 60;
    /**
     * 每小时有60分钟
     */
    public static final int MINUTE_PER_HOUR = 60;
    /**
     * 每天有24小时
     */
    public static final int HOUR_PER_DAY = 24;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_TIME;
    public static final ZoneOffset ZONE_OFFSET = ZoneOffset.of("+8");
    /**
     * 该标准意味着：周一是每周的第一天，第一周必须是4天及以上
     */
    public static final WeekFields ISO_WEEK_FIELDS = WeekFields.ISO;

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

    public static LocalDate toLocalDate(long milliTimestamp) {
        return toLocalDateTime(milliTimestamp).toLocalDate();
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

    /**
     * @param milliTimestamp1 - millisecond timestamp
     * @param milliTimestamp2 - millisecond timestamp
     * @return true if two timestamp is same day
     */
    public static boolean isSameDay(final long milliTimestamp1, final long milliTimestamp2) {
        return toLocalDate(milliTimestamp1).isEqual(toLocalDate(milliTimestamp2));
    }

    public static boolean isSameDay(final LocalDateTime dateTime1, final LocalDateTime dateTime2) {
        return dateTime1.toLocalDate().isEqual(dateTime2.toLocalDate());
    }

    /**
     * 判断两个日期是否是同周，周数采用的是ISO标准。可能是不同的年份。
     * @param dateTime1
     * @param dateTime2
     * @return
     */
    public static boolean isSameWeek(final LocalDateTime dateTime1, final LocalDateTime dateTime2) {
        return weekOfYear(dateTime1) == weekOfYear(dateTime2);
    }

    public static int weekOfYear(LocalDateTime localDateTime) {
        return localDateTime.get(ISO_WEEK_FIELDS.weekOfWeekBasedYear());
    }

    public static boolean isSameMonth(final LocalDateTime dateTime1, final LocalDateTime dateTime2) {
        return dateTime1.getYear() == dateTime2.getYear() && dateTime1.getMonth() == dateTime2.getMonth();
    }

    public static long durationDays(final LocalDate dateTime1) {
        return dateTime1.until(LocalDate.now(), ChronoUnit.DAYS);
    }

    public static long durationDays(final LocalDate dateTime1, final LocalDate dateTime2) {
        return dateTime1.until(dateTime2, ChronoUnit.DAYS);
    }

    public static String weekDisplayName(LocalDate localDate) {
        return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE);
    }

    public static long durationMonth(final LocalDate dateTime1) {
        return dateTime1.until(LocalDate.now(), ChronoUnit.MONTHS);
    }

    public static long durationMonth(final LocalDate dateTime1, final LocalDate dateTime2) {
        return dateTime1.until(dateTime2, ChronoUnit.MONTHS);
    }

    public static long durationYear(final LocalDate dateTime1) {
        return dateTime1.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    public static long durationYear(final LocalDate dateTime1, final LocalDate dateTime2) {
        return dateTime1.until(dateTime2, ChronoUnit.YEARS);
    }
}
