package com.zheng.myanimation;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Michael on 2015/8/1.
 */
public class MyAnimation extends Animation {

    private String TAG = "MyAnimation";

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {

        Log.d(TAG,"initialize");

        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        Log.d(TAG,interpolatedTime+"");

//        t.setAlpha(interpolatedTime);

//        t.getMatrix().setTranslate(200*interpolatedTime,200*interpolatedTime);

        t.getMatrix().setTranslate((float)Math.sin(interpolatedTime*20)*15,0);

        super.applyTransformation(interpolatedTime, t);
    }
}
