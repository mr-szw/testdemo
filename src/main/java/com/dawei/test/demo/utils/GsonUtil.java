package com.dawei.test.demo.utils;

import com.google.gson.Gson;

public class GsonUtil {



    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
