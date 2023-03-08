package com.wyw.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public class JsonUtils {

    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";

    private JsonUtils() {
        throw new UnsupportedOperationException(INVALID_CONSTRUCT);
    }

    public static JSONObject readFileAsJSONObject(String url) {
        return JSON.parseObject(FileUtils.readFile(url));
    }

    public static JSONArray readFileAsJSONArray(String url) {
        return JSON.parseArray(FileUtils.readFile(url));
    }

    public static <T> T readFileAsClass(String url, Class<T> clazz) {
        return JSON.parseObject(FileUtils.readFile(url), clazz);
    }

}
