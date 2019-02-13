package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ImageHelper {
    public static Bitmap handleImageEffect(Bitmap src,float hue,float saturation,float lum){
        Bitmap bitmap=Bitmap.createBitmap(src.getWidth(),src.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        Paint mPaint=new Paint();

        ColorMatrix hueMatrix=new ColorMatrix();
        hueMatrix.setRotate(0,hue);
        hueMatrix.setRotate(1,hue);
        hueMatrix.setRotate(2,hue);

        ColorMatrix saturationMatrix=new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix=new ColorMatrix();
        lumMatrix.setScale(lum,lum,lum,1);

        ColorMatrix imageMatrix=new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        mPaint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(src,0,0,mPaint);
        return bitmap;
    }
}
