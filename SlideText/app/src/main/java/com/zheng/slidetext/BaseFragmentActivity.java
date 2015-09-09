package com.zheng.slidetext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by michael on 2015/8/25.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_content);

        if (fragment == null){

            fragment = new ZhengFragment();
            fm.beginTransaction().
                    add(R.id.fragment_content,fragment).
                    commit();
        }
    }
}
