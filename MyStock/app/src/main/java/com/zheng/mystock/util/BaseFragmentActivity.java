package com.zheng.mystock.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.zheng.mystock.R;

/**
 * Created by michael on 2015/8/25.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_content);

        if (fragment == null){

            fragment = createFragment();
            fragmentManager.beginTransaction().
                    add(R.id.fragment_content,fragment).
                    commit();
        }
    }
}
