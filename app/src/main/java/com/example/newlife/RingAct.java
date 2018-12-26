package com.example.newlife;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RingAct extends AppCompatActivity {
    private  MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ring);
        RingtoneManager manager=new RingtoneManager(this);
        manager.setType(RingtoneManager.TYPE_ALARM);
        manager.getCursor();
        Uri ringUri=manager.getRingtoneUri(1);

        mediaPlayer=new MediaPlayer();
        mediaPlayer.reset();
        try{
            mediaPlayer.setDataSource(this,ringUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }catch (Exception e){
            e.printStackTrace();
        }

        Button stop=findViewById(R.id.stop_ring);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }
}
