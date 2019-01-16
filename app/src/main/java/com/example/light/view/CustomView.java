package com.example.light.view;

import android.content.Context;
import android.graphics.Canvas;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * 测试view滑动的几种方式
 */
public class CustomView extends View {
    private static final String TAG = "CustomView";
    private float lastX;
    private float lastY;
    private float newX;
    private float newY;
    private Scroller mScroller;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure()");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,"onLayout()");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=event.getX();
                lastY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                newX=event.getX();
                newY=event.getY();
                int offsetX=(int)(newX-lastX);
                int offsetY=(int)(newY-lastY);
                //layout的方式使得view滑动
//                layout(getLeft()+offsetX,getTop()+offsetY,
//                        getRight()+offsetX,getBottom()+offsetY);
                //offset()方法使得view滑动
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
                //使用LayoutParams的方式来使得view滑动
//                ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams)getLayoutParams();
//                params.leftMargin=getLeft()+offsetX;
//                params.topMargin=getTop()+offsetY;
//                setLayoutParams(params);
                //使用scrollBy
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG,"onDraw()");
        super.onDraw(canvas);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
        int scrollX=getScrollX();
        int delta=destX-scrollX;
        mScroller.startScroll(scrollX,0,delta,destY,2000);
        invalidate();
    }
}
