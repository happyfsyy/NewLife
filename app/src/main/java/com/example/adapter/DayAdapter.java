package com.example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Day;
import com.example.newlife.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DayAdapter extends ArrayAdapter<Day> {
    private static final String TAG = ">>>>>>>DayAdapter<<<<<<";
    private  int resId;
    public DayAdapter(Context context,int resId,List<Day> dayList){
        super(context,resId,dayList);
        this.resId=resId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Day day=getItem(position);
        View view;
        DayViewHolder viewHolder;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resId,null);
            viewHolder=new DayViewHolder();
            viewHolder.icon=view.findViewById(R.id.day_icon);
            viewHolder.name=view.findViewById(R.id.day_name);
            view.setTag(viewHolder);
            Log.i(TAG,"convertView==null");
        }else{
            view=convertView;
            viewHolder=(DayViewHolder)view.getTag();
        }
        viewHolder.icon.setImageResource(day.getResId());
        viewHolder.name.setText(day.getName());
        return  view;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    public class DayViewHolder{
        ImageView icon;
        TextView name;
    }
}
