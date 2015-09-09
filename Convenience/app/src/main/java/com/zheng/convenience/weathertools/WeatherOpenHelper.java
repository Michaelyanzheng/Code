package com.zheng.convenience.weathertools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michael on 2015/9/4.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "weather.db";
    public static final int VERSION = 1;

    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String COUNTY = "county";

    public static final String PROVINCE_TABLE = "Province";
    public static final String CITY_TABLE = "City";
    public static final String COUNTY_TABLE = "County";

    public static final String ID = "id";
    public static final String PROVINCE_NAME = "province_name";
    public static final String PROVINCE_CODE = "province_code";

    public static final String CITY_NAME = "city_name";
    public static final String CITY_CODE = "city_code";
    public static final String PROVINCE_ID = "province_id";

    public static final String COUNTY_NAME = "county_name";
    public static final String COUNTY_CODE = "county_code";
    public static final String CITY_ID = "city_id";


    private static final String CREATE_PROVINCE = "create table Province(" +
            "id integer primary key autoincrement," +
            "province_name text," +
            "province_code text" +
            ")";

    private static final String CREATE_CITY = "create table City(" +
            "id integer primary key autoincrement," +
            "city_name text," +
            "city_code text," +
            "province_id integer" +
            ")";

    private static final String CREATE_COUNTY = "create table County(" +
            "id integer primary key autoincrement," +
            "county_name text," +
            "county_code text," +
            "city_id integer" +
            ")";

    public WeatherOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
