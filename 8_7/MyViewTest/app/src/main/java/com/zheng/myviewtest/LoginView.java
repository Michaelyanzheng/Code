package com.zheng.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by michael on 2015/8/7.
 */
public abstract class LoginView extends View {

    private MyThread mMyThread;

    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected abstract void drawSub(Canvas canvas);

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        drawSub(canvas);

        if(mMyThread == null){
            mMyThread = new MyThread();
            mMyThread.start();
        }
    }

    protected abstract void login();

    @Override
    protected void onDetachedFromWindow() {
        running = false;
        super.onDetachedFromWindow();

    }

    private boolean running = true;

    class MyThread extends Thread{

        @Override
        public void run() {

            while(running){
                login();
                postInvalidate();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
