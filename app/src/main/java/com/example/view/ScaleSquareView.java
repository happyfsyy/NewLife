package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.example.utils.DisplayUtil;

public class ScaleSquareView extends View {
    private static final String TAG=ScaleSquareView.class.getSimpleName();
    private static final int TOTAL_SQUARE_COUNT=15;
    private int mTotalWidth;
    private int mTotalHeight;
    private int mHalfWidth;
    private int mHalfHeight;
    private Paint mPaint;
    private Rect mSquareRect;
    private int mLineWidth;
    private int mHalfLineWidth;

    public ScaleSquareView(Context context) {
        super(context);
        mLineWidth= DisplayUtil.dp2px(context,4);
        mHalfLineWidth=mLineWidth/2;
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG,"onSizeChanged");
        mTotalWidth=w;
        mTotalHeight=h;
        mHalfWidth=w/2;
        mHalfHeight=h/2;
        int top=mHalfHeight-mHalfWidth;
        mSquareRect=new Rect(mHalfLineWidth,top-mHalfLineWidth,mTotalWidth-mHalfLineWidth,
                top+mTotalWidth-mHalfLineWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=1;i<=TOTAL_SQUARE_COUNT;i++){
            canvas.save();
            float scale=(float)i/TOTAL_SQUARE_COUNT;
            canvas.scale(scale,scale,mHalfWidth,mHalfHeight);
            canvas.drawRect(mSquareRect,mPaint);
            canvas.restore();
        }
    }
}
