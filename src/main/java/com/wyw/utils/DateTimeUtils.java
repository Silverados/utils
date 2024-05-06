package com.wyw.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.List;
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
    public static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
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
     *
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
     *
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
     * @return get start of today timestamp
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
     *
     * @param dateTime1
     * @param dateTime2
     * @return
     */
    public static boolean isSameWeek(final LocalDateTime dateTime1, final LocalDateTime dateTime2) {
        return weekBasedYear(dateTime1) == weekBasedYear(dateTime2) && weekOfWeekBaseYear(dateTime1) == weekOfWeekBaseYear(dateTime2);
    }

    public static boolean isSameWeek(long cmpTimeMillis, long nowTimeMillis) {
        LocalDateTime cmpLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(cmpTimeMillis), ZoneId.systemDefault());
        LocalDateTime nowLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(nowTimeMillis), ZoneId.systemDefault());
        return isSameWeek(cmpLocalDateTime, nowLocalDateTime);
    }

    public static int weekOfWeekBaseYear(LocalDateTime localDateTime) {
        return localDateTime.get(ISO_WEEK_FIELDS.weekOfWeekBasedYear());
    }

    public static int weekBasedYear(LocalDateTime localDateTime) {
        return localDateTime.get(ISO_WEEK_FIELDS.weekBasedYear());
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

    public static boolean isToday(LocalDateTime dateTime) {
        return isSameDay(dateTime, LocalDateTime.now());
    }

    public static boolean notToday(LocalDateTime dateTime) {
        return !isToday(dateTime);
    }

    /**
     * 获取给定的时间戳（毫秒）到下一个整点的毫秒数
     * 使用LocalDateTime
     */
    public static long getMilliSecondToNextHour(long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        LocalDateTime nextHour = localDateTime.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        return Duration.between(localDateTime, nextHour).toMillis();
    }

    /**
     * 获取给定的时间戳（毫秒）到下一个整半点的毫秒数
     * 例如: 当前是 10:23:45, 则返回 到10:30:00 的毫秒数; 当前是 10:35:00, 则返回 到11:00:00 的毫秒数
     */
    public static long getMilliSecondToNextHalfHour(long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        // 判断是在整点的前半小时还是后半小时
        if (localDateTime.getMinute() < 30) {
            LocalDateTime nextHalfHour = localDateTime.withMinute(30).withSecond(0).withNano(0);
            return Duration.between(localDateTime, nextHalfHour).toMillis();
        } else {
            LocalDateTime nextHour = localDateTime.plusHours(1).withMinute(0).withSecond(0).withNano(0);
            return Duration.between(localDateTime, nextHour).toMillis();
        }
    }

    /**
     * 获取给定的时间戳（毫秒）在区间内的下一个整点的毫秒数
     * 例如: 给定区间[3,6,10,12,0] 代表预期在北京时间的3点或者6点或者10点...刷新
     * 当前是 11:23:45, 这种情况下就要返回到12点的毫秒数
     * 当前是 15:23:45, 这种情况下就要返回到第二天3点的毫秒数
     */
    public static long getMilliSecondFixedHour(long timestamp, List<Integer> hours) {
        if (hours == null || hours.isEmpty()) {
            throw new IllegalArgumentException("The 'hours' list must not be empty.");
        }

        // sort the hours in ascending order
        hours.sort(Integer::compareTo);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("Asia/Shanghai"));
        var currentHour = localDateTime.getHour();

        int nextHour = -1;
        for (int hour : hours) {
            // check hour valid
            if (hour < 0 || hour > 23) {
                throw new IllegalArgumentException("The 'hours' list must be in range [0, 23].");
            }

            if (hour > currentHour) {
                nextHour = hour;
                break;
            }
        }

        if (nextHour == -1) {
            // If there are no upcoming hours, go to the first hour in the list on the next day
            nextHour = hours.get(0);
            localDateTime = localDateTime.plusDays(1);
        }

        var nextTime = localDateTime.withHour(nextHour).withMinute(0).withSecond(0).withNano(0);
        return Duration.between(localDateTime, nextTime).toMillis();
    }

    /**
     * 获取给定的时间戳（毫秒）在区间内的下一个间隔Z毫秒的毫秒数
     * 例如: 给定刷新区间[12, 14]和间隔分钟Z=30*60*1000, 代表预期在北京时间的12点到14点之间每隔30分钟刷新
     * 当前是 11:23:45, 这种情况下就要返回到12点的毫秒数
     * 当前是 12:23:45, 这种情况下返回30分钟毫秒数
     * 当前是 13:35:45, 这种情况下就要返回到第二天12点的毫秒数, 因为他超出了14点
     */
    public static long getMilliSecondInDuration(long timestamp, int[] hours, int millisInterval) {
        if (hours == null || hours.length <= 2 || hours.length % 2 != 0) {
            throw new IllegalArgumentException("The 'hours' array must not be null and its length must be 2.");
        }

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("Asia/Shanghai"));
        LocalDateTime nextTime = null;
        for (int i = 0; i < hours.length - 1; i += 2) {
            int hour = hours[i];
            // check hour valid
            if (hour < 0 || hour > 23) {
                throw new IllegalArgumentException("The 'hours' list must be in range [0, 23].");
            }

            int beginHour = hours[i];
            int endHour = hours[i + 1];
            if (beginHour > endHour && endHour != 0) {
                throw new IllegalArgumentException("beginHour %d bigger than endHour %d".formatted(beginHour, endHour));
            }

            var beginTime = localDateTime.withHour(beginHour).withMinute(0).withSecond(0).withNano(0);
            var endTime = localDateTime.withHour(endHour).withMinute(0).withSecond(0).withNano(0);
            if (endHour == 0) {
                endTime = endTime.plusDays(1);
            }

            if (localDateTime.isBefore(beginTime) || localDateTime.equals(beginTime)) {
                // 如果当前时间在区间之前，那么下一个时间就是区间的开始时间
                nextTime = beginTime;
                break;
            } else if (localDateTime.isAfter(beginTime) && localDateTime.isBefore(endTime)) {
                // 如果当前时间在区间之中，那么预期下一个时间就是当前时间加上间隔
                nextTime = localDateTime.plus(Duration.ofMillis(millisInterval));
                if (nextTime.isBefore(endTime)) {
                    // 如果下一个时间在区间之中，那么就是下一个时间
                    break;
                }
            }

            // 最后一个区间
            if (i == hours.length - 2) {
                // 如果下一个时间在区间之后，而且已经是最后一个区间，那么就是第二天的开始时间
                nextTime = localDateTime.plusDays(1).withHour(hours[0]).withMinute(0).withSecond(0).withNano(0);
                break;
            }
        }

