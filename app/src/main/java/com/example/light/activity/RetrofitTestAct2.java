package com.example.light.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bean.IpModel;
import com.example.newlife.R;
import com.example.utils.LogUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class RetrofitTestAct2 extends AppCompatActivity {
    private Button test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }
    private void initView(){
        test=findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getIpInfo();
                getIpInfoOkHttp();
            }
        });
    }
    private void getIpInfo(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://ip.taobao.com/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IpService ipService=retrofit.create(IpService.class);
        Call<IpModel> call=ipService.getIpMsg();
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                String country=response.body().getData().getCountry();
                LogUtils.e("country",country+"");
            }
            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                LogUtils.e(t.toString());
            }
        });
    }
    private void getIpInfoOkHttp(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .connectTimeout(15,TimeUnit.SECONDS);
        OkHttpClient client=builder.build();
        RequestBody requestBody=new FormBody.Builder().add("ip","112.65.176.145").build();
        final Request request=new Request.Builder()
                .url("http://ip.taobao.com/service/getIpInfo.php")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                parseJsonWithGSON(response.body().string());
//                LogUtils.e(response.body().string());
            }
        });
    }

    public interface IpService{
        @GET("getIpInfo.php?ip=112.68.176.145")
        Call<IpModel> getIpMsg();
    }

    private void parseJsonWithGSON(String jsonData){
        Gson gson=new Gson();
        IpBean ipModel=gson.fromJson(jsonData,IpBean.class);
        LogUtils.e("code",ipModel.code+"");
    }
    class IpBean{
        int code;
        String data;
    }
}
