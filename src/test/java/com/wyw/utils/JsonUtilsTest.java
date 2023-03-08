package com.wyw.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {

    public static class People {
        public String name;
        public int age;
    }

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

    @Test
    void readFileAsClass() {
        People people = JsonUtils.readFileAsClass("src/test/java/com/wyw/utils/obj.json", People.class);
        assertEquals(people.name, "Wish");
        assertEquals(people.age, 15);
    }
}