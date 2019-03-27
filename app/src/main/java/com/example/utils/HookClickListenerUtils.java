package com.example.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;

public class HookClickListenerUtils {
    private static HookClickListenerUtils mHookClickListenerUtils;
    private HookClickListenerUtils(){
    }
    public static HookClickListenerUtils getInstance(){
        if(mHookClickListenerUtils==null){
            synchronized (HookClickListenerUtils.class){
                if(mHookClickListenerUtils==null){
                    mHookClickListenerUtils=new HookClickListenerUtils();
                }
            }
        }
        return mHookClickListenerUtils;
    }
    public void hookDecorViewClick(ViewGroup decorView){
        int count=decorView.getChildCount();
        for(int i=0;i<count;i++){
            View childView=decorView.getChildAt(i);
            if(childView instanceof  ViewGroup){
                hookDecorViewClick((ViewGroup)childView);
            }else{
                hookViewClick(childView);
            }
        }
    }
    public void hookViewClick(View view){
        try{
            Class<?> viewClass=Class.forName("android.view.View");
            Method getListenerInfoMethod=viewClass.getDeclaredMethod("getListenerInfo");
            if(!getListenerInfoMethod.isAccessible()){
                getListenerInfoMethod.setAccessible(true);
            }
            //反射view中的getListenerInfo方法
            Object listenerInfoObject=getListenerInfoMethod.invoke(view);

            Class<?> mListenerInfoClass=Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListenerField=mListenerInfoClass.getDeclaredField("mOnClickListener");
            mOnClickListenerField.setAccessible(true);
            View.OnClickListener onClickListener=(View.OnClickListener)mOnClickListenerField.get(listenerInfoObject);
            mOnClickListenerField.set(listenerInfoObject,new HookClickListener(onClickListener));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static class HookClickListener implements View.OnClickListener{
        private View.OnClickListener onClickListener;
        private int MIN_CLICK_DELAY_TIME=2000;
        private long lastClickTime=0;
        public HookClickListener(View.OnClickListener onClickListener){
            this.onClickListener=onClickListener;
        }
        @Override
        public void onClick(View v) {
            long currentTime= Calendar.getInstance().getTimeInMillis();
            if(currentTime-lastClickTime>MIN_CLICK_DELAY_TIME){
                lastClickTime=currentTime;
                if(onClickListener!=null){
                    onClickListener.onClick(v);
                }
            }else{
                LogUtils.e("又点击了一次，不让你点击了");
            }
        }
    }
}
