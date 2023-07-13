package com.wyw.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Main {
    @Test
    void test() {
        HashMap<String, String> map = null;
        test(map);
        System.out.println(map);
    }

    private void test(HashMap<String, String> map) {
        map = new HashMap<>();
        map.put("a", "a");
    }
}
