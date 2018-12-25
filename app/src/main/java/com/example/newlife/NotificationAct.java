package com.example.newlife;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NotificationAct extends AppCompatActivity {
    public static final int ALE_HANDSOME=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button send=findViewById(R.id.send_notification);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotificationAct.this,BroadcastLoginActivity.class);
                PendingIntent pi=PendingIntent.getActivity(NotificationAct.this,0,intent,0);
                NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification=new NotificationCompat.Builder(NotificationAct.this,"test")
                        .setContentTitle("hello ale246")
                        .setContentText("ale is handsome")
                        .setSmallIcon(R.drawable.ali_047)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.b08))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .build();
                manager.notify(ALE_HANDSOME,notification);
            }
        });
    }
}
