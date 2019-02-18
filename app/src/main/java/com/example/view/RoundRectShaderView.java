package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.newlife.R;

import androidx.annotation.Nullable;

public class RoundRectShaderView extends View {
    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;
    private Paint mPaint;
    private int width,height;
    public RoundRectShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mBitmapShader=new BitmapShader(mBitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        mPaint=new Paint();
        mPaint.setShader(mBitmapShader);
//        mPaint.setShader(new LinearGradient(0,0,400,400, Color.BLUE,Color.YELLOW,Shader.TileMode.REPEAT));
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int imgWidth=mBitmap.getWidth();
        int imgHeight=mBitmap.getHeight();
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else{
            width=Math.min(widthSize,imgWidth);
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else{
            height=Math.min(heightSize,imgHeight);
        }
        setMeasuredDimension(500,500);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(200,200,200,mPaint);
//        canvas.drawRect(0,0,500,500,mPaint);
    }
}
