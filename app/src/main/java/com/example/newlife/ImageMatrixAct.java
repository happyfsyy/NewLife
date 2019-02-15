package com.example.newlife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.view.ImageMatrixView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImageMatrixAct extends AppCompatActivity {
    private ImageMatrixView imageMatrixView;
    private GridLayout gridLayout;
    private EditText[] mEts=new EditText[9];
    private float[] mImageMatrix=new float[9];
    private int mWidth;
    private int mHeight;
    private Bitmap mBitmap;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_matrix);

        imageMatrixView=findViewById(R.id.image_matrix_view);
        gridLayout=findViewById(R.id.image_matrix_grid);
        mBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.test3);
        imageView=findViewById(R.id.image_matrix_imgview);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        Matrix matrix=new Matrix();
        matrix.setScale(2,2);
        imageView.setImageMatrix(matrix);

        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                mWidth=gridLayout.getWidth()/3;
                mHeight=gridLayout.getHeight()/3;
                addEts();
                initMatrix();
            }
        });
    }
    private void addEts(){
        for(int i=0;i<9;i++){
            EditText editText=new EditText(this);
            editText.setGravity(Gravity.CENTER);
            mEts[i]=editText;
            gridLayout.addView(editText,mWidth,mHeight);
        }
    }
    private void initMatrix(){
        for(int i=0;i<9;i++){
            if(i%4==0){
                mEts[i].setText("1");
            }else{
                mEts[i].setText("0");
            }
        }
    }
    private void getMatrix(){
        for(int i=0;i<9;i++){
            mImageMatrix[i]=Float.valueOf(mEts[i].getText().toString());
        }
    }
    public void change(View view){
        getMatrix();
        Matrix matrix=new Matrix();
//        matrix.setValues(mImageMatrix);
        matrix.setRotate(45);
        matrix.postTranslate(200,200);

        matrix.setTranslate(200,200);
        matrix.postRotate(45);
        matrix.setScale(1,-1);
        matrix.postRotate(45);
        matrix.postTranslate(0,200);

        imageMatrixView.setBitmapAndMatrix(mBitmap,matrix);
        imageMatrixView.postInvalidate();
    }
    public void reset(View view){
        initMatrix();
        getMatrix();
        Matrix matrix=new Matrix();
        matrix.setValues(mImageMatrix);
        imageMatrixView.setBitmapAndMatrix(mBitmap,matrix);
        imageMatrixView.postInvalidate();
    }

}
