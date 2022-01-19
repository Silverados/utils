package com.wyw.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtils {

    public static final String INVALID_PARAM = "Param must be positive!";

    public static int cellDiv(int x, int y) {
        if (x < 0 || y <= 0) {
            throw new IllegalArgumentException(INVALID_PARAM);
        }
        return x % y == 0 ? x / y : x / y + 1;
    }
}
