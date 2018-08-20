package com.xingen.devicelibrary;

import android.content.Context;

import com.xingen.devicelibrary.utils.ImeiUtils;

/**
 * Author by {xinGen}
 * Date on 2018/8/13 10:43
 */
public class DeviceHelper {
    private static Context appContext;
    public static void init(Context context){
        appContext=context.getApplicationContext();
    }
    public static String getImei(){
        return ImeiUtils.getImei(appContext);
    }

}
