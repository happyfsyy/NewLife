package com.example.newlife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fragment.NewsContentFragment;

import androidx.annotation.Nullable;

public class NewsContentActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        NewsContentFragment fragment=(NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.news_content_content_fragment);
        String title=getIntent().getStringExtra("title");
        String content=getIntent().getStringExtra("content");
        fragment.setData(title,content);
    }

    public static void startAct(Context context,String title, String content){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }
}
