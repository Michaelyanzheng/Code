package com.zheng.convenience.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zheng.convenience.R;
import com.zheng.convenience.util.Config;
import com.zheng.convenience.weathertools.BackPressedListener;
import com.zheng.convenience.weathertools.City;
import com.zheng.convenience.weathertools.County;
import com.zheng.convenience.weathertools.HttpUtil;
import com.zheng.convenience.weathertools.Province;
import com.zheng.convenience.weathertools.Utility;
import com.zheng.convenience.weathertools.WeatherDB;
import com.zheng.convenience.weathertools.WeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 2015/8/28.
 */
public class WeatherFragmentList extends Fragment implements BackPressedListener{

    public static final String WEATHER_FRAGMENT_LIST = "WeatherFragmentList";


    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private WeatherDB mWeatherDB;

    private ProgressDialog mProgressDialog;
    private TextView mTitleText;
    private ListView mListView;
    private ArrayAdapter<String> mStringArrayAdapter;

    private ArrayList<String> dataList;

    private List<Province> mProvinceList;
    private List<City> mCityList;
    private List<County> mCountyList;

    private Province selectedProvince;
    private City selectedCity;

    public static int currentLevel;
    private static final String CURRENTLEVEL = "currentLevel";
    private static final String DATELIST = "dateList";

    private static final String SELECTEDPROVINCE = "selectedProvince";
    private static final String SELECTEDCITY = "selectedCity";

    private LayoutAnimationController mLayoutAnimationController;
    private ScaleAnimation mScaleAnimation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(Config.TAG, "WeatherFragmentList onCreate.......");

