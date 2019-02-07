package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.newlife.R;

/**
 * 参考：https://blog.csdn.net/tianjian4592/article/details/45234419
 */
public class TranslateView extends View {
    private Paint mPaint;
    public TranslateView(Context context) {
        super(context);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.translate(100,100);
        canvas.drawRect(0,0,400,400,mPaint);
        canvas.translate(100,100);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0,0,400,400,mPaint);
    }
}
