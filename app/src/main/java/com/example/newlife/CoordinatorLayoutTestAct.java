package com.example.newlife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CoordinatorLayoutTestAct extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<Day> dayList;
    private DayAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        init();
    }
    private void init(){
        initToolBar();
        initRecyclerView();
//        initListView();
    }
    private void initToolBar(){
        toolbar=findViewById(R.id.coordinator_test_toolbar);
        setSupportActionBar(toolbar);
    }
    private void initListView(){
        ListView listView=findViewById(R.id.coordinator_test_listview);
        final List<String> data=new ArrayList<>();
        for(int i=0;i<20;i++){
            data.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }
    private void initRecyclerView(){
        recyclerView=findViewById(R.id.coordinator_test_recyclerview);
        generateDataList();
        adapter=new DayAdapter(dayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
    private void generateDataList(){
        dayList=new ArrayList<>();
        Random random=new Random();
        for(int i=0;i<30;i++){
            int j=random.nextInt(13)+1;
            int id=getResources().getIdentifier("day"+j,"drawable",getPackageName());
            String text="day"+i;
            Day day=new Day(id,text);
            dayList.add(day);
        }
    }

    class DayAdapter extends RecyclerView.Adapter<DayViewHolder>{
        private List<Day> dayList;
        private Context mContext;
        public DayAdapter(List<Day> dayList){
            this.dayList=dayList;
        }

        @NonNull
        @Override
        public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(mContext==null){
                mContext=parent.getContext();
            }
            View itemView= LayoutInflater.from(mContext).inflate(R.layout.cooridnator_recyclerview_item,
                    parent,false);
            return new DayViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final DayViewHolder holder, int position) {
            Day day=dayList.get(position);
            holder.dayText.setText(day.getDayText());
            holder.dayImg.setImageResource(day.getImgResId());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(CoordinatorLayoutTestAct.this,CoordinatorLayoutTest2Act.class);
                    intent.putExtra("imgResId",dayList.get(holder.getLayoutPosition()).imgResId);
                    intent.putExtra("name",dayList.get(holder.getLayoutPosition()).dayText);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dayList.size();
        }
    }
    class DayViewHolder extends RecyclerView.ViewHolder{
        ImageView dayImg;
        TextView dayText;
        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayImg=itemView.findViewById(R.id.coordinator_day_img);
            dayText=itemView.findViewById(R.id.coordinator_day_text);
        }
    }
    class Day{
        int imgResId;
        String dayText;
        public Day(int imgResId,String dayText){
            this.imgResId=imgResId;
            this.dayText=dayText;
        }
        public int getImgResId(){
            return imgResId;
        }
        public String getDayText(){
            return dayText;
        }
    }

}
