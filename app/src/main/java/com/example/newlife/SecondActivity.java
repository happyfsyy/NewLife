package com.example.newlife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.utils.ActivityCollector;

public class SecondActivity extends BaseActivity {
    private static final String TAG = "SecondActivity";
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        String data=getIntent().getStringExtra("extra_data");
//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        button2=findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.putExtra("return_data","I'm from SecondAct");
//                setResult(RESULT_OK,intent);
//                finish();
                ActivityCollector.finishAll();
//                android.os.Process.killProcess(android.os.Process.myPid());
//                finish();
            }
        });
    }

    /**
     *
     * @param context context
     * @param data1 objectId
     * @param data2 user.name
     */

    public static void startAct(Context context,String data1,String data2){
        Intent intent=new Intent(context,SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("return_data","I'm from SecondAct");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy()");
    }
}
