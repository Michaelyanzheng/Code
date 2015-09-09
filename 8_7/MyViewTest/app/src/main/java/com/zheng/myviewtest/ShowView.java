package com.zheng.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by michael on 2015/8/7.
 */
public class ShowView extends LoginView {

    private Paint mPaint = new Paint();
    private float rx = 0;
    private float ry = 0;

    public ShowView(Context context) {
        super(context);
    }

    public ShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawSub(Canvas canvas) {

        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(30);
        canvas.drawText("zheng shouli",rx,ry,mPaint);

    }

    @Override
    protected void login() {

        rx++;
        ry++;

        if (rx > getWidth() && ry > getHeight()){
            rx = 0 - mPaint.measureText("zheng shouli");
            ry = 0;
        }

    }
}
