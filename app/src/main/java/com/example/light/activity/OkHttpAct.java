package com.example.light.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestBuilder;
import com.example.light.utils.OkHttpEngine;
import com.example.light.utils.ResultCallback;
import com.example.light.utils.UrlConnManager;
import com.example.newlife.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Timeout;

public class OkHttpAct extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "OkHttpAct";
    private OkHttpClient mOkHttpClient;
    private TextView engineText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        Button getRequest=findViewById(R.id.get_request);
        Button postRequest=findViewById(R.id.post_request);
        Button uploadFile=findViewById(R.id.post_file);
        Button downFile=findViewById(R.id.down_file);
        Button cancelRequest=findViewById(R.id.cancel_request);
        Button useEngine=findViewById(R.id.use_engine_test);
        engineText=findViewById(R.id.engine_get_text);
        getRequest.setOnClickListener(this);
        postRequest.setOnClickListener(this);
        uploadFile.setOnClickListener(this);
        downFile.setOnClickListener(this);
        cancelRequest.setOnClickListener(this);
        useEngine.setOnClickListener(this);
        long ThreadId=Thread.currentThread().getId();
        Log.e(TAG,"主线程："+ThreadId);
        initClient();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_request:
                getAsyncHttp();
                break;
            case R.id.post_request:
                postAsyncHttp();
                break;
            case R.id.post_file:
                postAsyncFile();
                break;
            case R.id.down_file:
                downAsyncFile();
                break;
            case R.id.cancel_request:
                cancelRequest();
                break;
            case R.id.use_engine_test:
                useEngineTest();
                break;
        }
    }

    private void useEngineTest(){
        String url="http://blog.csdn.net/happy_fsyy";
        OkHttpEngine.getInstance(this).getAsyncHttp(url,new ResultCallback(){
            @Override
            public void onError(Request request, Exception e) {
                Log.e(TAG,"失败了");
                e.printStackTrace();
            }

            @Override
            public void onResponse(String str) {
                Log.e(TAG,"当前线程："+Thread.currentThread().getId());
//                Toast.makeText(OkHttpAct.this,"请求成功",Toast.LENGTH_SHORT).show();
                engineText.setText(str);
            }
        });
    }

    private void initClient(){
        File sdCache=getExternalCacheDir();
        int cacheSize=10*1024*1024;
        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .connectTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile(),cacheSize));
        mOkHttpClient=builder.build();
    }

    private void getAsyncHttp(){
        Request.Builder builder=new Request.Builder().url("http://blog.csdn.net/happy_fsyy");
        builder.method("GET",null);
        Request request=builder.build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call,  Response response) throws IOException {
                String responseData=response.body().string();
                Log.e(TAG,responseData);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"请求成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void postAsyncHttp(){
        RequestBody requestBody=new FormBody.Builder().add("","").build();
        Request request=new Request.Builder()
                .url("http://blog.csdn.net/happy_fsyy")
                .post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Log.e(TAG,responseData);
            }
        });
    }

    private void postAsyncFile(){
        String filePath="";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filePath=Environment.getExternalStorageDirectory().getAbsolutePath();
        }else{
            Log.e(TAG,"内存卡状态不对");
        }
        File file=new File(filePath,"hello.txt");
        MediaType contentType=MediaType.parse("text/x-markdown;charset=utf-8");
        Request request=new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(contentType,file))
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"上传文件失败咯");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Log.e(TAG,responseData);
            }
        });
    }

    private void downAsyncFile(){
        String url="https://raw.githubusercontent.com/happyfsyy/NewLife/master/app/img_folder/img1.png";
        Request request=new Request.Builder()
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"Oops");
            }

            @Override
            public void onResponse( Call  call, Response response) throws IOException {
                Log.e(TAG,"success");

                InputStream inputStream=response.body().byteStream();
                String filePath;
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    filePath=Environment.getExternalStorageDirectory().getAbsolutePath();
                }else{
                    return;
                }

                File file=new File(filePath,"ale.jpg");
                FileOutputStream outputStream=new FileOutputStream(file);
                byte[] bytes=new byte[1024];
                int len=0;
                while((len=inputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,len);
                }
                outputStream.flush();
            }
        });
    }

    private void sendMultiPart(){
        MediaType mediaType=MediaType.parse("image/png");
        String path="/sdcard/xxxxxxxxx.jpg";
        File file=new File(path);

        RequestBody requestBody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title","ale")
                .addFormDataPart("img",file.getName(),RequestBody.create(mediaType,file))
                .build();




        Request request=new Request.Builder()
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG,response.body().string());
            }
        });
    }

    private void cancelRequest(){
        Request request=new Request.Builder()
                .url("https://www.baidu.com")
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();
        Call call=mOkHttpClient.newCall(request);
        final Call finalCall=call;
        ScheduledExecutorService executorService=Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                finalCall.cancel();
            }
        },100,TimeUnit.MILLISECONDS);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"Cancel!!!!!!");
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(null!=response.cacheResponse()){
                    String str=response.cacheResponse().toString();
                    Log.e(TAG,"cache----"+str);
                }else{
                    String str=response.networkResponse().toString();
                    Log.e(TAG,"network---"+str);
                }
            }
        });
    }
}
