package com.wyw.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

public class JsonUtils {

    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";

    private JsonUtils() {
        throw new UnsupportedOperationException(INVALID_CONSTRUCT);
    }

    public static JSONObject readFileAsJSONObject(InputStream stream) {
        return JSON.parseObject(stream);
    }
    public static JSONObject readFileAsJSONObject(Path path) {
        return JSON.parseObject(FileUtils.readFile(path));
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

    public static <T> T readFileAsClass(Path path, Class<T> clazz) {
        return JSON.parseObject(FileUtils.readFile(path), clazz);
    }

    public static String toJSONString(Object object) {
        var str = JSON.toJSONString(object, JSONWriter.Feature.PrettyFormat);
        str = str.replaceAll("\\t", "  ");
        return str;
    }

    /**
     * Converts input like {"foo": {"bar": "baz"}} to {"foo.bar": "baz"}
     */
    public static void putFlattenedKey(Map<String, String> map, String key, Object element) {
        if (element instanceof JSONObject) {
            ((JSONObject) element).forEach((subKey, subValue) -> {
                String keyPrefix = key.isEmpty() ? "" : key + ".";
                putFlattenedKey(map, keyPrefix + subKey, subValue);
            });
        } else if (element instanceof JSONArray) {
            map.put(key, ((JSONArray) element).toJSONString());
        } else {
            map.put(key, element.toString());
        }
    }
}
