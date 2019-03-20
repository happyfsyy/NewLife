package com.example.newlife;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.utils.LogUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CameraAct extends AppCompatActivity {
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Button cameraBtn;
    private ImageView picture;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initViews();
        initListener();
        initParams();

    }
    private void initViews(){
        surfaceView=findViewById(R.id.surface_view);
        surfaceHolder=surfaceView.getHolder();
        cameraBtn=findViewById(R.id.camera_click);
        picture=findViewById(R.id.camera_picture);
    }
    private void initListener(){
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(CameraAct.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CameraAct.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    takePicture();
                }
            }
        });

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                camera=getCamera();
                if(camera!=null){
                    try{
                        camera.setPreviewDisplay(surfaceHolder);
                        camera.startPreview();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                releaseCamera();
            }
        });
    }
    private void initParams(){
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        camera=getCamera();

    }
    private void takePicture(){
        if(camera!=null){
            try{
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                picture.setImageBitmap(bitmap);

                BufferedOutputStream bufferedOutputStream=null;
                try{
                    String fileName="hey"+System.currentTimeMillis()+".jpg";
                    bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(
                            new File(Environment.getExternalStorageDirectory(),fileName)));
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,bufferedOutputStream);
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    if(bufferedOutputStream!=null){
                        try{
                            bufferedOutputStream.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    private Camera getCamera(){
        if(camera==null){
            if (ContextCompat.checkSelfPermission(CameraAct.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CameraAct.this,
                        new String[]{Manifest.permission.CAMERA},2);
            }else{
                camera=Camera.open();

            }
        }
        return camera;
    }
    private void releaseCamera(){
        if(camera!=null){
            camera.release();
            camera=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    LogUtils.e("允许权限咯");
                    takePicture();
                }
                break;
            case 2:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    LogUtils.e("允许权限咯");
                    camera=Camera.open();
                }
        }

    }
}
