package com.wyw.utils;

public class SystemUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";

    private SystemUtils() {
        throw new IllegalArgumentException(INVALID_CONSTRUCT);
    }

    public static final String JAVA_VERSION = getSystemProperty("java.version");
    public static final String OS_NAME = getSystemProperty("os.name");

    private static String getSystemProperty(final String property) {
        try {
            return System.getProperty(property);
        } catch (final SecurityException exception) {
            return null;
        }
    }

    public static final String OS_NAME_WINDOWS_PREFIX = "WINDOWS";
    public static final boolean IS_OS_WINDOWS = isOsNameMatch(OS_NAME_WINDOWS_PREFIX);

    public static final String OS_NAME_LINUX_PREFIX = "LINUX";
    public static final boolean IS_OS_LINUX = isOsNameMatch(OS_NAME_LINUX_PREFIX);

    public static final String OS_NAME_MAC_PREFIX = "MAC";
    public static final boolean IS_OS_MAC = isOsNameMatch(OS_NAME_MAC_PREFIX);

    public static boolean isOsNameMatch(String prefix) {
        if (OS_NAME == null || prefix == null) {
            return false;
        }
        return OS_NAME.toUpperCase().startsWith(prefix);
    }

}
