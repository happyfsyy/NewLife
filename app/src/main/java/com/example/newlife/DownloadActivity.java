package com.example.newlife;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.service.DownloadService;
import com.example.utils.LogUtils;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class DownloadActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private DownloadService.DownloadBinder binder;
    private static final String TAG = "DownloadActivity";

    public static final int WRITE_EXTERNAL=1;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.e(TAG,"onServiceConnected");
            binder=(DownloadService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.e(TAG,"onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        Button start=findViewById(R.id.start_download);
        progressBar=findViewById(R.id.download_progress_bar);
        progressText=findViewById(R.id.download_progress_text);
        Button pause=findViewById(R.id.pause_download);
        Button cancel=findViewById(R.id.cancel_download);

        Intent intent=new Intent(DownloadActivity.this,DownloadService.class);
        startService(intent);
        bindService(intent,connection,Service.BIND_AUTO_CREATE);


        if(ContextCompat.checkSelfPermission(DownloadActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DownloadActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,WRITE_EXTERNAL);
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                binder.startDownload(url);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder.cancelDownload();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder.pauseDownload();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_EXTERNAL:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
