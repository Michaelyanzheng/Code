package com.zheng.convenience.weathertools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.zheng.convenience.util.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by michael on 2015/9/4.
 */
public class Utility {

    public static final String ADDRESS = "http://www.weather.com.cn/data/list3/city.xml";
    public static final String ADDRESS_CODE = "http://www.weather.com.cn/data/list3/city";
    public static final String END_XML = ".xml";


    // 01|北京,02|上海,03|天津,04|重庆,05|黑龙江,06|吉林,07|辽宁,08|内蒙古,09|河北,10|山西,
    // 11|陕西,12|山东,13|新疆,14|西藏,15|青海,16|甘肃,17|宁夏,18|河南,19|江苏,20|湖北,
    // 21|浙江,22|安徽,23|福建,24|江西,25|湖南,26|贵州,27|四川,28|广东,29|云南,30|广西,
    // 31|海南,32|香港,33|澳门,34|台湾
    public synchronized static boolean handleProvincesResponse(WeatherDB weatherDB, String response){

        if (!TextUtils.isEmpty(response)){

            String [] allProvinces = response.split(",");

            if (allProvinces != null && allProvinces.length > 0 ){

                for (String provinces : allProvinces){

                    String [] array = provinces.split("\\|");

                    Province province = new Province();

                    for (String s : array){

                        Log.d(Config.TAG,s);
                    }

                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);

                    weatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    // 2801|广州,2802|韶关,2803|惠州,2804|梅州,2805|汕头,2806|深圳,2807|珠海,2808|佛山,2809|肇庆,2810|湛江,
    // 2811|江门,2812|河源,2813|清远,2814|云浮,2815|潮州,2816|东莞,2817|中山,2818|阳江,2819|揭阳,2820|茂名,
    // 2821|汕尾,2822|东沙岛
    public static boolean handleCitiesResponse(WeatherDB weatherDB,String response,int provinceId){

        if (!TextUtils.isEmpty(response)){

            String [] allCities = response.split(",");

            if (allCities != null && allCities.length > 0){

                for (String cities : allCities){

                    String [] array = cities.split("\\|");

                    City city = new City();

                    for (String s : array){

                        Log.d(Config.TAG,s);
                    }

                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);

                    weatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    // 280101|广州,280102|番禺,280103|从化,280104|增城,280105|花都,280106|天河
    public static boolean handleCountiesResponse(WeatherDB weatherDB,String response,int cityId){

        if (!TextUtils.isEmpty(response)){

            String [] allCounties = response.split(",");

            if (allCounties != null && allCounties.length > 0){

                for (String counties : allCounties){

                    String [] array = counties.split("\\|");

                    County county = new County();

                    for (String s : array){

                        Log.d(Config.TAG,s);
                    }

                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);

                    weatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

    public static final String WEATHERINFO = "weatherinfo";
    public static final String CITY = "city";
    public static final String CITYID = "cityid";
    public static final String TEMP1 = "temp1";
    public static final String TEMP2 = "temp2";
    public static final String WEATHER = "weather";
    public static final String PTIME = "ptime";

    public static void handleWeatherResponse(Context context,String response){

        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONObject weatherInfo = jsonObject.getJSONObject(WEATHERINFO);

            String cityName = weatherInfo.getString(CITY);
            String weatherCode = weatherInfo.getString(CITYID);
            String temp1 = weatherInfo.getString(TEMP1);
            String temp2 = weatherInfo.getString(TEMP2);
            String weatherDescribe = weatherInfo.getString(WEATHER);
            String publishTime = weatherInfo.getString(PTIME);

            saveWeatherInfo(context,cityName,weatherCode,temp1,temp2,weatherDescribe,publishTime);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static final String CITY_SELECTED = "city_selected";
    public static final String CITY_NAME = "city_name";
    public static final String WEATHER_CODE = "weather_code";
    public static final String WEATHER_DESP = "weather_desp";
    public static final String PUBLISH_TIME = "publish_time";
    public static final String CURRENT_DATE = "current_date";

    public static void saveWeatherInfo(Context context,
                                       String cityName,
                                       String weatherCode,
                                       String temp1,
                                       String temp2,
                                       String weatherDescribe,
                                       String publishTime){

        Log.d(Config.TAG,cityName+"--"+weatherCode+"---"+temp1+"---"+temp2+"---"+weatherDescribe+"---"+publishTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d", Locale.CHINA);

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

        editor.putBoolean(CITY_SELECTED,true);
        editor.putString(CITY_NAME, cityName);
        editor.putString(WEATHER_CODE, weatherCode);
        editor.putString(TEMP1, temp1);
        editor.putString(TEMP2, temp2);
        editor.putString(WEATHER_DESP, weatherDescribe);
        editor.putString(PUBLISH_TIME, publishTime);
        editor.putString(CURRENT_DATE,simpleDateFormat.format(new Date()));

        editor.commit();

    }



}
