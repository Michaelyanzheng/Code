package com.zheng.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by michael on 2015/8/6.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Contanier mContanier;
    private Rect mRect;
    private Circle mCircle;

    public GameView(Context context) {
        super(context);

        mContanier = new Contanier();
        mRect = new Rect();
        mCircle = new Circle();

        mRect.addChildrenView(mCircle);
        mContanier.addChildrenView(mRect);

        getHolder().addCallback(this);
    }

    public void draw(){

        Canvas canvas = getHolder().lockCanvas();

        canvas.drawColor(Color.WHITE);
        mContanier.draw(canvas);

        getHolder().unlockCanvasAndPost(canvas);
    }

    private Timer mTimer;
    private TimerTask mTimerTask;

    public void startTimer(){
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {

                draw();
            }
        };
        mTimer.schedule(mTimerTask,100,1000);
    }

    public void stopTimer(){
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        startTimer();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        stopTimer();
    }
}
