package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class ScrollerTestView extends View {
    private static final String TAG = "ScrollerTestView";
    private float tempX;
    private float tempY;
    private float downX;
    private float downY;
    private int offsetX;
    private int offsetY;
    private Scroller mScroller;
    private float lastRawX;
    private float newRawX;
    public ScrollerTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,"onLayout()");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tempX=event.getX();
        tempY=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=tempX;
                downY=tempY;
                lastRawX=event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX=(int)(tempX-downX);
                offsetY=(int)(tempY-downY);
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
//                layout(getLeft()+offsetX,getTop()+offsetY,
//                        getRight()+offsetX,getBottom()+offsetY);
                break;
            case MotionEvent.ACTION_UP:
                newRawX=event.getRawX();
                Log.e(TAG,"offsetX: "+(int)(newRawX-lastRawX));
                View viewGroup=((View)getParent());
                Log.e(TAG,"ScrollX: "+viewGroup.getScrollX()+"\tScrollY: "+viewGroup.getScrollY());
                mScroller.startScroll(viewGroup.getScrollX(),viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),-viewGroup.getScrollY());
                invalidate();
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            Log.e(TAG,"getCurrX(): "+mScroller.getCurrX()+
                    "\tgetCurrY(): "+mScroller.getCurrY());
            ((View)(getParent())).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
