package com.example.newlife;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.DayRecyclerViewAdapter;
import com.example.bean.Day;
import com.example.listener.OnClickListener;
import com.example.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class DayRecyclerViewActivity extends BaseActivity {
    private List<Day> dayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_recyclerview);
        initData();
        RecyclerView recyclerView;
        recyclerView=findViewById(R.id.recyclerView);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DayRecyclerViewAdapter adapter=new DayRecyclerViewAdapter(R.layout.day_recycler_view_item,dayList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(DayRecyclerViewActivity.this, "setOnItemClick"+dayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(DayRecyclerViewActivity.this, "setOnClickListener"+dayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<length;i++){
            stringBuilder.append(name);
        }
        return stringBuilder.toString();
    }
    private void initData(){
        dayList=new ArrayList<>();
        for(int i=0;i<2;i++) {
            Day day1 = new Day(getRandomLengthName("Day1"), R.drawable.day1);
            Day day2 = new Day(getRandomLengthName("Day2"), R.drawable.day2);
            Day day3 = new Day(getRandomLengthName("Day3"), R.drawable.day3);
            Day day4 = new Day(getRandomLengthName("Day4"), R.drawable.day4);
            Day day5 = new Day(getRandomLengthName("Day5"), R.drawable.day5);
            Day day6 = new Day(getRandomLengthName("Day6"), R.drawable.day6);
            Day day7 = new Day(getRandomLengthName("Day7"), R.drawable.day7);
            Day day8 = new Day(getRandomLengthName("Day8"), R.drawable.day8);
            Day day9 = new Day(getRandomLengthName("Day9"), R.drawable.day9);
            Day day10 = new Day(getRandomLengthName("Day10"), R.drawable.day10);
            Day day11 = new Day(getRandomLengthName("Day11"), R.drawable.day11);
            Day day12 = new Day(getRandomLengthName("Day12"), R.drawable.day12);
            Day day13 = new Day(getRandomLengthName("Day13"), R.drawable.day13);
            dayList.add(day1);
            dayList.add(day2);
            dayList.add(day3);
            dayList.add(day4);
            dayList.add(day5);
            dayList.add(day6);
            dayList.add(day7);
            dayList.add(day8);
            dayList.add(day9);
            dayList.add(day10);
            dayList.add(day11);
            dayList.add(day12);
            dayList.add(day13);
        }
    }
}
