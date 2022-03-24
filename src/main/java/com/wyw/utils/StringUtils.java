package com.wyw.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Iterator;

public class StringUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";
    public static final String BAD_RANGE = "Must be positive";

    private StringUtils() {
        throw new IllegalArgumentException(INVALID_CONSTRUCT);
    }

    /**
     * @param str - may be null
     * @return true if str is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * @param str - may be null
     * @return true if str is blank or null
     * @since 11
     */
    public static boolean isBlank(String str) {
        return isEmpty(str) || str.isBlank();
    }

    /**
     * @param str -
     * @return true if str is numeric
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int size = str.length();
        for (int i = 0; i < size; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 注意： 值会四舍五入
     * careful: value will be rounded using the round half up algorithm
     * @param value - value to format
     * @param nDecimal - keep n decimal
     * @return a string value that keep n decimal
     */
    public static String keepNDecimal(double value, int nDecimal) {
        if (nDecimal < 0) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        String formatStr = String.format("%%.%df", nDecimal);
        return String.format(formatStr, value);
    }

    /**
     * 保留两位小数 （四舍五入）
     * @param value - value to format
     * @return a string value that keep 2 decimal
     */
    public static String keep2Decimal(double value) {
        return keepNDecimal(value, 2);
    }

    /**
     * 保留N位小数 （向下取整）
     * @param value - value to format
     * @param nDecimal - keep n decimal
     * @return a string value that keep n decimal
     */
    public static String keepNDecimalFloor(double value, int nDecimal) {
        if (nDecimal < 0) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(nDecimal);
        format.setMinimumFractionDigits(nDecimal);
        format.setRoundingMode(RoundingMode.FLOOR);
        return format.format(value);
    }

    /**
     * 保留两位小数 （向下取整）
     * @param value - value to format
     * @return a string value that keep 2 decimal
     */
    public static String keep2DecimalFloor(double value) {
        return keepNDecimalFloor(value, 2);
    }


    /**
     * 给字符串上色
     * @param str - string need to format
     * @param color color type
     * @return formatted string
     */
    public static String formatWithColor(String str, Color color) {
        String format = String.format("\033[%dm%%s\033[0m", color.getNum());
        return String.format(format, str);
    }

    public enum Color {
        BLACK(30),
        RED(31),
        GREEN(32),
        YELLOW(33),
        BLUE(34),
        MAGENTA(35),
        CYAN(36),
        WHITE(37);

        int num;

        Color(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }

    /**
     * revert a string
     * @param str
     * @return
     */
    public static String revert(String str) {
        return new StringBuilder(str).reverse().toString();
    }


    public static String concat(String sign, Object... objects) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            sb.append(objects[i]);
            if (i != objects.length - 1) {
                sb.append(sign);
            }
        }
        return sb.toString();
    }
    public static <E> String concat(String sign, Iterable<E> iterable) {
        Iterator<E> iterator = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append(sign);
        }
        if (sb.length() >= sign.length()) {
            sb.delete(sb.length() - sign.length(), sb.length());
        }
        return sb.toString();
    }
}
