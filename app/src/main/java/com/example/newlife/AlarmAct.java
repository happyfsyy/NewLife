package com.example.newlife;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.broadcast_receiver.AlarmReceiver;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Button alarm=findViewById(R.id.alarm_button);

//        IntentFilter intentFilter=new IntentFilter();
//        intentFilter.addAction("com.example.newlife.ALARM");
//        AlarmReceiver receiver=new AlarmReceiver();
//        registerReceiver(receiver,intentFilter);
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR_OF_DAY);
                int minute=calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog=new TimePickerDialog(AlarmAct.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.e("AlarmAct","onTimeSet");
                        Calendar c=Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);

                        Intent intent=new Intent("com.example.newlife.ALARM");
                        intent.setPackage(getPackageName());
                        PendingIntent pendingIntent=PendingIntent.getBroadcast(AlarmAct.this,0,intent,0);
                        AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                        manager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);

                    }
                },hour,minute,true);
                dialog.show();
            }
        });
    }
}
