package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Day;
import com.example.newlife.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DayRecyclerViewAdapter extends RecyclerView.Adapter<DayRecyclerViewAdapter.DayVH> {
    private int resId;
    private List<Day> dayList;
    public DayRecyclerViewAdapter(int resId, List<Day> dayList){
        this.resId=resId;
        this.dayList=dayList;
    }


    @NonNull
    @Override
    public DayVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(resId,parent,false);
        DayVH viewHolder=new DayVH(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DayVH holder, int position) {
        holder.img.setImageResource(dayList.get(position).getResId());
        holder.name.setText(dayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    class DayVH extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;

        public DayVH(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.day_icon2);
            name=itemView.findViewById(R.id.day_name2);
        }

    }
}
