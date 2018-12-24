package com.example.newlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class ForceOffLineAct extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_broadcast_main);

        final Button sendBroadcast=findViewById(R.id.send_broadcast);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.newlife.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
