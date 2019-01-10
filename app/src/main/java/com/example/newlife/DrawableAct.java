package com.example.newlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DrawableAct extends AppCompatActivity {
    private static final String TAG = "DrawableAct";
    private ImageView testImgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        ImageView button=findViewById(R.id.button5);
        TransitionDrawable drawable=(TransitionDrawable)getDrawable(R.drawable.transition);
        button.setImageDrawable(drawable);
        drawable.setCrossFadeEnabled(true);
        drawable.startTransition(2000);

        final Button toogle=findViewById(R.id.button6);
//        toogle.setImageLevel(0);
        toogle.getBackground().setLevel(0);
        toogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogle.getBackground().setLevel(1);
            }
        });

        testImgView=findViewById(R.id.button8);
        rToBitmap1();

        DisplayMetrics displayMetrics=this.getResources().getDisplayMetrics();
        float density=displayMetrics.density;
        int densityDpi=displayMetrics.densityDpi;
        float scaledDensity=displayMetrics.scaledDensity;
        float xdpi=displayMetrics.xdpi;
        int widthPixels=displayMetrics.widthPixels;
        LogUtils.e(TAG,"density: "+density);
        LogUtils.e(TAG,"densityDpi: "+densityDpi);
        LogUtils.e(TAG,"scaledDensity: "+scaledDensity);
        LogUtils.e(TAG,"xdpi: "+xdpi);
        LogUtils.e(TAG,"widthPixels: "+widthPixels);


        ImageView button9=findViewById(R.id.button9);
        Drawable drawable1=button9.getDrawable();
        drawable1.setLevel(drawable1.getLevel()+4000);
    }

    private void rToDrawable(){
        Resources resources=this.getResources();
        Drawable drawable=resources.getDrawable(R.drawable.b08);
        testImgView.setImageDrawable(drawable);
    }

    private void rToBitmap1(){
        Resources resources=this.getResources();
        InputStream inputStream=resources.openRawResource(R.drawable.b08);
        BitmapDrawable bitmapDrawable=new BitmapDrawable(resources,inputStream);
        Bitmap bitmap=bitmapDrawable.getBitmap();
        testImgView.setImageBitmap(bitmap);
    }
    private void rToBitmap2(){
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.b08);
    }
    private void rToBitmap3(){
        InputStream inputStream=getResources().openRawResource(R.drawable.b08);
        Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
    }

    private Bitmap drawableToBitmap(Drawable drawable){
        if(drawable instanceof BitmapDrawable){
            return ((BitmapDrawable)drawable).getBitmap();
        }else if (drawable instanceof NinePatchDrawable){
            //透明或者半透明采用ARGB8888，否则采用RGB565
            Bitmap bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),
                    drawable.getOpacity()!=PixelFormat.OPAQUE?Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565);
            //用指定的bitmap构建一个画布canvas
            Canvas canvas=new Canvas(bitmap);
            //为drawable指定一个边界长方形。当draw()方法调用之后，drawable将会画在其中。
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            //setBounds之后，将drawable画在其中。canvas是指将要画入drawable的画布。
            drawable.draw(canvas);
            return bitmap;
        }else{
            return null;
        }
    }

    private Drawable bitmapToDrawable(Bitmap bitmap){
        return new BitmapDrawable(getResources(),bitmap);
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        //写一个压缩版本的bitmap到指定的输出流。bitmap可以重新构建，通过传入一个相应的输入流到BitmapFactory.decodeStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        try{
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    private Bitmap byteToBitmap(byte[] bytes){
        if(bytes.length!=0){
            Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            return bitmap;
        }else{
            return null;
        }
    }

    private int dp2px(int dpValue){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,getResources().getDisplayMetrics());
    }

    private int sp2px(int spValue){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spValue,getResources().getDisplayMetrics());
    }
}
