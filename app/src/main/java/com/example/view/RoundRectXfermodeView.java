package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.example.newlife.R;

import androidx.annotation.Nullable;

public class RoundRectXfermodeView extends View {
    private Bitmap mBitmap;
    private Bitmap mOut;
    private Paint mPaint;
    private int width;
    private int height;
    public RoundRectXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    private void initView(){
        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mOut=Bitmap.createBitmap(mBitmap.getHeight(),mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(mOut);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
//        canvas.drawRoundRect(0,0,mBitmap.getWidth(),mBitmap.getHeight(),80,80,mPaint);
        canvas.drawCircle(mBitmap.getHeight()/2,mBitmap.getHeight()/2,mBitmap.getHeight()/2,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap,0,0,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int imgWidth=mOut.getWidth();
        int imgHeight=mOut.getHeight();
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else{
            width=Math.min(imgWidth,widthSize);
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else{
            height=Math.min(imgHeight,heightSize);
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mOut,0,0,null);
    }
}
