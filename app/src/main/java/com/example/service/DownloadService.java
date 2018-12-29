package com.example.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import com.example.listener.DownloadListener;
import com.example.listener.ProgressListener;
import com.example.newlife.DownloadActivity;
import com.example.newlife.DownloadTask;
import com.example.newlife.R;
import com.example.utils.LogUtils;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class DownloadService extends Service {
    private DownloadListener listener;
    private DownloadBinder binder;
    private DownloadTask downloadTask;
    private static final String TAG = "DownloadService";
    private String downloadUrl;
    private ProgressListener progressListener;


    @Override
    public void onCreate() {
        LogUtils.e(TAG,"onCreate()");
        listener=new DownloadListener() {
            @Override
            public void onSuccess() {
                downloadTask=null;
                downloadUrl=null;
                stopForeground(true);

                if(Build.VERSION.SDK_INT>=26){
                    getNotificationManger().createNotificationChannel(getNotificationChannel());
                }
                getNotificationManger().notify(DownloadTask.TYPE_SUCCESS,getNotification("success",-1));

                Toast.makeText(DownloadService.this,"success",Toast.LENGTH_SHORT).show();

                stopSelf();
            }

            @Override
            public void onFail() {
                downloadTask=null;
                downloadUrl=null;
                stopForeground(true);
                if(Build.VERSION.SDK_INT>=26){
                    getNotificationManger().createNotificationChannel(getNotificationChannel());
                }
                getNotificationManger().notify(DownloadTask.TYPE_FAILED,getNotification("fail",-1));
                Toast.makeText(DownloadService.this,"fail",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(int progress) {
                if(Build.VERSION.SDK_INT>=26){
                    getNotificationManger().createNotificationChannel(getNotificationChannel());
                }
                getNotificationManger().notify(DownloadTask.TYPE_PROGRESS,getNotification("Downloading...",progress));
//                progressListener.onProgress(progress);
                Intent intent=new Intent("com.example.newlife.download");
                intent.putExtra("progress",progress);
                sendBroadcast(intent);
            }

            @Override
            public void onCancel() {
                stopForeground(true);
                Toast.makeText(DownloadService.this,"Cancel Download",Toast.LENGTH_SHORT).show();

                File file=DownloadTask.getFile(downloadUrl);
                if(file.exists()){
                    file.delete();
                }
                downloadTask=null;
                downloadUrl=null;
            }

            @Override
            public void onPause() {
                downloadTask=null;
                Toast.makeText(DownloadService.this,"Pause Download",Toast.LENGTH_SHORT).show();
            }
        };

        binder=new DownloadBinder();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.e(TAG,"onBind()");
        return binder;
    }


    public class DownloadBinder extends Binder{
        public void startDownload(String url){
            if(downloadTask==null){
                downloadTask=new DownloadTask(listener);
                downloadUrl=url;
                downloadTask.execute(downloadUrl);
                startForeground(DownloadTask.TYPE_PROGRESS,getNotification("Downloading...",0));
            }
        }
        public void cancelDownload(){
            if(downloadTask!=null) {
                downloadTask.cancelDownload();
            }else{
                if(downloadUrl!=null){
                    stopForeground(true);
                    File file=DownloadTask.getFile(downloadUrl);
                    if(file.exists()){
                        file.delete();
                    }
                }
            }

        }
        public void pauseDownload(){
            if(downloadTask!=null){
                downloadTask.pauseDownload();
            }
        }

        public void setListener(ProgressListener listener){
            progressListener=listener;
        }
    }

    private NotificationManager getNotificationManger(){
        return (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title,int progress){
        String id="channel1";
        Intent intent=new Intent(this,DownloadActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,id);
        builder.setSmallIcon(R.drawable.b08);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.b08));
        builder.setContentTitle(title);
        builder.setContentIntent(pi);
        builder.setAutoCancel(true);
        if(progress>0){
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }

    private NotificationChannel getNotificationChannel(){
        if(Build.VERSION.SDK_INT>=26){
            String id="channel1";
            String name="download_channel";
            int importance=NotificationManager.IMPORTANCE_DEFAULT;
            return new NotificationChannel(id,name,importance);
        }
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e(TAG,"onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtils.e(TAG,"onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.e(TAG,"onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        LogUtils.e(TAG,"onRebind()");
        super.onRebind(intent);
    }


}
