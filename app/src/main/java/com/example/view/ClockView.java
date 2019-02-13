package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.graphics.Paint.FontMetrics;
import androidx.annotation.Nullable;

public class ClockView extends View {
    private Paint mPaint;
    public ClockView(Context context) {
        this(context,null);
    }
    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }
    private void initParams(){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,18,getResources().getDisplayMetrics()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int mWidth=getMeasuredWidth();
        int mHeight=getMeasuredHeight();
        float circleX=mWidth*1.0f/2;
        float circleY=mHeight*1.0f/2;
        canvas.drawCircle(circleX,circleY,circleX,mPaint);
        for(int i=0;i<60;i++){
            if(i%5==0){
                mPaint.setStrokeWidth(5);
                canvas.drawLine(circleX,circleY-circleX,circleX,circleY-circleX+60,mPaint);
                String text;
                int result=i/5;
                if(result!=0){
                    text=String.valueOf(result);
                }else{
                    text="12";
                }
                FontMetrics fontMetrics=mPaint.getFontMetrics();
                float textWidth=mPaint.measureText(text);
                float textHeight=fontMetrics.bottom-fontMetrics.top;
                canvas.drawText(text,circleX-textWidth/2,textHeight-fontMetrics.descent+circleY-circleX+60,mPaint);
            }else{
                mPaint.setStrokeWidth(3);
                canvas.drawLine(circleX,circleY-circleX,circleX,circleY-circleX+30,mPaint);
            }
            canvas.rotate(6,circleX,circleY);
        }
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(circleX,circleY,15,mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.save();
        canvas.translate(circleX,circleY);
        mPaint.setStrokeWidth(20);
        canvas.drawLine(0,0,100,100,mPaint);
        mPaint.setStrokeWidth(15);
        canvas.drawLine(0,0,0,200,mPaint);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(0,0,-250,0,mPaint);
        canvas.restore();
    }
}