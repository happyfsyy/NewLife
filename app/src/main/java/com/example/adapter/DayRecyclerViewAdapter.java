package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.Day;
import com.example.listener.OnClickListener;
import com.example.listener.OnItemClickListener;
import com.example.listener.OnItemLongClickListener;
import com.example.newlife.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DayRecyclerViewAdapter extends RecyclerView.Adapter<DayRecyclerViewAdapter.DayVH> {
    private int resId;
    private List<Day> dayList;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnClickListener mOnClickListener;


    public DayRecyclerViewAdapter(int resId, List<Day> dayList){
        this.resId=resId;
        this.dayList=dayList;
    }

    public void setOnItemClickListener(OnItemClickListener onClickListener){
        mOnItemClickListener=onClickListener;
    }
    public void setOnItemLongClick(OnItemLongClickListener onLongClickListener){
        mOnItemLongClickListener=onLongClickListener;
    }
    public void setOnClickListener(OnClickListener onClickListener){
        mOnClickListener=onClickListener;
    }

    @NonNull
    @Override
    public DayVH onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view=LayoutInflater.from(parent.getContext()).inflate(resId,parent,false);
        final DayVH viewHolder=new DayVH(view);
//        viewHolder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"you have clicked the item"+viewHolder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        viewHolder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(),dayList.get(viewHolder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
//            }
//        });

        if(mOnItemClickListener!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick((RecyclerView)parent,view,viewHolder.getAdapterPosition());
                }
            });
        }
        if(mOnItemLongClickListener!=null){
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClickListener((RecyclerView)parent,view,viewHolder.getAdapterPosition());
                    return true;
                }
            });
        }
        if(mOnClickListener!=null){
            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onClick(viewHolder.img,viewHolder.getAdapterPosition());
                }
            });
        }




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
        View view;

        public DayVH(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            img=itemView.findViewById(R.id.day_icon2);
            name=itemView.findViewById(R.id.day_name2);
        }

    }
}
