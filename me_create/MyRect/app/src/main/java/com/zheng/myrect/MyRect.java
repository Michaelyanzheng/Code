package com.zheng.myrect;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Michael on 2015/7/31.
 */
public class MyRect extends View {


    public MyRect(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyView);

        int color = typedArray.getColor(R.styleable.MyView_rect_color,0xffff0000);
        setBackgroundColor(color);

        typedArray.recycle();

    }


        public MyRect(Context context) {
        super(context);
    }
}

















