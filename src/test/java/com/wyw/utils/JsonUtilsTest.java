package com.wyw.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {

    @Test
    void readFileAsJSONObject() {
        JSONObject jsonObject = JsonUtils.readFileAsJSONObject("src/test/java/com/wyw/utils/obj.json");
        assertEquals(jsonObject.getString("name"), "Wish");
        assertEquals(jsonObject.getInteger("age"), 15);
    }

    @Test
    void readFileAsJSONArray() {
        JSONArray jsonArray = JsonUtils.readFileAsJSONArray("src/test/java/com/wyw/utils/arr.json");
        assertEquals(jsonArray.getString(0), "a");
        assertEquals(jsonArray.getString(1), "b");
    }
}