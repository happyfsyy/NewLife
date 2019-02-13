package com.example.newlife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.utils.ImageHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PrimaryColorAct extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private static int MAX_VALUE=255;
    private static int MID_VALUE=127;
    private ImageView imageView;
    private SeekBar hueSeekBar;
    private SeekBar saturationSeekBar;
    private SeekBar lumSeekBar;
    private Bitmap bitmap;
    private float mHue,mSaturation,mLum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_color);
        initViews();
    }
    private void initViews(){
        imageView=findViewById(R.id.primary_imageview);
        hueSeekBar=findViewById(R.id.hue_seekbar);
        saturationSeekBar=findViewById(R.id.saturation_seekbar);
        lumSeekBar=findViewById(R.id.lum_seekbar);

        hueSeekBar.setMax(MAX_VALUE);
        saturationSeekBar.setMax(MAX_VALUE);
        lumSeekBar.setMax(MAX_VALUE);

        hueSeekBar.setProgress(MID_VALUE);
        saturationSeekBar.setProgress(MID_VALUE);
        lumSeekBar.setProgress(MID_VALUE);

        hueSeekBar.setOnSeekBarChangeListener(this);
        saturationSeekBar.setOnSeekBarChangeListener(this);
        lumSeekBar.setOnSeekBarChangeListener(this);

        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.test3);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.hue_seekbar:
                mHue=(progress-MID_VALUE)*1.0f/MID_VALUE*180;
//                mHue=progress*1.0f/MAX_VALUE*360;
                break;
            case R.id.saturation_seekbar:
                mSaturation=progress*1.0f/MID_VALUE;
//                mSaturation=progress*1.0f/MAX_VALUE;
                break;
            case R.id.lum_seekbar:
                mLum=progress*1.0f/MID_VALUE;
                break;
        }
        imageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap,mHue,mSaturation,mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
