package com.zheng.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by michael on 2015/8/7.
 */
public class MichaelView extends LoginView {

    private RectF mRectF = new RectF(0,60,100,160);
    private float sweepAngle = 0;

    private Paint mPaint = new Paint();
    private float rx = 0;

    public MichaelView(Context context) {
        super(context);
    }

    public MichaelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawSub(Canvas canvas) {

        mPaint.setTextSize(30);
        canvas.drawText("michael yanzheng", rx, 30, mPaint);
        canvas.drawArc(mRectF, 0, sweepAngle, true, mPaint);
    }

    @Override
    protected void login() {
        rx +=1;
        sweepAngle++;

        mPaint.setARGB(255, (int) rx, (int) rx, (int) rx);

        if (rx > getWidth()){
            rx = 0 - mPaint.measureText("michael yanzheng");
        }
        if (sweepAngle > 360){
            sweepAngle = 0;
        }

    }
}
