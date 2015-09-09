package com.zheng.convenience.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zheng.convenience.R;
import com.zheng.convenience.util.Config;
import com.zheng.convenience.weathertools.HttpUtil;
import com.zheng.convenience.weathertools.Utility;

/**
 * Created by michael on 2015/9/7.
 */
public class WeatherFragmentDetail extends Fragment {

    private static final String COUNTYCODE = "countyCode";
    private static final String WEATHERCODE = "weatherCode";

    private LinearLayout mWeatherInfoLayout;
    private TextView mCityNameText;
    private TextView mPublishText;
    private TextView mWeatherDespText;
    private TextView mTemp1Text;
    private TextView mTemp2Text;
    private TextView mCurrentDateText;
    private Button mRefreshWeather;

    private TextView mLoadToText;

    private String countyCode;

    private Handler myHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case 0:

                    refreshTextViewColor(Color.WHITE);

                    break;

                default:

                    super.handleMessage(msg);

                    break;
            }

        }
    };


    public static WeatherFragmentDetail newInstance(String countyCode){

        Bundle args = new Bundle();
        args.putSerializable(COUNTYCODE, countyCode);

        WeatherFragmentDetail weatherFragmentDetail = new WeatherFragmentDetail();
        weatherFragmentDetail.setArguments(args);

        return weatherFragmentDetail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_detail,container,false);

        mWeatherInfoLayout = (LinearLayout) view.findViewById(R.id.weather_info_layout);
        mLoadToText = (TextView) view.findViewById(R.id.loadTo_Text);

        mCityNameText = (TextView) view.findViewById(R.id.city_name);
        mPublishText = (TextView) view.findViewById(R.id.publish_text);
        mWeatherDespText = (TextView) view.findViewById(R.id.weather_desp);
        mTemp1Text = (TextView) view.findViewById(R.id.temp1);
        mTemp2Text = (TextView) view.findViewById(R.id.temp2);
        mCurrentDateText = (TextView) view.findViewById(R.id.current_date);
        mRefreshWeather = (Button) view.findViewById(R.id.refresh_weather);

        if (savedInstanceState != null){

            mLoadToText.setText("");
            mCityNameText.setText(savedInstanceState.getString(Utility.CITY_NAME));
            mPublishText.setText(savedInstanceState.getString(Utility.PUBLISH_TIME));
            mWeatherDespText.setText(savedInstanceState.getString(Utility.WEATHER_DESP));
            mTemp1Text.setText(savedInstanceState.getString(Utility.TEMP1));
            mTemp2Text.setText(savedInstanceState.getString(Utility.TEMP2));
            mCurrentDateText.setText(savedInstanceState.getString(Utility.CURRENT_DATE));
        }

        countyCode = (String) getArguments().getSerializable(COUNTYCODE);

        mRefreshWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoadToText.setText(getString(R.string.Load_to));
                queryWeatherCode(countyCode);
                refreshTextViewColor(Color.RED);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            Thread.sleep(500);

                            Message message = new Message();
                            message.what = 0;
                            WeatherFragmentDetail.this.myHandler.sendMessage(message);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.start();
            }
        });

        queryWeatherCode(countyCode);

        return view;
    }

    private void refreshTextViewColor(int color){

//        mCityNameText.setTextColor(color);
//        mPublishText.setTextColor(color);
        mWeatherDespText.setTextColor(color);
        mTemp1Text.setTextColor(color);
        mTemp2Text.setTextColor(color);
        mCurrentDateText.setTextColor(color);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString(Utility.CITY_NAME, mCityNameText.getText().toString());
        outState.putString(Utility.PUBLISH_TIME, mPublishText.getText().toString());
        outState.putString(Utility.WEATHER_DESP, mWeatherDespText.getText().toString());
        outState.putString(Utility.TEMP1, mTemp1Text.getText().toString());
        outState.putString(Utility.TEMP2, mTemp2Text.getText().toString());
        outState.putString(Utility.CURRENT_DATE, mCurrentDateText.getText().toString());

        super.onSaveInstanceState(outState);
    }

    private void queryWeatherCode(String countyCode){

        String address = "http://www.weather.com.cn/data/list3/city"+countyCode+".xml";

        queryFromServer(address, COUNTYCODE);
    }

    private void queryWeatherInfo(String weatherCode){

        String address = "http://www.weather.com.cn/data/cityinfo/"+weatherCode+".html";
        queryFromServer(address,WEATHERCODE);
    }

    private void queryFromServer(final String address,final String type){

        HttpUtil.sendHttpRequest(address, new HttpUtil.SuccessCallback() {
            @Override
            public void onSuccess(String result) {

                successResult(result,type);
            }
        }, new HttpUtil.FailCallback() {
            @Override
            public void onFail() {

                failResult();
            }
        });
    }
    private void failResult(){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mLoadToText.setText("");

                Toast.makeText(getActivity(), "加载失败...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void successResult(final String result ,final String type){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (COUNTYCODE.equals(type)){
                    if (!TextUtils.isEmpty(result)){
                        String [] array = result.split("\\|");

                        if (array != null && array.length == 2){
                            String weatherCode = array[1];

                            queryWeatherInfo(weatherCode);
                        }
                    }
                }else if (WEATHERCODE.equals(type)){

                    Utility.handleWeatherResponse(getActivity(), result);

                    Log.d(Config.TAG, result);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            showWeather();
                        }
                    });
                }
            }
        });
    }

    private void showWeather(){

        mLoadToText.setText("");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mCityNameText.setText(sharedPreferences.getString(Utility.CITY_NAME,""));
        mTemp1Text.setText(sharedPreferences.getString(Utility.TEMP1,""));
        mTemp2Text.setText(sharedPreferences.getString(Utility.TEMP2,""));
        mWeatherDespText.setText(sharedPreferences.getString(Utility.WEATHER_DESP,""));
        mPublishText.setText(sharedPreferences.getString(Utility.PUBLISH_TIME,""));
        mCurrentDateText.setText(sharedPreferences.getString(Utility.CURRENT_DATE,""));

    }

    public void onBackPressed() {

        Log.d(Config.TAG, "--------WeatherFragmentDetail");

        getActivity().getSupportFragmentManager().popBackStack();
    }
}
