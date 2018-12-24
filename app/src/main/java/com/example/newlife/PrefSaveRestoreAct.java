package com.example.newlife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PrefSaveRestoreAct extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static String PREF_NAME="pref_person";
    private static final String TAG = "PrefSaveRestoreAct";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref_save_restore);
        Button save=findViewById(R.id.pref_save_button);
        Button restore=findViewById(R.id.pref_restore_button);

        preferences=getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor=preferences.edit();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name","ale246");
                editor.putInt("age",27);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=preferences.getString("name","");
                int age=preferences.getInt("age",-1);
                boolean isMarried=preferences.getBoolean("married",false);
                Log.e(TAG,name);
                Log.e(TAG,age+"");
                Log.e(TAG,isMarried+"");

            }
        });
    }
}