//        System.out.println("time: %s, nextTime: %s".formatted(localDateTime, nextTime));
        return Duration.between(localDateTime, nextTime).toMillis();
    }

    /**
     * 给定时间戳是否超过北京时间的小时数
     */
    public static boolean inDuration(long timestamp, int beginHour, int endHour) {
        // check hour valid
        if (beginHour < 0 || beginHour > 23 || endHour < 0 || endHour > 23) {
            throw new IllegalArgumentException("The hour must be in range [0, 23].");
        }

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("Asia/Shanghai"));
        LocalDateTime beginHourTime = localDateTime.withHour(beginHour).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endHourTime = localDateTime.withHour(endHour).withMinute(0).withSecond(0).withNano(0);
        if (endHour == 0) {
            endHourTime = localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0).plusDays(1);
        }
        return localDateTime.isAfter(beginHourTime) && localDateTime.isBefore(endHourTime);
    }

    public static boolean inDuration(long timestamp, int[] hours) {
        if (hours == null || hours.length <= 2 || hours.length % 2 != 0) {
            throw new IllegalArgumentException("The 'hours' array must not be null and its length must be 2.");
        }

        for (int i = 0; i < hours.length - 1; i += 2) {
            if (inDuration(timestamp, hours[i], hours[i + 1])) {
                return true;
            }
        }
        return false;
    }
}

