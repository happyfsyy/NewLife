package com.example.newlife;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RuntimePermissionAct extends AppCompatActivity {
    public static final int REQUEST_CALL=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);
        Button call=findViewById(R.id.make_call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(RuntimePermissionAct.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RuntimePermissionAct.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }else{
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel;10086"));
                    startActivity(intent);
                }
            }
        });

    }

    /**
     * 与onActivityResult()方法类似的处理办法
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CALL:
                //for循环判断permissions[index]=Manifest.permission.CALL_PHONE,然后获得grantResults[index]
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:10086"));
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
