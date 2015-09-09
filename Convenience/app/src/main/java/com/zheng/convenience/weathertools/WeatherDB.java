package com.zheng.convenience.weathertools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 2015/9/4.
 */
public class WeatherDB {

    private Context mContext;

    private static WeatherDB sWeatherDB;

    private SQLiteDatabase mSQLiteWriteDatabase;

    public static WeatherDB getInstance(Context context){


        if (sWeatherDB == null){
            sWeatherDB = new WeatherDB(context);
        }
        return sWeatherDB;
    }

    private WeatherDB(Context context){

        this.mContext = context;

        WeatherOpenHelper weatherOpenHelper = new WeatherOpenHelper(context);
        mSQLiteWriteDatabase = weatherOpenHelper.getWritableDatabase();
    }

    public void saveProvince(Province province){

        if (province != null){

            ContentValues contentValues = new ContentValues();

            contentValues.put(WeatherOpenHelper.PROVINCE_NAME, province.getProvinceName());
            contentValues.put(WeatherOpenHelper.PROVINCE_CODE, province.getProvinceCode());

            mSQLiteWriteDatabase.insert(WeatherOpenHelper.PROVINCE_TABLE, null, contentValues);
        }
    }

    public void saveCity(City city){

        if (city != null){

            ContentValues contentValues = new ContentValues();

            contentValues.put(WeatherOpenHelper.CITY_NAME, city.getCityName());
            contentValues.put(WeatherOpenHelper.CITY_CODE, city.getCityCode());
            contentValues.put(WeatherOpenHelper.PROVINCE_ID, city.getProvinceId());

            mSQLiteWriteDatabase.insert(WeatherOpenHelper.CITY_TABLE, null, contentValues);
        }
    }

    public void saveCounty(County county){

        if (county != null){

            ContentValues contentValues = new ContentValues();

            contentValues.put(WeatherOpenHelper.COUNTY_NAME, county.getCountyName());
            contentValues.put(WeatherOpenHelper.COUNTY_CODE, county.getCountyCode());
            contentValues.put(WeatherOpenHelper.CITY_ID, county.getCityId());

            mSQLiteWriteDatabase.insert(WeatherOpenHelper.COUNTY_TABLE, null, contentValues);
        }
    }

    public List<Province> loadProvinces(){

        ArrayList<Province> provinceList = new ArrayList<Province>();

        Cursor cursor = mSQLiteWriteDatabase.query(WeatherOpenHelper.PROVINCE_TABLE, null, null, null, null, null, null);

        while(cursor.moveToNext()){

            Province province = new Province();

            province.setId(cursor.getInt(cursor.getColumnIndex(WeatherOpenHelper.ID)));
            province.setProvinceName(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.PROVINCE_NAME)));
            province.setProvinceCode(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.PROVINCE_CODE)));

            provinceList.add(province);
        }

        return provinceList;
    }

    public List<City> loadCities(int provinceId){

        ArrayList<City> cityList = new ArrayList<City>();

        Cursor cursor = mSQLiteWriteDatabase.query(WeatherOpenHelper.CITY_TABLE,
                null,
                "province_id=?",
                new String[]{provinceId+""},
                null, null, null);

        while(cursor.moveToNext()){

            City city = new City();

            city.setId(cursor.getInt(cursor.getColumnIndex(WeatherOpenHelper.ID)));
            city.setCityName(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.CITY_NAME)));
            city.setCityCode(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.CITY_CODE)));
            city.setProvinceId(provinceId);

            cityList.add(city);
        }

        return cityList;
    }

    public List<County> loadCounties(int cityId){

        ArrayList<County> countyList = new ArrayList<County>();

        Cursor cursor = mSQLiteWriteDatabase.query(WeatherOpenHelper.COUNTY_TABLE,
                null, "city_id=?",
                new String[]{cityId+""},
                null, null, null);

        while (cursor.moveToNext()){

            County county = new County();

            county.setId(cursor.getInt(cursor.getColumnIndex(WeatherOpenHelper.ID)));
            county.setCountyName(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.COUNTY_NAME)));
            county.setCountyCode(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.COUNTY_CODE)));
            county.setCityId(cityId);

            countyList.add(county);
        }
        return countyList;
    }
}
