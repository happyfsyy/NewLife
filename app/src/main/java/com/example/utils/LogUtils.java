package com.example.utils;

import android.util.Log;

/**
 * 程序开发阶段，让level=Verbose，就可以让所有的Log显示出来。
 * 项目上线之后，让level=Nothing，就可以将所有的Log都屏蔽掉。
 */
public class LogUtils {
    public static final int VERBOSE=1;
    public static final int DEBUG=2;
    public static final int INFO=3;
    public static final int WRAN=4;
    public static final int ERROR=5;
    public static final int NOTHING=6;
    public static int level=VERBOSE;
    private static final String TAG = "LogUtils";

    public static void v(String tag,String msg){
        if(level<=VERBOSE){
            Log.v(tag,">>>>>>>>>"+msg);
        }
    }
    public static void d(String tag,String msg){
        if(level<=DEBUG){
            Log.d(tag,">>>>>>>>>"+msg);
        }
    }
    public static void i(String tag,String msg){
        if(level<=INFO){
            Log.i(tag,">>>>>>>>>"+msg);
        }
    }
    public static void w(String tag,String msg){
        if(level<=WRAN){
            Log.w(tag,">>>>>>>>>"+msg);
        }
    }
    public static void e(String tag,String msg){
        if(level<=ERROR){
            Log.e(tag,">>>>>>>>>"+msg);
        }
    }
    public static void e(String msg){
        if(level<=ERROR) {
            Log.e(TAG,msg);
        }
    }
}
