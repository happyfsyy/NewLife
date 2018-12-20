package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listener.OnItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> mList;
    private int mItemResId;
    private OnItemClickListener mItemListener;

    public void setOnItemClickListener(OnItemClickListener itemListener){
        mItemListener=itemListener;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder{
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public BaseAdapter(List<T> list,int itemResId){
        mList=list;
        mItemResId=itemResId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(mItemResId,parent,false);
        return onCreate(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        onBind(holder,holder.getLayoutPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.onItemClick(null,v,holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    abstract BaseViewHolder onCreate(View itemView);
    abstract void onBind(RecyclerView.ViewHolder holder,int position);
}
