package com.zheng.convenience.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zheng.convenience.R;
import com.zheng.convenience.util.Config;
import com.zheng.convenience.weathertools.BackPressedListener;

/**
 * Created by michael on 2015/9/5.
 */
public class WeatherFragmentContain extends Fragment implements BackPressedListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_contain, container, false);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_weather_contain);

        if (fragment == null){

            Log.d(Config.TAG, "fragment == null");

            fragment = new WeatherFragmentList();

            fragmentManager.beginTransaction().
                    replace(R.id.fragment_weather_contain, fragment).
                    addToBackStack(null).
                    commit();

        }

        return view;
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_weather_contain);

        if( fragment instanceof WeatherFragmentList){

            if (WeatherFragmentList.currentLevel == WeatherFragmentList.LEVEL_PROVINCE){

                getActivity().finish();
            }
            ((WeatherFragmentList) fragment).onBackPressed();
        }

        if ( fragment instanceof WeatherFragmentDetail){

            ((WeatherFragmentDetail) fragment).onBackPressed();
        }
    }
}

