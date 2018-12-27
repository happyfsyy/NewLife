package com.example.newlife;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utils.LogUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.Nullable;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkActivity extends Activity {
    private static final String TAG = "NetworkActivity";
    private TextView responseText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        responseText=findViewById(R.id.network_response_text);

        Button sendRequest=findViewById(R.id.network_send_request);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendRequestWithHttpUrlConnection();
                sendRequestWithOkHttp();
            }
        });
    }

    private void sendRequestWithHttpUrlConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try{
                    URL url=new URL("https://www.baidu.com");
                    connection=(HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("GET");
                    connection.setRequestMethod("POST");
                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("username=admin&password=123456");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream=connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try{
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("username","admin")
                            .add("password","123456")
                            .build();
                    Request request=new Request.Builder()
                            .url("http://192.168.248.2/get_data.xml")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseXMLWithPull(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(text);
            }
        });
    }

    private void parseXMLWithPull(String xmlData){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType=xmlPullParser.getEventType();
            String id="";
            String name="";
            String version="";
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName=xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
//                        LogUtils.e(TAG+"->EventType","START_TAG");
                        if("id".equals(nodeName)){
                            id=xmlPullParser.nextText();
                        }else if("name".equals(nodeName)){
                            name=xmlPullParser.nextText();
                        }else if("version".equals(nodeName)){
                            version=xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        LogUtils.e(TAG+"->EventType","END_TAG");
                        if("app".equals(nodeName)){
                            Log.e(TAG+"->id",id);
                            Log.e(TAG+"->name",name);
                            Log.e(TAG+"->versio",version);
                        }
                        break;
                    case XmlPullParser.TEXT:
//                        LogUtils.e(TAG+"->EventType","TEXT");
                        break;
                    case XmlPullParser.START_DOCUMENT:
//                        LogUtils.e(TAG+"->EventType","START_DOCUMENT");
                        break;
                    case XmlPullParser.END_DOCUMENT:
//                        LogUtils.e(TAG+"->EventType","END_DOCUMENT");
                        break;
                    default:
//                        LogUtils.e(TAG+"->EventType","others");
                        break;
                }
//                LogUtils.e(TAG+"->nodeName",nodeName);
                eventType=xmlPullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
