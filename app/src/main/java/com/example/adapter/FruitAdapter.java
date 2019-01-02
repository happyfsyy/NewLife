package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.Fruit;
import com.example.newlife.FruitActivity;
import com.example.newlife.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adapter.BaseAdapter;


public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {
    private List<Fruit> mList;
    private Context mContext;
    class FruitViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImg;
        TextView fruitName;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitImg=itemView.findViewById(R.id.fruit_img);
            fruitName=itemView.findViewById(R.id.fruit_name);
        }
    }
    public FruitAdapter(List<Fruit> mList){
        this.mList=mList;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View itemView=LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
        return new FruitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FruitViewHolder holder, int position) {
        Fruit fruit=mList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImgId()).into(holder.fruitImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position1=holder.getLayoutPosition();
                Fruit fruit1=mList.get(position1);
                Intent intent=new Intent(mContext,FruitActivity.class);
                intent.putExtra("fruit_name",fruit1.getName());
                intent.putExtra("fruit_img_id",fruit1.getImgId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
