package com.zheng.myviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by michael on 2015/8/7.
 */
public class MyView extends View {

    private Bitmap mBitmap;

    public MyView(Context context) {
        super(context);
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aa);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aa);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setTextSize(50);

        canvas.drawText("this is a draw", 0, 50, paint);

        canvas.drawLine(0, 60, 200, 60, paint);

        canvas.drawRect(0, 100, 100, 200, paint);

        RectF rectF = new RectF(10,250,100,350);

        canvas.drawRoundRect(rectF,10.0f,10.0f,paint);

        canvas.drawCircle(50, 450, 50, paint);

        canvas.drawBitmap(mBitmap,0,500,paint);
    }
}
