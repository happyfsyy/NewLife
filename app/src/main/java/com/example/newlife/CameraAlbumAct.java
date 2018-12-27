package com.example.newlife;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.utils.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

public class CameraAlbumAct extends AppCompatActivity {
    private static final String TAG = "CameraAlbumAct";
    public static final int TAKE_PHOTO=1;
    public static final int CHOOSE_PHOTO=2;


    private ImageView showImageView;
    private String mPhotoPath;
    private Uri photoUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_album);

        showImageView=findViewById(R.id.imageview_show);
        Button takePhoto=findViewById(R.id.take_photo);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍照方法和以前相同，但是6.0加入了运行时权限，需要申请授权。
                //检查用户是否已经授权，如果没有，就申请授权；否则直接拍照。
                if(ContextCompat.checkSelfPermission(CameraAlbumAct.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CameraAlbumAct.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},TAKE_PHOTO);
                }else{
                    takePhoto();
//                    takeThumbnailPhoto();
                }
            }
        });

        final Button choosePhoto=findViewById(R.id.choose_photo);
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查授权
                if(ContextCompat.checkSelfPermission(CameraAlbumAct.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CameraAlbumAct.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},CHOOSE_PHOTO);
                }else{
                    choosePhoto();
                }
            }
        });
    }

    /**
     * 获得拍照的缩略图
     */
    private void takeThumbnailPhoto(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //resolveActivity()，这个方法的意思就是返回一个可以处理这个Intent的Activity，参数是PackageManger
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,TAKE_PHOTO);
        }
    }
    private void setThumbnailBitmap(@Nullable Intent data){
        if(data==null) return;
        Bundle extras=data.getExtras();
        Bitmap bitmap=(Bitmap)extras.get("data");
        showImageView.setImageBitmap(bitmap);
    }

    /**
     * 根据自定义的文件名创建File
     */
    private File createFile(){
        String file_name="img_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".jpg";
        //将图片放入手机SD卡的应用关联缓存目录下，不需要进行运行时权限处理
//        File dir=getExternalCacheDir();
//        File dir=Environment.getExternalStorageDirectory();
//        File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File dir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        LogUtils.e(TAG+"->dir",dir.getAbsolutePath());
        File temp=new File(dir,file_name);
        //判断文件是否存在
        try{
            if(temp.exists()){
                temp.delete();
            }
            temp.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        mPhotoPath=temp.getPath();
        LogUtils.e(TAG+"->absolutePath",temp.getAbsolutePath());
        LogUtils.e(TAG+"->path",temp.getPath());
        return temp;
    }
    private void takePhoto(){
        File outputImg=createFile();
        //7.0以后使用本地真实路径的Uri被认为是不安全的，会抛出异常；
        //调用FileProvider的getUriForFile()方法将File对象转换成为一个封装过的Uri对象
        if(Build.VERSION.SDK_INT<24){
            photoUri=Uri.fromFile(outputImg);
        }else{
            photoUri=FileProvider.getUriForFile(this,getPackageName()+".fileprovider",outputImg);
        }
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,TAKE_PHOTO);
        }
    }

    private void setBitmap(){
        int targetWidth=showImageView.getWidth();
        int targetHeight=showImageView.getHeight();

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(mPhotoPath,options);
        int photoWidth=options.outWidth;
        int photoHeight=options.outHeight;

        int inSampleSize=1;
        if(photoWidth>targetWidth||photoHeight>targetHeight){
            int widthRatio=Math.round((float)photoWidth/targetWidth);
            int heightRatio=Math.round((float)photoHeight/targetHeight);
            inSampleSize=Math.min(widthRatio,heightRatio);
        }

        options.inSampleSize=inSampleSize;
        options.inJustDecodeBounds=false;
        Bitmap bitmap=BitmapFactory.decodeFile(mPhotoPath,options);
        showImageView.setImageBitmap(bitmap);
    }

    /**
     * 从图库中选择图片
     */
    private void choosePhoto(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    private void handleImage(@Nullable Intent data){
        if(data==null) return;
        Uri imgUri=data.getData();
        LogUtils.e(TAG+"->imguri",imgUri.getPath());
        LogUtils.e(TAG+"->imguri",imgUri.toString());

        //加入UCrop裁剪的时候可以这样写，不然容易OOM
//        Bitmap bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri));
//        showImageView.setImageBitmap(bitmap);
        if(DocumentsContract.isDocumentUri(this,imgUri)){
            String docId=DocumentsContract.getDocumentId(imgUri);
            if("com.android.providers.media.documents".equals(imgUri.getAuthority())){
                String id=docId.split(":")[1];
                String selection=MediaStore.Images.Media._ID+"="+id;
                mPhotoPath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(imgUri.getAuthority())){
                Uri contentUri=ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                mPhotoPath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(imgUri.getScheme())){
            mPhotoPath=getImagePath(imgUri,null);
        }else if("file".equalsIgnoreCase(imgUri.getScheme())){
            mPhotoPath=imgUri.getPath();
        }
    }

    private String getImagePath(@NonNull Uri uri,@Nullable String selection){
        String photoPath=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                photoPath=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return photoPath;
    }

    private void galleryAddPic(){
        Intent scanIntent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(photoUri);
        sendBroadcast(scanIntent);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    setBitmap();
//                    setThumbnailBitmap(data);
                    galleryAddPic();
                }
                break;
            case CHOOSE_PHOTO:
                handleImage(data);
                setBitmap();
                break;
            default:
                break;
        }
    }

    /**
     * 请求授权的回调方法
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    takePhoto();
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case CHOOSE_PHOTO:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    choosePhoto();
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
