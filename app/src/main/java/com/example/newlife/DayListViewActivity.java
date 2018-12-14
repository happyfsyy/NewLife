package com.example.newlife;

import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.DayAdapter;
import com.example.bean.Day;

import java.util.ArrayList;
import java.util.List;

public class DayListViewActivity extends BaseActivity {
    private ListView listView;
    private List<Day> dayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_listview);


        listView=findViewById(R.id.listView);
        initData();
        DayAdapter adapter=new DayAdapter(this,R.layout.day_item,dayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DayListViewActivity.this, dayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData(){
        dayList=new ArrayList<>();
        for(int i=0;i<2;i++) {
            Day day1 = new Day("Day1", R.drawable.day1);
            Day day2 = new Day("Day2", R.drawable.day2);
            Day day3 = new Day("Day3", R.drawable.day3);
            Day day4 = new Day("Day4", R.drawable.day4);
            Day day5 = new Day("Day5", R.drawable.day5);
            Day day6 = new Day("Day6", R.drawable.day6);
            Day day7 = new Day("Day7", R.drawable.day7);
            Day day8 = new Day("Day8", R.drawable.day8);
            Day day9 = new Day("Day9", R.drawable.day9);
            Day day10 = new Day("Day10", R.drawable.day10);
            Day day11 = new Day("Day11", R.drawable.day11);
            Day day12 = new Day("Day12", R.drawable.day12);
            Day day13 = new Day("Day13", R.drawable.day13);
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
