package com.zheng.mystock;

import android.support.v4.app.Fragment;

import com.zheng.mystock.util.BaseFragmentActivity;

/**
 * Created by michael on 2015/8/25.
 */
public class MainActivity extends BaseFragmentActivity {


    @Override
    public Fragment createFragment() {
        return new FragmentStock();
    }
}
