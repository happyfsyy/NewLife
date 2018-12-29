package com.example.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import com.example.newlife.MainActivity;
import com.example.newlife.R;
import com.example.newlife.ServiceTestAct;
import com.example.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service{
    private static final String TAG = "MyService";
    private DownloadBinder downloadBinder=new DownloadBinder();
    public static final int REQUEST_HELLO=1;

    /**
     * 当Context调用bindService()方法来获取一个服务的持久连接之后，这时就会回调服务中的OnBind()方法。
     * 如果服务之前没有创建过，onCraete()方法会先于onBind()方法执行。
     * 之后调用方就可以获得返回的IBinder对象的实例，这样就能自由地和服务进行通信了。
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.e(TAG,"onBind()");
        return downloadBinder;
    }

    /**
     * 服务第一次创建的时候调用。
     */
    @Override
    public void onCreate() {
        LogUtils.e(TAG,"onCreate()");
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,REQUEST_HELLO,intent,0);


        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=26){
            NotificationChannel notificationChannel=new NotificationChannel("123","1234",NotificationManager.IMPORTANCE_DEFAULT);
//            manager.createNotificationChannel(notificationChannel);
        }
        Notification notification=new NotificationCompat.Builder(this,"123")
                .setContentText("hello")
                .setContentTitle("h")
                .setSmallIcon(R.drawable.b08)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.b08))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
            manager.notify(REQUEST_HELLO,notification);
//        startForeground(REQUEST_HELLO,notification);

    }

    /**
     * 每次服务启动的时候都会调用。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e(TAG,"onStartCommand()");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                stopSelf();
//            }
//        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁的时候调用。
     * StopService()，stopSelf()和unbindService()都会使服务销毁，并回调onDestroy()。
     */
    @Override
    public void onDestroy() {
        LogUtils.e(TAG,"onDestroy()");
    }

    public class DownloadBinder extends Binder{
        public void startDownload(){
            LogUtils.e(TAG,"startDownload()");
        }
        public void getProgress(){
            LogUtils.e(TAG,"getProgress()");
        }
    }
}
