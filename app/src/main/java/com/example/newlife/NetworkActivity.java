package com.example.newlife;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.listener.HttpCallbackListener;
import com.example.utils.HttpUtils;
import com.example.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
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
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import androidx.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
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

    private void sendHttpRequest(){
        String address="https://www.baidu.com";
        HttpUtils.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
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
                            .url("http://192.168.248.2/get_data.json")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
//                    parseXMLWithPull(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseJsonWithJSONObject(responseData);
                    parseJsonWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendOkHttpRequest(){
        String address="https://www.baidu.com";
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
            }
        });
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

    private void parseXMLWithSAX(String xmlData){
        try{
            SAXParserFactory factory=SAXParserFactory.newInstance();
            XMLReader xmlReader=factory.newSAXParser().getXMLReader();
            xmlReader.setContentHandler(new ContentHandler());
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseJsonWithJSONObject(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String version = jsonObject.getString("version");
                String name = jsonObject.getString("name");
                LogUtils.e(TAG, "id->" + id);
                LogUtils.e(TAG, "version->" + version);
                LogUtils.e(TAG, "name->" + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJsonWithGSON(String jsonData) {
        Gson gson=new Gson();
        List<App> apps=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for(App app:apps){
            LogUtils.e(TAG,"id->"+app.getId());
            LogUtils.e(TAG,"version->"+app.getVersion());
            LogUtils.e(TAG,"name->"+app.getName());
        }
    }

    class App{
        private String id;
        private String version;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
