package com.lex.tang.lib.utils;

import com.google.gson.Gson;

/**
 * Created by tang on 2016/4/4.
 */
public class JsonParse {
    private static Gson gson;
    static {
        gson = new Gson();
    }
    public static <T> T fromJson(String json,Class<T> obj) {
        return gson.fromJson(json,obj);
    }

}
