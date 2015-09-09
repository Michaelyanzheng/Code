package com.zheng.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by michael on 2015/8/6.
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback{

    private Paint mPaint = null;

    public MyView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        getHolder().addCallback(this);
    }

    public void draw(){
        Canvas canvas = getHolder().lockCanvas();

        canvas.drawColor(Color.WHITE);
//        canvas.drawRect(0,0,100,100,mPaint);

        canvas.save();

        canvas.rotate(90,getWidth()/2,getHeight()/2);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight(), mPaint);

//        canvas.restore();
        canvas.drawLine(0,getHeight()/2 + 100,getWidth(),getHeight()+100,mPaint);

        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        draw();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
