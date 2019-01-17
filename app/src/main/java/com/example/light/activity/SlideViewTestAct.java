package com.example.light.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

import com.example.light.view.CustomView;
import com.example.newlife.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SlideViewTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_view_test);
        CustomView view=findViewById(R.id.custom_view);
//        view.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));
//        ObjectAnimator.ofFloat(view,"translationX",0,300).setDuration(1000).start();
//        view.smoothScrollTo(-400,0);

    }
}
