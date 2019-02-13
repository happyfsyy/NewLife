package com.example.newlife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LayerTestAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new LayerView(this));
    }
    class LayerView extends View{
        private Paint mPaint;
        public LayerView(Context context) {
            super(context);
            mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            mPaint.setColor(Color.BLACK);
            canvas.drawCircle(100,100,100,mPaint);

//            canvas.saveLayerAlpha(0,0,400,400,0);
//            canvas.saveLayerAlpha(0,0,400,400,127);
//            canvas.saveLayerAlpha(0,0,400,400,255);
            //可以看出，保存的范围内的layer透明度是可以被设置的，不在范围内的则是透明度为0
            canvas.saveLayerAlpha(0,0,200,250,127);
            mPaint.setColor(Color.RED);
            canvas.drawCircle(200,200,100,mPaint);
            canvas.restore();
        }
    }
}
