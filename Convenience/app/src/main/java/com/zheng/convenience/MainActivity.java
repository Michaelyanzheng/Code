package com.zheng.convenience;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import com.zheng.convenience.fragment.ContactFragment;
import com.zheng.convenience.fragment.StockFragment;
import com.zheng.convenience.fragment.WeatherFragmentContain;
import com.zheng.convenience.fragment.WeatherFragmentList;
import com.zheng.convenience.util.Config;

public class MainActivity extends FragmentActivity {


    private StockFragment mStockFragment;
    private WeatherFragmentList mWeatherFragmentList;
    private WeatherFragmentContain mWeatherFragmentContain;
    private ContactFragment mContactFragment;

    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mMyViewPagerAdapter;

    private DisplayMetrics mDisplayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mDisplayMetrics = getResources().getDisplayMetrics();

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.pagerSlidingTabStrip);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mMyViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mMyViewPagerAdapter);

        mPagerSlidingTabStrip.setViewPager(mViewPager);

        setTabsValue();

    }

    private void setTabsValue(){

        // 设置Tab是自动填充满屏幕的
        mPagerSlidingTabStrip.setShouldExpand(true);

        // 设置Tab的分割线是透明的
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);

        // 设置Tab底部线的高度
        mPagerSlidingTabStrip.setUnderlineHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, mDisplayMetrics)
        );

        // 设置Tab Indicator的高度
        mPagerSlidingTabStrip.setIndicatorHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, mDisplayMetrics)
        );

        // 设置Tab标题文字的大小
        mPagerSlidingTabStrip.setTextSize(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, mDisplayMetrics)
        );

        // 设置Tab Indicator的颜色
        mPagerSlidingTabStrip.setIndicatorColor(Color.parseColor("#45c01a"));

        // 设置选中Tab文字的颜色
        mPagerSlidingTabStrip.setSelectedTextColor(Color.parseColor("#45c01a"));

    }


    //转屏 bug
    @Override
    public void onBackPressed() {

        Log.d(Config.TAG, "--------zheng");

        if (mViewPager.getCurrentItem() == 1){

            Log.d(Config.TAG, "mWeatherFragmentContain");

            mWeatherFragmentContain.onBackPressed();

        }else {

            Log.d(Config.TAG, "--------finish");
            super.onBackPressed();
        }
    }

    public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        public MyViewPagerAdapter(FragmentManager fragmentManager) {

            super(fragmentManager);
        }

        private final String[] titles = {Config.SOCKER,Config.WEATHER,Config.CONTACT};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0:

                    if (mStockFragment == null){
                        mStockFragment = new StockFragment();
                    }
                    return mStockFragment;

                case 1:

                    if (mWeatherFragmentContain == null){
                        mWeatherFragmentContain = new WeatherFragmentContain();
                    }
                    return mWeatherFragmentContain;

                case 2:

                    if (mContactFragment == null){
                        mContactFragment = new ContactFragment();
                    }
                    return mContactFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
