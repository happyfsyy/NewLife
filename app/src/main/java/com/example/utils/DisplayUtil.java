package com.example.utils;

import android.content.Context;

/**
 * dp、sp与px的相互转化工具
 *
 */
public class DisplayUtil {
    private DisplayUtil(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 将px值转化为dp值
     *
     */
    public static int px2dp(Context context,float pxValue){
        float density=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/density+0.5f);
    }

    /**
     * 将dp值转化为px值
     */
    public static int dp2px(Context context,float dpValue){
        float density=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*density+0.5f);
    }

    /**
     * 将px值转化为sp值
     *
     */
    public static int px2sp(Context context,float pxValue){
        float scaledDensity=context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue/scaledDensity+0.5f);
    }

    /**
     * sp值转化为px值
     *
     */
    public static int sp2px(Context context,float spValue){
        float scaledDensity=context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue*scaledDensity+0.5f);
    }
}
