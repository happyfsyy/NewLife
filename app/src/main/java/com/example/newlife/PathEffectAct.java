package com.example.newlife;

import android.os.Bundle;

import com.example.view.PathEffectView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PathEffectAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathEffectView(this));
    }
}
