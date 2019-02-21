package com.example.light.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bean.IpModel;
import com.example.newlife.R;
import com.example.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
                getIpInfo();
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
    public interface IpService{
        @GET("getIpInfo.php?ip=112.68.176.145")
        Call<IpModel> getIpMsg();
    }
}
