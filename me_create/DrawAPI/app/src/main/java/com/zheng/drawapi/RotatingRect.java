package com.zheng.drawapi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Michael on 2015/7/31.
 */
public class RotatingRect extends View {

    private Paint mPaint;

    public RotatingRect(Context context) {
        super(context);
        initProperties();
    }

    public RotatingRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProperties();
    }

    public RotatingRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initProperties();
    }

    private void initProperties(){

        mPaint = new Paint();
        mPaint.setColor(Color.RED);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.save();
        canvas.translate(200,200);
        canvas.rotate(degress,50,50);

        canvas.drawRect(0, 0, 100, 100, mPaint);
        degress--;
        canvas.restore();
        Log.d(MainActivity.TAG,"----------------->>>>>>>>>>>>>>>"+degress);

        invalidate();

    }
    private float degress = 0;
}
