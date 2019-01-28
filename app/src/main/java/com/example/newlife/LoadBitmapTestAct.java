package com.example.newlife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.utils.LogUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 参考链接：https://blog.csdn.net/guolin_blog/article/details/9316683
 * 采用BitmapFactory.Options压缩图片
 */
public class LoadBitmapTestAct extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    private ImageView bigImageView;
    private int maxMemory;
    private LruCache<String,Bitmap> mMemoryCache;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bitmap);
        imageView=findViewById(R.id.load_bitmap_test_imgview);
        button=findViewById(R.id.load_bitmap_button);
        bigImageView=findViewById(R.id.load_bitmap_test_big_imageview);
        //这里虽然是将inSampleSize设置为12，设置为6，设置为4，但是图片大小好像并没有缩小
        // 而且图片设置更大，反而从profiler看到的图片内存变小了。
        //有可能是Profiler不准
        bigImageView.setImageBitmap(decodeBitmapFromResource(R.drawable.strawberry,300,300));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDifferentDpi();
            }
        });

        getScreenParams();
        getOptions();


        initMemoryCache();
    }

    /**
     * 我的这台三星，最大可使用内存是128M
     */
    private void getScreenParams(){
        float dpi=getResources().getDisplayMetrics().densityDpi;
        float xdpi=getResources().getDisplayMetrics().xdpi;
        float yDpi=getResources().getDisplayMetrics().ydpi;
        maxMemory=(int)(Runtime.getRuntime().maxMemory()/(1024*1024));
        LogUtils.e("屏幕DPI："+dpi+"\txdpi: "+xdpi+"\tydpi: "+yDpi+"\t最大可使用内存："+maxMemory+"MB");

    }

    private void getOptions(){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(getResources(),R.drawable.strawberry,options);
        int imgWidth=options.outWidth;
        int imgHeight=options.outHeight;
        LogUtils.e("图片宽度："+imgWidth+"\t图片高度："+imgHeight);
    }

    /**
     * 测量在不同dpi下，图片的大小内存
     * b08.png在xxxhdpi下的宽度是45，高度是45
     * b08.png在hdpi下的宽度是120，高度是120
     * 很明显被自动缩放了，缩放的倍数是xxxhpdi的最高dpi640，除以hdpi的最高240
     */
    private void testDifferentDpi(){
        LogUtils.e("imgView宽度："+imageView.getWidth());
        LogUtils.e("imgView高度："+imageView.getHeight());
    }
    private int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        int imgWidth=options.outWidth;
        int imgHeight=options.outHeight;
        int inSampleSize=1;
        if(imgWidth>reqWidth||imgHeight>reqHeight){
            int widthRatio=Math.round(imgWidth*1.0f/reqWidth);
            int heightRatio=Math.round(imgHeight*1.0f/reqHeight);
            //选两者间较小的比率，这样可以保证最终的图片的宽和高肯定大于等于imgview的宽和高
            //如果不是那种特长的图，我倒是建议选择大的比率，这样图片的宽和高会小于等于imgView的宽和高
            inSampleSize=widthRatio<heightRatio?widthRatio:heightRatio;
        }
        LogUtils.e("inSampleSize: "+inSampleSize);
        return inSampleSize;
    }

    private Bitmap decodeBitmapFromResource(int resId, int reqWidth, int reqHeight){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(getResources(),resId,options);
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(getResources(),resId,options);
    }

    private void initMemoryCache(){
        int cacheSize=maxMemory/8;
        mMemoryCache=new LruCache<String,Bitmap>(cacheSize){
            /** 重写此方法来衡量每张图片的大小 */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };
    }
    public void addBitmapToMemoryCache(String key,Bitmap bitmap){
        if(getBitmapFromMemoryCache(key)==null){
            mMemoryCache.put(key,bitmap);
        }
    }
    public Bitmap getBitmapFromMemoryCache(String key){
        return mMemoryCache.get(key);
    }
}
