package com.example.newlife;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.utils.HookClickListenerUtils;
import com.example.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JavaTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        Button test=findViewById(R.id.test_button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("测试第一个按钮");
            }
        });
        Button test2=findViewById(R.id.test_button2);
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("测试第二个按钮");
            }
        });

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup decorView=(ViewGroup)getWindow().getDecorView();
        HookClickListenerUtils.getInstance().hookDecorViewClick(decorView);
    }
}
