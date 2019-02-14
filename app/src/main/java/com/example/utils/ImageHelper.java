package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
    public static Bitmap handleImageNegative(Bitmap src){
        int width=src.getWidth();
        int height=src.getHeight();
        int color;
        int r,g,b,a;
        Bitmap bitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        int[] oldPx=new int[width*height];
        int[] newPx=new int[width*height];
        src.getPixels(oldPx,0,width,0,0,width,height);
        for(int i=0;i<width*height;i++){
            color=oldPx[i];
            r= Color.red(color);
            g=Color.green(color);
            b=Color.blue(color);
            a=Color.alpha(color);
            r=255-r;
            g=255-g;
            b=255-b;
            if(r>255){
                r=255;
            }else if(r<0){
                r=0;
            }
            if(g>255){
                g=255;
            }else if(g<0){
                g=0;
            }
            if(b>255){
                b=255;
            }else if(b<0){
                b=0;
            }
            newPx[i]=Color.argb(a,r,g,b);
        }
        bitmap.setPixels(newPx,0,width,0,0,width,height);
        return bitmap;
    }
    public static Bitmap handleImageOldPhoto(Bitmap src){
        Bitmap bitmap=Bitmap.createBitmap(src.getWidth(),src.getHeight(), Bitmap.Config.ARGB_8888);
        int width=src.getWidth();
        int height=src.getHeight();
        int color;
        int a,r,g,b,r1,g1,b1;

        int[] oldPx=new int[width*height];
        int[] newPx=new int[width*height];
        src.getPixels(oldPx,0,src.getWidth(),0,0,width,height);
        for(int i=0;i<width*height;i++){
            color=oldPx[i];
            a=Color.alpha(color);
            r=Color.red(color);
            g=Color.green(color);
            b=Color.blue(color);

            r1=(int)(0.393*r+0.769*g+0.189*b);
            g1=(int)(0.349*r+0.686*g+0.168*b);
            b1=(int)(0.272*r+0.534*g+0.131*b);
            if(r1>255){
                r1=255;
            }
            if(g1>255){
                g1=255;
            }
            if(b1>255){
                b1=255;
            }
            newPx[i]=Color.argb(a,r1,g1,b1);
        }
        bitmap.setPixels(newPx,0,width,0,0,width,height);
        return bitmap;
    }
    public static Bitmap handleImageRelief(Bitmap src){
        Bitmap des=Bitmap.createBitmap(src.getWidth(),src.getHeight(), Bitmap.Config.ARGB_8888);
        int width=src.getWidth();
        int height=src.getHeight();
        int bColor,cClor;
        int a,r,g,b,a1,r1,g1,b1;
        int newr,newg,newb;
        int[] oldPx=new int[width*height];
        int[] newPx=new int[width*height];
        src.getPixels(oldPx,0,width,0,0,width,height);
        for(int i=1;i<width*height;i++){
            cClor=oldPx[i-1];
            a=Color.alpha(cClor);
            r=Color.red(cClor);
            g=Color.green(cClor);
            b=Color.blue(cClor);

            bColor=oldPx[i];
            a1=Color.alpha(bColor);
            r1=Color.red(bColor);
            b1=Color.blue(bColor);
            g1=Color.green(bColor);

//            newr=r-r1+127;
//            newg=g-g1+127;
//            newb=b-b1+127;
            newr=r1-r+127;
            newg=g1-g+127;
            newb=b1-b+127;

            if(newr>255){
                newr=255;
            }
            if(newg>255){
                newg=255;
            }
            if(newb>255){
                newb=255;
            }
            newPx[i]=Color.argb(a1,newr,newg,newb);
        }
        des.setPixels(newPx,0,width,0,0,width,height);
        return des;
    }
}
