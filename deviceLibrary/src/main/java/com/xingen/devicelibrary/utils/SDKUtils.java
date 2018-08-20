package com.xingen.devicelibrary.utils;

import android.os.Build;

/**
 * Author by {xinGen}
 * Date on 2018/8/13 10:48
 */
public class SDKUtils {
    /**
     * 获取当前sdk 的api
     * @return
     */
    public static  int  get_SDK_API(){
        return Build.VERSION.SDK_INT;
    }

}
