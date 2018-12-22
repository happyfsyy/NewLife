package com.example.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newlife.R;


public class TabFragment extends Fragment{

    public static TabFragment newInstance(int index){
        TabFragment tabFragment=new TabFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("index",index);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainLayout=inflater.inflate(R.layout.tab_fragment_main,null);
        TextView contentText=mainLayout.findViewById(R.id.content_text);
        Bundle bundle=getArguments();
        String content=String.format(getResources().getString(R.string.tab_content),bundle.getInt("index"));
        contentText.setText(content);
        return mainLayout;
    }
}