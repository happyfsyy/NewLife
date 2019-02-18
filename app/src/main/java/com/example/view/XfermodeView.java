package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.newlife.R;

import androidx.annotation.Nullable;

public class XfermodeView extends View {
    private Bitmap mBgBitmap;
    private Bitmap mFgBitmap;
    private Paint mPaint;
    private Path mPath;
    private Canvas mCanvas;

    public XfermodeView(Context context) {
        super(context);
        initParams(context);
    }
    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initParams(context);
    }
    private void initParams(Context context){
        mBgBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.test);
        mPaint=new Paint();
        mPaint.setAlpha(0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        mPath=new Path();
        mFgBitmap=mBgBitmap.createBitmap(mBgBitmap.getWidth(),mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas=new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBitmap,0,0,null);
        canvas.drawBitmap(mFgBitmap,0,0,null);
    }
}
