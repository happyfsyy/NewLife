package com.example.newlife;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fragment.AnotherRightFragment;
import com.example.fragment.LeftFragment;
import com.example.fragment.RightFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentTestActivity extends BaseActivity {
    private static final String TAG = "FragmentTestActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG,">>>>>>onCreate()>>>>>>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        getDisplayMetrics();

        Fragment rightFragment=new RightFragment();
        final Fragment anotherRightFragment=new AnotherRightFragment();
        replaceFragment(rightFragment);

        Button clickHere=findViewById(R.id.fragment_left_button);
        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(anotherRightFragment);
            }
        });
    }

    private void getDisplayMetrics(){
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels=metrics.widthPixels;
        int heightPixels=metrics.heightPixels;
        float density=metrics.density;
        int dpi=metrics.densityDpi;
        int widthDp=(int)(widthPixels/density+0.5f);
        Log.e(TAG,"widthPixels:"+widthPixels);
        Log.e(TAG,"heightPixels:"+heightPixels);
        Log.e(TAG,"density:"+density);
        Log.e(TAG,"dpi:"+dpi);
        Log.e(TAG,"widthDp:"+widthDp);
    }


    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction transaction=fragmentManager.beginTransaction();
//        transaction.replace(R.id.right_layout,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,">>>>>>onStart()>>>>>>");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,">>>>>>onResume()>>>>>>");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,">>>>>>onPause()>>>>>>");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,">>>>>>onStop()>>>>>>");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,">>>>>>onDestroy()>>>>>>");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,">>>>>>onRestart()>>>>>>");
    }
}

