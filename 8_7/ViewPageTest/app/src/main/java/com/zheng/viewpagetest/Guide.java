package com.zheng.viewpagetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 2015/8/3.
 */
public class Guide extends Activity implements android.support.v4.view.ViewPager.OnPageChangeListener {

    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private List<View> mViews;

    private Button mStartButton;

    private ImageView[] dots;
    private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initView();
        initDots();
    }

    private void initView(){

        LayoutInflater inflater = LayoutInflater.from(this);

        mViews = new ArrayList<View>();

        mViews.add(inflater.inflate(R.layout.one,null));
        mViews.add(inflater.inflate(R.layout.two,null));
        mViews.add(inflater.inflate(R.layout.three,null));
        mViews.add(inflater.inflate(R.layout.four, null));

        mViewPagerAdapter = new ViewPagerAdapter(mViews,this);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(this);


        mStartButton = (Button) mViews.get(3).findViewById(R.id.start_btn);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guide.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void initDots(){
        dots = new ImageView[mViews.size()];
        for (int i = 0; i < mViews.size(); i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < ids.length; i++){
            if (position == i){
                dots[i].setImageResource(R.drawable.login_point_selected);
            }else{
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

















