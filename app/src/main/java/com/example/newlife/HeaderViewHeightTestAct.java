package com.example.newlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HeaderViewHeightTestAct extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_view_height_test);
        listView=findViewById(R.id.header_view_height_test_listView);
        List<String> data=new ArrayList<>();
        for(int i=0;i<20;i++){
            data.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        View headerView= LayoutInflater.from(this).inflate(R.layout.header_view_height_test_header,null);
        listView.addHeaderView(headerView);
        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View child0=listView.getChildAt(0);
                View child1=listView.getChildAt(1);
                if(child0!=null&&child1!=null){
                    LogUtils.e("top0: "+child0.getTop()+"\tchild0Height: "+child0.getHeight()+
                            "\ttop1: "+child1.getTop()+"\tchild1Height: "+child1.getHeight());
                }
                LogUtils.e("getScrollY(): "+listView.getScrollY());
            }
        });
    }
}
