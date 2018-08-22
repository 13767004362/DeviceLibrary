package com.xingen.devicelibrary.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Author by {xinGen}
 * Date on 2018/8/13 10:55
 */
public class ImeiUtils {
    private static final String TAG = ImeiUtils.class.getSimpleName();

    /**
     * 获取默认的Imei
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        String imei = getNativeImei(context);
        boolean result = RegularUtils.isMatchesDigital(imei);
        if (result) {
            return imei;
        } else {
            return adapterRom(context, imei);
        }
    }

    private static String adapterRom(Context context, String meid) {
        String packageName = RomUtils.getLauncherPackageName(context.getApplicationContext());
        Log.i(TAG, " 系统launcher名字 " + packageName);
        if (packageName.contains(RomUtils.ROM_HUAWEI)) {
            if (SDKUtils.get_SDK_API() >= 23) {
                return meid;
            } else {
                String imei = adapterHuaWei();
                return imei == null ? meid : imei;
            }
        } else if (packageName.contains(RomUtils.ROM_LIAN_XIANG)) {
            String imei = null;
         //   imei = adapterK30E(context);

            Log.i(TAG," 设备机型 "+RomUtils.getModel());
         /*   if (RomUtils.getModel().equalsIgnoreCase("K30-E")) {

            }*/
            return imei == null ? meid : imei;
        } else {
            return meid;
        }
    }

    /**
     *  Android 原生方式获取 imei
     * @param context
     * @return
     */
    private static String getNativeImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = null;
        if (SDKUtils.get_SDK_API() >= 21) {
            try {
                Method getImeiMethod = telephonyManager.getClass().getDeclaredMethod("getImei");
                getImeiMethod.setAccessible(true);
                imei = (String) getImeiMethod.invoke(telephonyManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i(TAG, " getImei() " + imei +" "+RegularUtils.isMatchesDigital(imei));
        }
        if (!RegularUtils.isMatchesDigital(imei)) {
            imei = telephonyManager.getDeviceId();
            Log.i(TAG, " getDeviceId() " + imei);
        }
        return imei;
    }

    /**
     * 适配华为5.0部分机型
     *
     * @return
     */
    private static String adapterHuaWei() {
        try {
            Class<?> mSimTelephonyManagerClass = Class.forName("android.telephony.MSimTelephonyManager");
            Method getDefaultMethod = mSimTelephonyManagerClass.getMethod("getDefault");
            getDefaultMethod.setAccessible(true);
            Object MSimTelephonyManager = getDefaultMethod.invoke(mSimTelephonyManagerClass);
            Method getDeviceIdMethod = MSimTelephonyManager.getClass().getMethod("getDeviceId", int.class);
            getDeviceIdMethod.setAccessible(true);
            return (String) getDeviceIdMethod.invoke(MSimTelephonyManager, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String adapterK30E(Context context) {
        String imei = null;

        try {
            Class<?> PhoneFactoryClass = Class.forName("com.android.internal.telephony.PhoneFactory");
            Method makeDefaultPhonesMethod = PhoneFactoryClass.getDeclaredMethod("makeDefaultPhones", Context.class);
            makeDefaultPhonesMethod.setAccessible(true);
            makeDefaultPhonesMethod.invoke(null, context);
            Method getDefaultPhoneMethod = PhoneFactoryClass.getDeclaredMethod("getDefaultPhone");
            getDefaultPhoneMethod.setAccessible(true);
            Object phone = getDefaultPhoneMethod.invoke(PhoneFactoryClass);
            Class<?> PhoneClass = phone.getClass();
            Method getImeiMethod = PhoneClass.getMethod("getImei");
            getImeiMethod.setAccessible(true);
            imei = (String) getImeiMethod.invoke(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, " K30E " + imei);
        return imei;
    }
}
