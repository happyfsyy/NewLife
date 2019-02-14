package com.example.newlife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.utils.ImageHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PixelsEffectAct extends AppCompatActivity {
    private ImageView img1,img2,img3,img4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_effect);

        img1=findViewById(R.id.pixels_img1);
        img2=findViewById(R.id.pixels_img2);
        img3=findViewById(R.id.pixels_img3);
        img4=findViewById(R.id.pixels_img4);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.test1);
        img1.setImageBitmap(bitmap);
        img2.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        img3.setImageBitmap(ImageHelper.handleImageOldPhoto(bitmap));
        img4.setImageBitmap(ImageHelper.handleImageRelief(bitmap));
    }
}
