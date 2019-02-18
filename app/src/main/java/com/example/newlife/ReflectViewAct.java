package com.example.newlife;

import android.os.Bundle;

import com.example.view.ReflectView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReflectViewAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ReflectView(this));
    }
}
