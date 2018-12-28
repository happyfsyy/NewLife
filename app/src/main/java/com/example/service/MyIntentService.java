package com.example.service;

import android.app.IntentService;
import android.content.Intent;

import com.example.utils.LogUtils;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public MyIntentService() {
        super("MyIntentService");
        LogUtils.e(TAG,"MyIntentService()");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        LogUtils.e(TAG,"id is "+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e(TAG,"onDestroy()");
    }
}
