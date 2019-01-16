package com.example.newlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.light.activity.OkHttpAct;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TransitionStartAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_start);
        Button start=findViewById(R.id.transition_start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TransitionStartAct.this,TransitionEndAct.class);
                startActivity(intent);
//                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
    }
}
