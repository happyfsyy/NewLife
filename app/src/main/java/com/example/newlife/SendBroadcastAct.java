package com.example.newlife;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.broadcast_receiver.MyBroadcastReceiver;

import androidx.annotation.Nullable;

/**
 * 发送标准广播和有序广播的Activity
 * 8.0以后，静态注册的广播已经废了。。。
 */
public class SendBroadcastAct extends Activity {

    private MyBroadcastReceiver receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_broadcast_main);
//        receiver=new MyBroadcastReceiver();
//        IntentFilter intentFilter=new IntentFilter();
//        intentFilter.addAction("com.example.newlife.MY_BROADCAST");
//        registerReceiver(receiver,intentFilter);
        Button sendBroadcast=findViewById(R.id.send_broadcast);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.newlife.MY_BROADCAST");
//                intent.setComponent(new ComponentName("com.example.broadcast_receiver","com.example.broadcast_receiver.MyBroadcastReceiver"));
                sendBroadcast(intent);
//                sendOrderedBroadcast(intent,null);


            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
    }
}
