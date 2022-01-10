package com.wyw.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    static final String TEST_PATH = "src/test/java/com/wyw/utils/hello.txt";

    @Test
    void readFile() {
        System.out.println(FileUtils.readFile(TEST_PATH));
    }
}