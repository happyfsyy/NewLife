package com.example.light.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.light.utils.UrlConnManager;
import com.example.newlife.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HttpUrlConAct extends AppCompatActivity {
    private static final String TAG = "HttpUrlConAct";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        useHttpUrlConnectionPostThread();
    }
    private void useHttpUrlConnectionPostThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                useHttpUrlConnectionPost("https://blog.csdn.net/happy_fsyy");
            }
        }).start();
    }

    private void useHttpUrlConnectionPost(String url){
        InputStream mInputStream=null;
        HttpURLConnection mHttpURLConnection=UrlConnManager.getHttpURLConnection(url);
        try {
//            List<NameValuePair> postParams=new ArrayList<>();
//            postParams.add(new BasicNameValuePair("ip","59.108.54.37"));
//            UrlConnManager.postParams(mHttpURLConnection.getOutputStream(),postParams);
            mHttpURLConnection.connect();
            mInputStream=mHttpURLConnection.getInputStream();
            int code=mHttpURLConnection.getResponseCode();
            String response=convertStreamToString(mInputStream);
            Log.e(TAG,"请求状态码："+code+"\n请求结果：\n"+response);
            mInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder=new StringBuilder();
        String line;
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        String response=stringBuilder.toString();
        return response;
    }
}

