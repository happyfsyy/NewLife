package com.example.newlife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PaddingTestAct extends AppCompatActivity {
    private Button button1;
    private TextView textView;
    private Button button2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padding_test);
        button1=findViewById(R.id.padding_test_button1);
        textView=findViewById(R.id.padding_test_text);
        button2=findViewById(R.id.padding_test_button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int paddingTop=textView.getPaddingTop();
                textView.setPadding(0,paddingTop-10,0,0);
                LogUtils.e("paddingTop: "+textView.getPaddingTop());

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int paddingTop=textView.getPaddingTop();
                textView.setPadding(0,paddingTop+10,0,0);
                LogUtils.e("paddingTop: "+textView.getPaddingTop());
            }
        });
    }
}
