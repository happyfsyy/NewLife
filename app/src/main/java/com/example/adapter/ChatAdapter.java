package com.example.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.Message;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newlife.R;

public class ChatAdapter extends BaseAdapter<Message>{

    class ChatViewHolder extends BaseViewHolder{
        LinearLayout leftLayout;
        TextView leftText;
        LinearLayout rightLayout;
        TextView rightText;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            leftLayout=itemView.findViewById(R.id.chat_left_layout);
            rightLayout=itemView.findViewById(R.id.chat_right_layout);
            leftText=itemView.findViewById(R.id.chat_left_text);
            rightText=itemView.findViewById(R.id.chat_right_text);
        }
    }

    public ChatAdapter(List<Message> list, int itemResId) {
        super(list, itemResId);
    }

    @Override
    BaseViewHolder onCreate(View itemView) {
        return new ChatViewHolder(itemView);
    }

    @Override
    void onBind(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ChatViewHolder){
            ChatViewHolder viewHolder=(ChatViewHolder)holder;
            int type=mList.get(position).getType();
            if(type==Message.RECEIVE_TYPE){
                viewHolder.leftLayout.setVisibility(View.VISIBLE);
                viewHolder.rightLayout.setVisibility(View.GONE);
                viewHolder.leftText.setText(mList.get(position).getContent());
            }else{
                viewHolder.leftLayout.setVisibility(View.GONE);
                viewHolder.rightLayout.setVisibility(View.VISIBLE);
                viewHolder.rightText.setText(mList.get(position).getContent());
            }
        }
    }
}
