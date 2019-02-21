package com.example.light.activity;

import android.os.Bundle;
import com.example.newlife.R;
import com.example.utils.LogUtils;
import java.util.List;
import java.util.Map;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class RetrofitTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getApps();
    }
    private void getApps(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.248.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AppService appService=retrofit.create(AppService.class);
//        Call<List<APP>> call=appService.getAppMsg();
//        Call<List<APP>> call=appService.getAppMsg("jsondata");
        Call<List<APP>> call=appService.getAppMsg("jsondata","get_data.json");

        call.enqueue(new Callback<List<APP>>() {
            @Override
            public void onResponse(Call<List<APP>> call, Response<List<APP>> response) {
                for(APP app:response.body()){
                    LogUtils.e("id is "+app.getId()+"\tname is "+app.getName()+"\tversion is "+app.getVersion());
                }
            }
            @Override
            public void onFailure(Call<List<APP>> call, Throwable t) {
                LogUtils.e(t.toString());
            }
        });

    }

    public interface AppService{
        @GET("get_data.json")
        Call<List<APP>> getAppMsg();
        @GET("{path}/get_data.json")
        Call<List<APP>> getAppMsg(@Path("path") String path);
        @GET("{path}/{dst}")
        Call<List<APP>> getAppMsg(@Path("path") String path,@Path("dst") String dst);
    }
    public interface QueryAppService{
        /**
         * 这个结果是查询url/get_data.json ?version=1
         */
        @GET("get_data.json")
        Call<APP> getAppMsg(@Query("version") String version);
        @GET("get_data.json")
        Call<APP> getAppMsg(@QueryMap Map<String,String> options);
    }
    class APP{
        private String id;
        private String name;
        private String version;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getVersion() {
            return version;
        }
        public void setVersion(String version) {
            this.version = version;
        }
    }
}

