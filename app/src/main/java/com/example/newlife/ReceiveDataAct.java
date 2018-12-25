package com.example.newlife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bean.BookSerializable;
import com.example.bean.CategoryParcelable;
import com.example.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiveDataAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data);

        TextView data=findViewById(R.id.receive_data);
//        Bundle bundle=getIntent().getExtras();
//        BookSerializable book=(BookSerializable) bundle.getSerializable("book");
//        String text="name: "+book.getName()+"\n"+"price: "+book.getPrice();
        CategoryParcelable category=getIntent().getParcelableExtra("category");
        String text="name: "+category.getCategoryName()+"\n code: "+category.getCategoryCode();
        data.setText(text);
    }
}
