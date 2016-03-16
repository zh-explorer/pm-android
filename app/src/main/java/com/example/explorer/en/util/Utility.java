package com.example.explorer.en.util;

import android.text.TextUtils;

import com.example.explorer.en.db.WeatherDB;
import com.example.explorer.en.model.City;
import com.example.explorer.en.model.CityList;
import com.example.explorer.en.model.CityInfo;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by explorer on 16-3-16.
 *
 */
public class Utility {
    public static boolean handleCitiesResponse(WeatherDB weatherDB, String response) {
        Gson gson = new Gson();
        CityList cityList = gson.fromJson(response, CityList.class);
        List<CityInfo> list = cityList.getCityList();
        if (!TextUtils.isEmpty(response)) {
            for (Object aList : list) {
                City city = new City(aList);
                weatherDB.saveCity(city);
            }
            return true;
        }
        return false;
    }
}
