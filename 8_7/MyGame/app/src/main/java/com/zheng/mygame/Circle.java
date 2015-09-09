package com.zheng.mygame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by michael on 2015/8/6.
 */
public class Circle extends Contanier {

    private Paint mPaint = null;

    public Circle(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
    }

    @Override
    public void childrenView(Canvas canvas) {
        super.childrenView(canvas);
        canvas.drawCircle(50,50,50,mPaint);
    }
}
