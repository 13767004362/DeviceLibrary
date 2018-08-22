package com.xingen.devicelibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;

/**
 * Author by {xinGen}
 * Date on 2018/8/13 10:46
 */
public class RomUtils {
    /**
     * 获取到Launcher的包名，从而判断是哪个手机厂商。
     * @param context
     * @return
     */
    public static String getLauncherPackageName(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo res = context.getPackageManager().resolveActivity(intent, 0);
        if (res == null || res.activityInfo == null) {
            return "";
        }
        if ("android".equals(res.activityInfo.packageName)) {
            return "";
        } else {
            return res.activityInfo.packageName;
        }
    }
    /**
     * 获取机型
     */
    public static String getModel() {
        try {
            String s= Build.MODEL;
            return s;
        } catch (Throwable throwable) {
            if (throwable != null) {
                throwable.printStackTrace();
            }
        }
        return "";
    }
    /**
     * 华为
     */
    public static final String ROM_HUAWEI="huawei";
    /**
     * 金立
     */
    public static final String ROM_JING_LI="gionee";
    /**
     * 魅族
     */
    public static final  String ROM_MEIZU="flyme";
    /**
     * 联想
     */
    public  static final  String ROM_LIAN_XIANG="lenovo";
    /**
     *  锤子
     */
    public static final  String ROM_CUI_ZI="smartisanos";
    /**
     * 小辣椒
     */
    public  static final String ROM_XIAO_LA_JIAO="ila";
    /**
     * oppo
     */
    public static final  String ROM_OPPO="oppo";
    /**
     *  vivo
     */
    public static final  String ROM_VIVO="bbk";
}
