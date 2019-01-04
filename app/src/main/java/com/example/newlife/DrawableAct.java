package com.example.newlife;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DrawableAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        ImageView button=findViewById(R.id.button5);
        TransitionDrawable drawable=(TransitionDrawable)getDrawable(R.drawable.transition);
        button.setImageDrawable(drawable);
        drawable.setCrossFadeEnabled(true);
        drawable.startTransition(2000);

        final Button toogle=findViewById(R.id.button6);
//        toogle.setImageLevel(0);
        toogle.getBackground().setLevel(0);
        toogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogle.getBackground().setLevel(1);
            }
        });
    }
}
