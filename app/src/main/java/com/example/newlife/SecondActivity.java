package com.example.newlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String data=getIntent().getStringExtra("extra_data");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
