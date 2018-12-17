package com.example.listener;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnItemLongClickListener {
    void onItemLongClickListener(RecyclerView parent, View view,int position);
}
