package com.example.newlife;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.utils.LogUtils;

import androidx.annotation.Nullable;

public class InterceptLayout extends LinearLayout {
    private static final String TAG = "InterceptLayout";
    public InterceptLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtils.e(TAG,"InterceptLayout: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e(TAG,"InterceptLayout: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e(TAG,"InterceptLayout: ACTION_UP");
                break;
            default:
                break;
        }
        return false;
    }


}
