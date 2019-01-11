package com.example.light.utils;

import android.text.TextUtils;

import org.apache.http.NameValuePair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * 代码来源于Android进阶之光那本书
 *
 */
public class UrlConnManager {
    public static HttpURLConnection getHttpURLConnection(String url){
        HttpURLConnection mHttpURLConnection=null;
        try{
            URL mUrl=new URL(url);
            mHttpURLConnection=(HttpURLConnection)mUrl.openConnection();
            mHttpURLConnection.setConnectTimeout(8000);
            mHttpURLConnection.setReadTimeout(8000);
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(true);
            mHttpURLConnection.setRequestProperty("Connection","Keep-Alive");
        }catch (Exception e){
            e.printStackTrace();
        }
        return mHttpURLConnection;
    }

    public static void postParams(OutputStream outputStream, List<NameValuePair>paramsList) throws IOException{
        StringBuilder mStringBuilder=new StringBuilder();
        for(NameValuePair pair:paramsList){
            if(!TextUtils.isEmpty(mStringBuilder)){
                mStringBuilder.append("&");
            }
            mStringBuilder.append(URLEncoder.encode(pair.getName(),"UTF-8"));
            mStringBuilder.append("=");
            mStringBuilder.append(URLEncoder.encode(pair.getValue(),"UTF-8"));
        }
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        bufferedWriter.write(mStringBuilder.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
