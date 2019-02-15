package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.example.newlife.R;

import androidx.annotation.Nullable;

public class ImageMatrixView extends View {
    private Matrix mMatrix;
    private Bitmap mBitmap;
    public ImageMatrixView(Context context) {
        super(context);
        initView();
    }
    public ImageMatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImageMatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.test3);
        setBitmapAndMatrix(bitmap,new Matrix());
    }
    public void setBitmapAndMatrix(Bitmap bitmap,Matrix matrix){
        this.mBitmap=bitmap;
        this.mMatrix=matrix;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,0,0,null);
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }
}
