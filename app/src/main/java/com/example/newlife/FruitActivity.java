package com.example.newlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FruitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_main);
        Intent intent=getIntent();
        String fruitName=intent.getStringExtra("fruit_name");
        int fruitImgId=intent.getIntExtra("fruit_img_id",0);

        Toolbar toolbar=findViewById(R.id.fruit_toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(fruitName);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ImageView fruitImg=findViewById(R.id.fruit_image_view);
        Glide.with(this).load(fruitImgId).into(fruitImg);

        TextView fruitContentText=findViewById(R.id.fruit_content_text);
        fruitContentText.setText(generateFruitContent(fruitName));
    }

    private String generateFruitContent(String fruitName){
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<300;i++){
            stringBuilder.append(fruitName);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
