package com.zheng.myviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by michael on 2015/8/7.
 */
public class ZhengView extends LoginView {

    private Paint mPaint = new Paint();

    private int lineNum = 0;
    private boolean xScroll = false;

    private int mx = 0;

    public ZhengView(Context context) {
        super(context);
    }

    public ZhengView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ZhengView);

        lineNum = typedArray.getInt(R.styleable.ZhengView_lineNum,1);
        xScroll = typedArray.getBoolean(R.styleable.ZhengView_scroll,false);

        typedArray.recycle();
    }

    @Override
    protected void drawSub(Canvas canvas) {

        int textSize = 0;
        for (int i = 0; i < lineNum; i++){

            textSize = 30 + i;
            mPaint.setTextSize(textSize);
            canvas.drawText("zheng",mx,textSize+textSize*i,mPaint);

        }

    }

    @Override
    protected void login() {

        if (xScroll){
            mx = mx + 3;
            if (mx > getWidth()){
                mx = (int) - mPaint.measureText("zheng");
            }
        }

    }
}
