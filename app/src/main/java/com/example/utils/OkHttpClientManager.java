package com.example.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientManager {
    private OkHttpClientManager clientManager;
    private OkHttpClient mClient;
    private Handler mHandler;
    private Gson gson;

    private OkHttpClientManager(Context context){
        File sdCacheFile=context.getExternalCacheDir();
        int cacheSize=10*1024*1024;
        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .readTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .connectTimeout(15,TimeUnit.SECONDS)
                .cache(new Cache(sdCacheFile,cacheSize));
        mClient=builder.build();
        mHandler=new Handler(Looper.getMainLooper());
        gson=new Gson();
    }

    public OkHttpClientManager getInstance(Context context){
        if(clientManager==null){
            synchronized (OkHttpClientManager.class){
                if(clientManager==null){
                    clientManager=new OkHttpClientManager(context);
                }
            }
        }
        return clientManager;
    }

    /**
     * 同步的get请求
     * @param url
     * @return Response
     * @throws IOException
     */
    private Response _getResponse(String url)throws IOException {
        Request request=new Request.Builder()
                .url(url).build();
        Response response=mClient.newCall(request).execute();
        return response;
    }

    /**
     * 同步的get请求
     * @param url
     * @return 字符串
     * @throws IOException
     */
    private String _getString(String url) throws IOException{
        Response response=_getResponse(url);
        return response.body().string();
    }

    private void _getAsync(String url,ResultCallback resultCallback){
        Request request=new Request.Builder().url(url).build();

    }


    private void deliverResult(final ResultCallback callback, final Request request){
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedResultCallback(request,e,callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string=response.body().string();
                if(callback.mType==String.class){
                    
                }


            }
        });
    }

    private void sendFailedResultCallback(final Request request, final Exception e, final ResultCallback callback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onError(request,e);
                }
            }
        });
    }
    private void sendSuccessResultCallback(final Object object, final ResultCallback resultCallback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(resultCallback!=null){
                    resultCallback.onSuccess(object);
                }
            }
        });
    }

    private Request buildPostRequest(String url,Param[] params){
        if(params==null){
            params=new Param[0];
        }
        FormBody.Builder builder=new FormBody.Builder();
        for(Param param:params){
            builder.add(param.key,param.value);
        }
        RequestBody requestBody=builder.build();
        return new Request.Builder().url(url).post(requestBody).build();

    }

    public static abstract class ResultCallback<T>{
        Type mType;
        public ResultCallback(){
            mType=getSuperClassTypeParam(getClass());
        }

        public abstract void onError(Request request,Exception e);
        public abstract void onSuccess(T response);
        static Type getSuperClassTypeParam(Class<?> subclass){
            Type superClass=subclass.getGenericSuperclass();
            if(superClass instanceof Class){
                throw  new RuntimeException("missing Type param");
            }
            ParameterizedType parameterizedType=(ParameterizedType)superClass;
            return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
        }
    }

    public static class Param{
        String key;
        String value;
        public Param(){

        }
        public Param(String key,String value){
            this.key=key;
            this.value=value;
        }
    }

}

