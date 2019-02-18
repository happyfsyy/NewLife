package com.example.newlife;

import android.os.Bundle;

import com.example.view.XfermodeView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class XferModeViewAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new XfermodeView(this));
    }
}
