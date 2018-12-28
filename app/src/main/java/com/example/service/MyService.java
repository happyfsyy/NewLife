package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.utils.LogUtils;

import androidx.annotation.Nullable;

public class MyService extends Service{
    private static final String TAG = "MyService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 服务第一次创建的时候调用。
     */
    @Override
    public void onCreate() {
        LogUtils.e(TAG,"onCreate()");
    }

    /**
     * 每次服务启动的时候都会调用。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e(TAG,"onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁的时候调用。
     */
    @Override
    public void onDestroy() {
        LogUtils.e(TAG,"onDestroy()");
    }
}
