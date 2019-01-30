package com.example.newlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CoordinatorLayoutTest2Act extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_test2);
        initViews();
        getParams();
    }
    private void initViews(){
        collapsingToolbarLayout=findViewById(R.id.coordinator_test_collapsing_toolbar_layout);
        toolbar=findViewById(R.id.coordinator_test_collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageView=findViewById(R.id.coordinator_test_collapsing_img);
        textView=findViewById(R.id.coordinator_test_nested_text);
    }

    private void getParams(){
        Intent intent=this.getIntent();
        int imgResId=intent.getIntExtra("imgResId",-1);
        String name=intent.getStringExtra("name");
        collapsingToolbarLayout.setTitle(name);
        Glide.with(this).load(imgResId).into(imageView);
        textView.setText(generateContent(name));

    }

    private String generateContent(String name){
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder();
        int count=random.nextInt(500)+1;
        for(int i=0;i<count;i++){
            stringBuilder.append(name);
        }
        return stringBuilder.toString();
    }
}
