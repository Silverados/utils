package com.wyw.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemUtilsTest {

    @Test
    void getPidStr() {
        System.out.println(SystemUtils.getPidStr());
    }
}