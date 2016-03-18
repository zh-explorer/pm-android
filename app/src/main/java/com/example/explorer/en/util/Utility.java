package com.example.explorer.en.util;

import android.text.TextUtils;

import com.example.explorer.en.db.WeatherDB;
import com.example.explorer.en.model.City;
import com.example.explorer.en.model.CityList;
import com.example.explorer.en.model.CityInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by explorer on 16-3-16.
 *
 */
public class Utility {
    public static List<City> handleCitiesResponse(WeatherDB weatherDB, String response) {
        Gson gson = new Gson();
        CityList cityList = gson.fromJson(response, CityList.class);
        List<CityInfo> list = cityList.getCityList();
        List<City> cityList1 = new ArrayList<>();
        if (!TextUtils.isEmpty(response)) {
            for (Object aList : list) {
                City city = new City(aList);
                cityList1.add(city);
                weatherDB.saveCity(city);
            }
            return cityList1;
        }
        return null;
    }
}
