package com.example.newlife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.plattysoft.leonids.ParticleSystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ParticleAct extends AppCompatActivity {
    private Button test1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        test1=findViewById(R.id.test_button);
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                meteor();
                bomb();
//                flower();
//                ripple();
            }
        });
    }
    private void meteor(){
        new ParticleSystem(ParticleAct.this, 10, R.drawable.star1, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 30, 80)
                .emit(0, 0, 1, 10000);
        new ParticleSystem(ParticleAct.this, 10, R.drawable.star2, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.4f, 30, 80)
                .emit(-100, 100, 1, 10000);
        new ParticleSystem(ParticleAct.this, 10, R.drawable.star2, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 30, 80)
                .emit(-100, 100, 1, 10000);
    }
    private void bomb(){
        new ParticleSystem(ParticleAct.this, 1000, R.drawable.flower0, 3000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 360)
                .setRotationSpeed(30)
                .setAcceleration(0, 90)
                .oneShot(test1, 200);
    }
    private void flower(){
        new ParticleSystem(ParticleAct.this, 1000, R.drawable.flower0, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                .setRotationSpeed(60)
                .setAcceleration(0.00005f, 90)
                .emit(0, -100, 30, 10000);
        new ParticleSystem(ParticleAct.this, 1000, R.drawable.flower1, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                .setRotationSpeed(30)
                .setAcceleration(0.00005f, 90)
                .emit(0, -100, 30, 10000);


        new ParticleSystem(ParticleAct.this, 1000, R.drawable.flower3, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                .setRotationSpeed(60)
                .setAcceleration(0.00005f, 90)
                .emit(0, -100, 30, 10000);
        new ParticleSystem(ParticleAct.this, 1000, R.drawable.flower4, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                .setRotationSpeed(80)
                .setAcceleration(0.00005f, 90)
                .emit(0, -100, 30, 10000);
        new ParticleSystem(ParticleAct.this, 1000, R.drawable.flower5, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                .setRotationSpeed(10)
                .setAcceleration(0.00005f, 90)
                .emit(0, -100, 30, 10000);
        new ParticleSystem(ParticleAct.this, 1000, R.drawable.flower6, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                .setRotationSpeed(50)
                .setAcceleration(0.00005f, 90)
                .emit(0, -100, 30, 10000);
    }
    private void ripple(){
        new ParticleSystem(ParticleAct.this, 1000, R.drawable.flower1, 5000)
                .setSpeedModuleAndAngleRange(0.05f, 0.05f, 0, 360)
                .setRotationSpeed(30)
                .setAcceleration(0, 90)
                .oneShot(test1, 300);
    }
}
