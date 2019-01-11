package com.example.light.utils;

import android.content.Context;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpEngine {
    private static volatile OkHttpEngine mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    private OkHttpEngine(Context context){
        File sdCacheFile=context.getExternalCacheDir();
        int cacheSize=10*1024*1024;
        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .connectTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .cache(new Cache(sdCacheFile,cacheSize));
        mOkHttpClient=builder.build();
        mHandler=new Handler();
    }

    public static OkHttpEngine getInstance(Context mContext){
        if(mInstance==null){
            synchronized (OkHttpEngine.class){
                if(mInstance==null){
                    mInstance=new OkHttpEngine(mContext);
                }
            }
        }
        return mInstance;
    }

    public void getAsyncHttp(String url, final ResultCallback resultCallback){
        final Request request=new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                resultCallback.onError(call.request(),e);
                sendFailedCallback(call.request(),e,resultCallback);
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
//                sendSuccessCallback(response.body().string(),resultCallback);
//                resultCallback.onResponse(response.body().string());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            resultCallback.onResponse(response.body().string());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void sendFailedCallback(final Request request, final Exception e, final ResultCallback callback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onError(request,e);
                }
            }
        });
    }

    private void sendSuccessCallback(final String str, final ResultCallback resultCallback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(resultCallback!=null){
                    resultCallback.onResponse(str);
                }
            }
        });
    }



}
