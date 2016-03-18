package com.example.explorer.en.model;

import android.os.Bundle;

/**
 * Created by explorer on 16-3-16.
 * a city class to stone data
 */
public class City {
    private int id;
    private String cityName;
    private String countyName;
    private String cityCode;
    private double latitude;
    private double longitude;
    private String province;

    public City(Object info) {
        if (info.getClass() == CityInfo.class) {
            CityInfo cityInfo = (CityInfo)info;
            cityName = cityInfo.getCity();
            countyName = cityInfo.getCnty();
            cityCode = cityInfo.getId();
            latitude = Double.parseDouble(cityInfo.getLat());
            longitude = Double.parseDouble(cityInfo.getLon());
            province = cityInfo.getProv();
        }
    }

    public City() {

    }

    public City(Bundle bundle) {
        id = bundle.getInt("id");
        cityName = bundle.getString("cityName");
        countyName = bundle.getString("countyName");
        cityCode = bundle.getString("cityCode");
        latitude = bundle.getDouble("latitude");
        longitude = bundle.getDouble("longitude");
        province = bundle.getString("province");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public  String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("cityName", cityName);
        bundle.putString("countyName",countyName);
        bundle.putString("cityCode",cityCode);
        bundle.putDouble("longitude",longitude);
        bundle.putDouble("latitude",latitude);
        bundle.putString("province",province);
        return bundle;
    }
}
