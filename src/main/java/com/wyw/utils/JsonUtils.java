package com.wyw.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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

}
