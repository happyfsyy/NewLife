package com.example.newlife;

import android.os.AsyncTask;
import android.os.Environment;

import com.example.listener.DownloadListener;
import com.example.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String,Integer,Integer> {
    public static final int TYPE_SUCCESS=1;
    public static final int TYPE_FAILED=2;
    public static final int TYPE_PROGRESS=3;
    public static final int TYPE_CANCELED=4;
    public static final int TYPE_PAUSED=5;

    private int lastProgress=0;
    private DownloadListener listener;
    private boolean isPause=false;
    private boolean isCancel=false;

    public DownloadTask(DownloadListener listener){
        this.listener=listener;
    }

    @Override
    protected Integer doInBackground(String... urls) {
        InputStream inputStream=null;
        RandomAccessFile savedFile=null;
        File file=null;
        try{
            String downloadUrl=urls[0];
            long downloadLength=0;
            long contentLength=getContentLength(downloadUrl);
            file=getFile(downloadUrl);
            if(file.exists()){
                downloadLength=file.length();
            }
            if(contentLength==0){
                return TYPE_FAILED;
            }else if(contentLength==downloadLength){
                return TYPE_SUCCESS;
            }

            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .addHeader("RANGE","bytes="+downloadLength+"-")
                    .url(downloadUrl)
                    .build();
            Response response=client.newCall(request).execute();

            savedFile=new RandomAccessFile(file,"rw");
            savedFile.seek(downloadLength);
            inputStream=response.body().byteStream();

            byte[]b=new byte[1024];
            long total=downloadLength;
            int len;
            while((len=inputStream.read(b))!=-1){
                if(isCancel){
                    return TYPE_CANCELED;
                }else if(isPause){
                    return TYPE_PAUSED;
                }else{
                    total+=len;
                    savedFile.write(b,0,len);
                    int progress=(int)((total*1.0/contentLength)*100);
                    publishProgress(progress);
                }
            }
            response.body().close();
            return TYPE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(inputStream!=null){
                    inputStream.close();
                }
                if(savedFile!=null){
                    savedFile.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress=values[0];
        if(progress>lastProgress){
            listener.onProgress(progress);
            lastProgress=progress;
        }
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch (result){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFail();
                break;
            case TYPE_CANCELED:
                listener.onCancel();
                break;
            case TYPE_PAUSED:
                listener.onPause();
                break;
            default:
                break;
        }
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(downloadUrl).build();
        Response response=client.newCall(request).execute();
        if(response.isSuccessful()){
            long contentLength=response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }

    public void cancelDownload(){
        isCancel=true;
    }
    public void pauseDownload(){
        isPause=true;
    }

    public static File getFile(String url){
        File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        LogUtils.e("DownloadTask",dir.getPath());
        String fileName=url.substring(url.lastIndexOf("/"));

        return new File(dir,fileName);
    }
}
