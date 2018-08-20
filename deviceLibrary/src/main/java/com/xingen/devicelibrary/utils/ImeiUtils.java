package com.xingen.devicelibrary.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * Author by {xinGen}
 * Date on 2018/8/13 10:55
 */
public class ImeiUtils {
    /**
     * 获取默认的Imei
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        boolean result= RegularUtils.isMatchesDigital(imei);
        if (result) {
            return  imei;
        }else{
            return  adapterRom(context ,imei);
        }
    }

    private static String adapterRom(Context context,String meid) {
        String packageName=RomUtils.getLauncherPackageName(context.getApplicationContext());
        if (packageName.contains(RomUtils.ROM_HUAWEI)){
            if (SDKUtils.get_SDK_API()>=23){
                return meid;
            }else {
                String imei=adapterHuWei();
                return imei==null?meid:imei;
            }
        }else {
            return meid;
        }
    }
    private static String adapterHuWei(){
        try {
            Class<?> mSimTelephonyManagerClass = Class.forName("android.telephony.MSimTelephonyManager");
            Method getDefaultMethod = mSimTelephonyManagerClass.getMethod("getDefault");
            getDefaultMethod.setAccessible(true);
            Object MSimTelephonyManager = getDefaultMethod.invoke(mSimTelephonyManagerClass);
            Method getDeviceIdMethod = MSimTelephonyManager.getClass().getMethod("getDeviceId", int.class);
            getDeviceIdMethod.setAccessible(true);
            return  (String) getDeviceIdMethod.invoke(MSimTelephonyManager, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
