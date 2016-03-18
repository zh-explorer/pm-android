package com.example.explorer.en.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.widget.SimpleCursorAdapter;

import com.example.explorer.en.model.City;
import com.example.explorer.en.model.Data;
import com.example.explorer.en.util.HttpCallbackListenter;
import com.example.explorer.en.util.HttpUtil;
import com.example.explorer.en.util.Utility;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by explorer on 16-3-16.
 * a single for database control
 */
public class WeatherDB {

    /**
     * database name
     */
    public static final String DB_NAME = "weather";

    /**
     * database version
     */
    public static final int VERSION = 1;

    private static WeatherDB weatherDB;

    private SQLiteDatabase db;


    /**
     * make a private build func
     */
    private WeatherDB(Context context) {
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    /**
     * stone city instance to database
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("county_name", city.getCountyName());
            values.put("city_code", city.getCityCode());
            values.put("lat", city.getLatitude());
            values.put("lon", city.getLongitude());
            values.put("province", city.getProvince());
            db.insert("City", null, values);
        }
    }

    /**
     * get all city from database
     */
    public List<City> loadCities() {
        List<City> list = new ArrayList<>();
        Cursor cursor = db.query("City", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCountyName(cursor.getString(cursor.getColumnIndex("count_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setLatitude(cursor.getDouble(cursor.getColumnIndex("lat")));
                city.setLongitude(cursor.getDouble(cursor.getColumnIndex("lon")));
                city.setProvince(cursor.getString(cursor.getColumnIndex("province")));
                list.add(city);
            } while(cursor.moveToNext());
        }
        else {
            list = queryFromServer();
        }

        cursor.close();
        return list;
    }

    private List<City> queryFromServer() {
        String url = "https://api.heweather.com/x3/citylist?search=allchina&key=9bcab69f01614c91a5a5c652b7999ee0";
        HttpUtil.sendHttpRequest(url, new HttpCallbackListenter() {
            @Override
            public void onFinish(String response) {
                Utility.handleCitiesResponse(weatherDB, response);
                Message message = new Message();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
