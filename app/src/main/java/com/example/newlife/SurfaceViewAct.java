package com.example.newlife;

import android.os.Bundle;

import com.example.view.SimpleDraw;
import com.example.view.SinView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SurfaceViewAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new SinView(this));
        setContentView(new SimpleDraw(this));
    }

}
