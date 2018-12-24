package com.example.newlife;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.broadcast_receiver.LocalBroadcastReceiver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class LocalBroadcastAct extends AppCompatActivity {
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceiver receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_broadcast_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        Button button=findViewById(R.id.send_broadcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.newlife.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.newlife.LOCAL_BROADCAST");
        receiver=new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(receiver);
    }
}
