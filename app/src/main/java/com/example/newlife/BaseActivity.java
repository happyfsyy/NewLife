package com.example.newlife;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import com.example.broadcast_receiver.OfflineReceiver;
import com.example.utils.ActivityCollector;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private OfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        Log.i(TAG,getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver=new OfflineReceiver();
        IntentFilter intentFilter=new IntentFilter("com.example.newlife.FORCE_OFFLINE");
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }
}
