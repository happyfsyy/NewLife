package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newlife.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {
    private TextView titleText;
    private TextView contentText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentLayout=inflater.inflate(R.layout.news_content_layout,container,false);
        titleText=contentLayout.findViewById(R.id.news_content_title_text);
        contentText=contentLayout.findViewById(R.id.news_content_content_text);
        return contentLayout;
    }

    public void setData(String title,String content){
        titleText.setText(title);
        contentText.setText(content);
    }



}
