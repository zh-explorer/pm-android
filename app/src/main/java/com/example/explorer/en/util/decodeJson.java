package com.example.explorer.en.util;

import com.example.explorer.en.model.Data;
import com.google.gson.Gson;

/**
 * Created by explorer on 15-11-24.
 *
 */
public class decodeJson {
    public static Data decode(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, Data.class);
    }
}