        if (savedInstanceState != null){

            currentLevel = savedInstanceState.getInt(CURRENTLEVEL);
            dataList = savedInstanceState.getStringArrayList(DATELIST);

            selectedProvince = (Province) savedInstanceState.getSerializable(SELECTEDPROVINCE);
            selectedCity = (City) savedInstanceState.getSerializable(SELECTEDCITY);

            Log.d(Config.TAG,currentLevel +"");
            Log.d(Config.TAG,dataList.toString());

        }else{

            currentLevel = 0;
            dataList = new ArrayList<String>();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt(CURRENTLEVEL, currentLevel);
        outState.putStringArrayList(DATELIST, dataList);

        if (selectedProvince != null){

            outState.putSerializable(SELECTEDPROVINCE,selectedProvince);
        }
        if (selectedCity != null){

            outState.putSerializable(SELECTEDCITY,selectedCity);
        }

        Log.d(Config.TAG, "onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_list,container,false);

        mTitleText = (TextView) view.findViewById(R.id.title_text);
        mListView = (ListView) view.findViewById(R.id.list_view);

        mStringArrayAdapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,dataList);
        mListView.setAdapter(mStringArrayAdapter);

        mWeatherDB = WeatherDB.getInstance(getActivity());
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (currentLevel == LEVEL_PROVINCE) {

                    selectedProvince = mProvinceList.get(position);
                    queryCities();

                } else if (currentLevel == LEVEL_CITY) {

                    selectedCity = mCityList.get(position);
                    queryCounties();

                } else if (currentLevel == LEVEL_COUNTY) {

                    String countyCode = mCountyList.get(position).getCountyCode();

                    Log.d(Config.TAG,"currentLevel == LEVEL_COUNTY============= "+countyCode);

                    WeatherFragmentDetail weatherFragmentDetail = WeatherFragmentDetail.newInstance(countyCode);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.
                            beginTransaction().
                            add(R.id.fragment_weather_contain, weatherFragmentDetail).
                            addToBackStack(null).
                            commit();
                }
                mListView.startLayoutAnimation();
            }
        });

        mScaleAnimation = new ScaleAnimation(0,1,0,1);
        mScaleAnimation.setDuration(500);
        mLayoutAnimationController = new LayoutAnimationController(mScaleAnimation,0.5f);
        mListView.setLayoutAnimation(mLayoutAnimationController);

        refresh();


        return view;
    }

    private void refresh(){

        if (currentLevel == LEVEL_PROVINCE) {

            queryProvinces();

        } else if (currentLevel == LEVEL_CITY) {

            queryCities();

        }else if (currentLevel == LEVEL_COUNTY){

            queryCounties();
        }

    }

    private void queryProvinces(){

        mProvinceList = mWeatherDB.loadProvinces();

        if (mProvinceList.size() > 0){

            dataList.clear();

            for (Province province : mProvinceList){
                dataList.add(province.getProvinceName());
            }

            mStringArrayAdapter.notifyDataSetChanged();
            mListView.setSelection(0);
            mTitleText.setText("中国");
            currentLevel = LEVEL_PROVINCE;

        }else{

            queryFromServer(null,WeatherOpenHelper.PROVINCE);
        }
    }

    private void queryCities(){

        mCityList = mWeatherDB.loadCities(selectedProvince.getId());

        if (mCityList.size() > 0){

            dataList.clear();

            for (City city : mCityList){

                dataList.add(city.getCityName());
            }

            mStringArrayAdapter.notifyDataSetChanged();
            mListView.setSelection(0);
            mTitleText.setText(selectedProvince.getProvinceName());
            currentLevel = LEVEL_CITY;

        }else{

            queryFromServer(selectedProvince.getProvinceCode(),WeatherOpenHelper.CITY);
        }
    }

    private void queryCounties(){

        mCountyList = mWeatherDB.loadCounties(selectedCity.getId());

        if (mCountyList.size() > 0){

            dataList.clear();

            for (County county : mCountyList){

                dataList.add(county.getCountyName());
            }

            mStringArrayAdapter.notifyDataSetChanged();
            mListView.setSelection(0);
            mTitleText.setText(selectedCity.getCityName());
            currentLevel = LEVEL_COUNTY;

        }else{

            queryFromServer(selectedCity.getCityCode(),WeatherOpenHelper.COUNTY);
        }

    }

    // http://www.weather.com.cn/data/list3/city.xml?

    // http://www.weather.com.cn/data/list3/city01.xml

    // http://www.weather.com.cn/data/list3/city0101.xml

    // http://www.weather.com.cn/data/list3/city010101.xml?

    // http://www.weather.com.cn/data/cityinfo/101010100.html?

    private void queryFromServer(final String code,final String type){

        String addressUrl;

        if (!TextUtils.isEmpty(code)){

            addressUrl = Utility.ADDRESS_CODE + code + Utility.END_XML;

        }else{

            addressUrl = Utility.ADDRESS;
        }

        showProgressDialog();

        HttpUtil.sendHttpRequest(addressUrl, new HttpUtil.SuccessCallback() {
            @Override
            public void onSuccess(String result) {

                onFinish(result, type);
            }
        }, new HttpUtil.FailCallback() {
            @Override
            public void onFail() {
                onFailResult();
            }
        });
    }

    private void onFailResult(){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                closeProgressDialog();
                showFail();
            }
        });

    }

    private void onFinish(String result,final String type){

        boolean resultBoolean = false;

        if (WeatherOpenHelper.PROVINCE.equals(type)){

            resultBoolean = Utility.handleProvincesResponse(mWeatherDB,result);

        }else if (WeatherOpenHelper.CITY.equals(type)){

            resultBoolean = Utility.handleCitiesResponse(mWeatherDB,result,selectedProvince.getId());

        }else if (WeatherOpenHelper.COUNTY.equals(type)){

            resultBoolean = Utility.handleCountiesResponse(mWeatherDB,result,selectedCity.getId());
        }

        if (resultBoolean){

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    closeProgressDialog();

                    switch (type){

                        case WeatherOpenHelper.PROVINCE:

                            queryProvinces();

                            break;
                        case WeatherOpenHelper.CITY:

                            queryCities();

                            break;

                        case WeatherOpenHelper.COUNTY:

                            queryCounties();

                            break;
                    }
                }
            });


        }
    }

    public void showFail(){

        Toast.makeText(getActivity(),"加载失败...",Toast.LENGTH_SHORT).show();

    }

    private void showProgressDialog(){

        if (mProgressDialog == null){

            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("正在加载...");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }

    public void closeProgressDialog(){

        if (mProgressDialog != null){

            mProgressDialog.dismiss();
        }
    }


    @Override
    public void onBackPressed() {

        Log.d(Config.TAG,"--------zheng");

        switch (currentLevel){

            case LEVEL_CITY:

                queryProvinces();

                break;

            case LEVEL_COUNTY:

                queryCities();

                break;

            default:

                break;
        }
        mListView.startLayoutAnimation();
    }
}
