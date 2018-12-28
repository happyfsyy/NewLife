package com.example.newlife;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utils.LogUtils;

import androidx.annotation.Nullable;

public class ThreadTestAct extends Activity {
    public static final int CHANGE_SIGNAL=1;

    private TextView changeText;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            LogUtils.e(TAG,"UI id->"+getTaskId());
            switch (msg.what){
                case CHANGE_SIGNAL:
                    changeText.setText(hello[msg.arg1]);
                    break;
                default:
                    break;
            }
        }
    };
    private String[] hello=new String[]{"hello0","hello1","hello2","hello3","hello4",
            "hello5","hello6","hello7","hello8","hello9","hello10"};
    private int num=0;

    private static final String TAG = "ThreadTestAct";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        changeText=findViewById(R.id.thread_change_text);
        Button changeButton=findViewById(R.id.thread_change_button);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            LogUtils.e(TAG,"Thread id->"+Thread.currentThread().getId());
                            if(num<=10){
                                Message message=new Message();
                                message.what=CHANGE_SIGNAL;
                                message.arg1=num;
                                mHandler.sendMessage(message);
                                try {
                                    Thread.sleep(1000);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                num++;
                            }else{
                                break;
                            }
                        }
                    }
                }).start();

            }
        });
    }
}
