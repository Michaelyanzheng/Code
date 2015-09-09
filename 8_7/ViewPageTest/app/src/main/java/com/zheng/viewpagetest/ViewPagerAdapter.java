package com.zheng.viewpagetest;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Michael on 2015/8/3.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> mViews;
    private Context mContext;

    public ViewPagerAdapter(List<View> views,Context context){

        this.mViews = views;
        this.mContext = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager)container).removeView(mViews.get(position));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ((ViewPager)container).addView(mViews.get(position));

        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view == o);
    }
}
