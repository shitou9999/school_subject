package com.example.administrator.mytable.School;

import android.content.Context;

/**
 * Created by Administrator on 2015/12/4.
 */
public class DisplayUtil {


    /**
     * 获得屏幕的宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕的高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
