package com.zheng.mygame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by michael on 2015/8/6.
 */
public class Rect extends Contanier {

    private Paint mPaint = null;

    public Rect(){

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    public void childrenView(Canvas canvas) {
        super.childrenView(canvas);
        canvas.drawRect(0,0,100,100,mPaint);
        this.setY(this.getY() + 1);
    }
}
