package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Map;

public class ScaleView extends View {
    private Paint mPaint;
    public ScaleView(Context context) {
        super(context);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        //第一种情况，采用scale(sx,sy)
//        canvas.drawRect(0,0,400,400,mPaint);
//        mPaint.setColor(Color.YELLOW);
//        canvas.scale(0.5f,0.5f);
//        canvas.drawRect(0,0,400,400,mPaint);

        //第二种情况，采用scale(sx,sy,px,py)
        canvas.drawRect(0,0,400,400,mPaint);

        canvas.save();
        canvas.scale(0.5f,0.5f);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,400,400,mPaint);
        canvas.restore();

        canvas.scale(0.5f,0.5f,200f,200f);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0,0,400,400,mPaint);
    }
}
