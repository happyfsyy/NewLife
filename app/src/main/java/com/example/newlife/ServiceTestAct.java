package com.example.newlife;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.service.MyIntentService;
import com.example.service.MyService;
import com.example.utils.LogUtils;

import androidx.annotation.Nullable;

public class ServiceTestAct extends Activity {
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(MyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private static final String TAG = "ServiceTestAct";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        LogUtils.e(TAG,"onCreate");

        Button start=findViewById(R.id.service_start_button);
        Button stop=findViewById(R.id.service_stop_button);
        Button bind=findViewById(R.id.service_bind_button);
        Button unbind=findViewById(R.id.service_unbind_button);
        Button startIntentService=findViewById(R.id.service_start_intent_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceTestAct.this,MyService.class);
                startService(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceTestAct.this,MyService.class);
                stopService(intent);
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceTestAct.this,MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

        startIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e(TAG,"UI id is "+Thread.currentThread().getId());
                Intent intent=new Intent(ServiceTestAct.this,MyIntentService.class);
                startService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG,"onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e(TAG,"onResume()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e(TAG,"onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.e(TAG,"onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.e(TAG, "onStop()");
    }
}
