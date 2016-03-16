package com.example.explorer.en.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by explorer on 16-3-16.
 * sql open helper class to create table
 */
public class WeatherOpenHelper extends SQLiteOpenHelper{
    /**
     *  String to create City
     **/
    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement, "
            + "city_name text, "
            + "county_name text"
            + "city_code text, "
            + "lat real"
            + "lon real"
            + "province text)";

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
