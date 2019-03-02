package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CanvasClipView extends View {
    private Paint mPaint;
    public CanvasClipView(Context context) {
        super(context);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //这里是对画布进行裁剪的，要在画图之前对canvas进行裁剪。如果画图之后再对canvas进行clip不会影响到已经画好的图形。
//        canvas.drawCircle(100,100,100,mPaint);
//        canvas.clipRect(100,100,300,600);
//        canvas.drawColor(Color.RED);

        canvas.clipRect(100,100,300,600);
        canvas.drawColor(Color.RED);
        canvas.drawCircle(100,100,100,mPaint);

        //默认的相交，两次clip之后，取得只是相交的那一块画布，还有其中几种Op
        //例如Difference，Union，Xor，Replace
//        canvas.save();
//        canvas.clipRect(100,100,300,300);
//        canvas.clipRect(250,250,450,450);
//        canvas.drawColor(Color.CYAN);
//        canvas.restore();

        //画布clip之前save之后restore，就会恢复成原来的画布了，不然一直都是在clip的画布上进行绘画，那么超过clip范围的都看不到了
//        canvas.save();
//        canvas.clipRect(100,100,300,300);
//        canvas.drawColor(Color.MAGENTA);
//        canvas.restore();
//        canvas.save();
//        canvas.clipRect(250,250,450,450);
//        canvas.drawColor(Color.CYAN);
//        canvas.restore();
    }
}
