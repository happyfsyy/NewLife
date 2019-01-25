package com.example.newlife;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;

import com.example.view.ValueAnimatorView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ValueAnimatorAdvancedAct extends AppCompatActivity {
    private ValueAnimatorView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_adavaced);
        view=findViewById(R.id.value_animator_view);
    }
}
