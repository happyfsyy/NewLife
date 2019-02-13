package com.example.newlife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ColorMatrixAct extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap bitmap;
    private GridLayout gridLayout;
    private int mEtWidth;
    private int mEtHeight;
    private EditText[] mEts=new EditText[20];
    private float[] mColorMatrix=new float[20];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.test1);
        imageView=findViewById(R.id.imageview);
        imageView.setImageBitmap(bitmap);
        gridLayout=findViewById(R.id.gridlayout);
        initViews();
    }
    private void initViews(){
        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth=gridLayout.getWidth()/5;
                mEtHeight=gridLayout.getHeight()/4;
                addEts();
                initMatrix();
            }
        });
    }

    private void addEts(){
        for(int i=0;i<20;i++){
            EditText editText=new EditText(this);
            editText.setInputType(EditorInfo.TYPE_NUMBER_FLAG_SIGNED);
            mEts[i]=editText;
            gridLayout.addView(mEts[i],mEtWidth,mEtHeight);
        }
    }
    private void initMatrix(){
        for(int i=0;i<20;i++){
            if(i%6==0){
                mEts[i].setText("1");
            }else{
                mEts[i].setText("0");
            }
        }
    }
    public void btnChange(View view){
        getMatrix();
        setImageMatrix();
    }
    public void btnReset(View view){
        initMatrix();
        getMatrix();
        setImageMatrix();
    }
    private void getMatrix(){
        for(int i=0;i<20;i++){
            mColorMatrix[i]=Float.valueOf(mEts[i].getText().toString());
        }
    }
    private void setImageMatrix(){
        Bitmap des=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(des);
        Paint paint=new Paint();
        ColorMatrix colorMatrix=new ColorMatrix();
        colorMatrix.set(mColorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        imageView.setImageBitmap(des);
    }
}
