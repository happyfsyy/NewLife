package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.example.newlife.R;
import androidx.annotation.Nullable;

public class FlagBitmapMeshView extends View {
    private final int  WIDTH=200;
    private final int HEIGHT=200;
    private int COUNT=(WIDTH+1)*(HEIGHT+1);
    private float[] verts=new float[COUNT*2];
    private float[] orig=new float[COUNT*2];
    private Bitmap bitmap;
    private float A;
    private float k=0;
    public FlagBitmapMeshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    private void initView(Context context){
//        setFocusable(true);
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.test);
        float bitmapWidth=bitmap.getWidth();
        float bitmapHeight=bitmap.getHeight();
        int index=0;
        for(int y=0;y<=HEIGHT;y++){
            float fy=bitmapHeight*y/HEIGHT;
            for(int x=0;x<=WIDTH;x++){
                float fx=bitmapWidth*x/WIDTH;
                orig[index*2+0]=verts[index*2+0]=fx;
                orig[index*2+1]=verts[index*2+1]=fy+100;
                index+=1;
            }
        }
        A=40;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        flagWave();
        k+=0.1f;
        canvas.drawBitmapMesh(bitmap,WIDTH,HEIGHT,verts,0,null,0,null);
        invalidate();
    }
    private void flagWave(){
        for(int j=0;j<=HEIGHT;j++){
            for(int i=0;i<=WIDTH;i++){
                int index=j*(WIDTH+1)+i;
                verts[2*index+0]+=0;
                float offsetY=(float)Math.sin((float)i/WIDTH*2*Math.PI+k*Math.PI);
                verts[2*index+1]=orig[2*index+1]+offsetY*A;
            }
        }
    }
}