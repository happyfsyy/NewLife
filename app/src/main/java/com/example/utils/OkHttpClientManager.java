package com.example.utils;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.newlife.MyApplication;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientManager {
    private static OkHttpClientManager clientManager;
    private OkHttpClient mClient;
    private Handler mHandler;
    private Gson gson;

    private OkHttpClientManager(){
        File sdCacheFile=MyApplication.getContext().getExternalCacheDir();
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

    public static OkHttpClientManager getInstance(){
        if(clientManager==null){
            synchronized (OkHttpClientManager.class){
                if(clientManager==null){
                    clientManager=new OkHttpClientManager();
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

    /**
     * 异步的get请求
     * @param url
     * @param resultCallback
     */
    private void _getAsync(String url,ResultCallback resultCallback){
        Request request=new Request.Builder().url(url).build();
        deliverResult(resultCallback,request);
    }

    /**
     * 同步的post请求
     * @param url
     * @param params
     * @return Response
     * @throws IOException
     */
    private Response _postResponse(String url,Param[] params) throws IOException{
        Request request=buildPostRequest(url,params);
        return mClient.newCall(request).execute();
    }

    /**
     * 同步的post请求
     * @param url
     * @param params
     * @return 字符串
     * @throws IOException
     */
    private String _postString(String url,Param[] params)throws IOException {
        Request request=buildPostRequest(url,params);
        Response response=mClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 异步的post请求，想要的结果，都在回调中获得
     * @param url
     * @param map
     * @param resultCallback
     */
    private void _postAsync(String url,Map<String,String> map,ResultCallback resultCallback){
        Param[] params=mapToParams(map);
        Request request=buildPostRequest(url,params);
        deliverResult(resultCallback,request);
    }

    /**
     * 同步基于post的文件上传
     * @param url
     * @param files
     * @param fileKeys
     * @param params
     * @return
     * @throws IOException
     */
    private Response _post(String url,File[] files,String[] fileKeys,Param...params) throws IOException{
        Request request=buildMultiPartFormRequest(url,files,fileKeys,params);
        return mClient.newCall(request).execute();
    }
    private Response _post(String url,File file,String fileKey) throws IOException{
        Request request=buildMultiPartFormRequest(url,new File[]{file},new String[]{fileKey},null);
        return mClient.newCall(request).execute();
    }
    private Response _post(String url,File file,String fileKey,Param...params) throws IOException{
        Request request=buildMultiPartFormRequest(url,new File[]{file},new String[]{fileKey},params);
        return mClient.newCall(request).execute();
    }

    /**
     * 异步基于post的文件上传
     * @param url
     * @param files
     * @param fileKeys
     * @param resultCallback
     * @param params
     */
    private void _postAsync(String url,ResultCallback resultCallback,File[]files,String[] fileKeys,Param...params){
        Request request=buildMultiPartFormRequest(url,files,fileKeys,params);
        deliverResult(resultCallback,request);
    }
    private void _postAsync(String url,ResultCallback resultCallback,File file,String fileKey){
        Request request=buildMultiPartFormRequest(url,new File[]{file},new String[]{fileKey},null);
        deliverResult(resultCallback,request);
    }
    private void _postAsync(String url,ResultCallback resultCallback,File file,String fileKey,Param...params){
        Request request=buildMultiPartFormRequest(url,new File[]{file},new String[]{fileKey},params);
        deliverResult(resultCallback,request);
    }



    //**************对外公布的方法**************
    public static Response getResponse(String url) throws IOException{
        return getInstance()._getResponse(url);
    }
    public static String getString(String url) throws IOException{
        return getInstance()._getString(url);
    }

    public static void getAsync(String url,ResultCallback resultCallback){
        getInstance()._getAsync(url,resultCallback);
    }

    public static Response postResponse(String url,Param[] params) throws IOException{
        return getInstance()._postResponse(url,params);
    }
    public static String postString(String url,Param[] params) throws IOException{
        return getInstance()._postString(url,params);
    }

    public static void postAsync(String url,Map<String,String> map,ResultCallback resultCallback){
        getInstance()._postAsync(url,map,resultCallback);
    }

    public static Response post(String url,File[] files,String[]fileKeys,Param...params) throws IOException{
        return getInstance()._post(url,files,fileKeys,params);
    }
    public static Response post(String url,File file,String fileKey)throws IOException{
        return getInstance()._post(url,file,fileKey);
    }
    public static Response post(String url,File file,String fileKey,Param...params)throws IOException{
        return getInstance()._post(url,file,fileKey,params);
    }

    public static void postAsync(String url,ResultCallback resultCallback,File[]files,String[]fileKeys,Param...params){
        getInstance()._postAsync(url,resultCallback,files,fileKeys,params);
    }
    public static void postAsync(String url,ResultCallback resultCallback,File file,String fileKey){
        getInstance()._postAsync(url,resultCallback,file,fileKey);
    }
    public static void postAsync(String url,ResultCallback resultCallback,File file,String fileKey,Param...params){
        getInstance()._postAsync(url,resultCallback,file,fileKey,params);
    }

    /**
     * 将结果传入回调之中，因为okHttp是在子线程中运行的，
     * 所以为了将回调的结果与ui关联起来，这里就采用handler实现
     *
     * @param callback
     * @param request
     */
    private void deliverResult(final ResultCallback callback, final Request request){
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedResultCallback(request,e,callback);
            }

            @Override
            public void onResponse(Call call, Response response){
                try{
                    String responseData=response.body().string();
                    if(callback.mType==String.class){
                        sendSuccessResultCallback(responseData,callback);
                    }else{
                        Object o=gson.fromJson(responseData,callback.mType);
                        sendSuccessResultCallback(o,callback);
                    }
                }catch (IOException|com.google.gson.JsonParseException e){
                    sendFailedResultCallback(request,e,callback);
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
    private void sendSuccessResultCallback(final Object object, final ResultCallback callback){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onSuccess(object);
                }
            }
        });
    }

    private Request buildMultiPartFormRequest(String url,File[] files,String[] fileKeys,Param[] params){
        params=validateParam(params);
        MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for(Param param:params){
            builder.addFormDataPart(param.key,param.value);
        }
        if(files!=null){
            File file;
            String fileName;
            String contentType;
            for(int i=0;i<files.length;i++){
                file=files[i];
                fileName=file.getName();
                contentType=guessMIMEType(fileName);
                builder.addFormDataPart(fileKeys[i],fileName,
                        RequestBody.create(MediaType.parse(contentType),file));
            }
        }
        RequestBody requestBody=builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }

    private String guessMIMEType(String fileName){
        FileNameMap fileNameMap=URLConnection.getFileNameMap();
        String contentTypeFor=fileNameMap.getContentTypeFor(fileName);
        if(contentTypeFor==null){
            contentTypeFor="application/octet-stream";
        }
        return contentTypeFor;
    }

    private Param[] validateParam(Param[] params){
        if(params==null) return new Param[0];
        else return params;
    }

    private Request buildPostRequest(String url,Param[] params){
        params=validateParam(params);

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

    private Param[] mapToParams(Map<String,String> map){
        if(map==null) return new Param[0];
        int size=map.size();
        Param[] params=new Param[size];
        Set<Map.Entry<String,String>> entries=map.entrySet();
        int i=0;
        for(Map.Entry<String,String> entry:entries){
            params[i++]=new Param(entry.getKey(),entry.getValue());
        }
        return params;
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

