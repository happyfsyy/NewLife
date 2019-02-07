package com.example.newlife;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.DisplayUtil;
import com.example.view.ScaleSquareView;
import com.example.view.ScaleView;
import com.example.view.TranslateView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CanvasTestAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new TranslateView(this));
//        setContentView(new ScaleView(this));
        setContentView(new ScaleSquareView(this));
    }
}
