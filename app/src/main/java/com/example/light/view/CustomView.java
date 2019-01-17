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

    private float lastRawX;
    private float lastRawY;
    private float newRawX;
    private float newRawY;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.e(TAG,"onMeasure()");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        Log.e(TAG,"onLayout()");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=event.getX();
                lastY=event.getY();

                lastRawX=event.getRawX();
                lastRawY=event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                newX=event.getX();
                newY=event.getY();
                newRawX=event.getRawX();
                newRawY=event.getRawY();
                int offsetRawX=(int)(newRawX-lastRawX);
                int offsetX=(int)(newX-lastX);
                int offsetY=(int)(newY-lastY);
                //layout的方式使得view滑动
                Log.e(TAG,"getLeft(): "+getLeft()+"\tlastX: "+lastX+"\tnewX: "+newX+"\toffsetX: "+offsetX+
                        "\tlastRawX: "+lastRawX+"\tnewRawX: "+newRawX+"\toffsetRawX: "+offsetRawX);
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
//                scrollBy(offsetX,offsetY);
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"ACTION_UP");
                View viewGroup=((View)getParent());
                mScroller.startScroll(viewGroup.getScrollX(),viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),-viewGroup.getScrollY());
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        Log.e(TAG,"onDraw()");
        super.onDraw(canvas);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        Log.e(TAG,"computeScroll()");
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
