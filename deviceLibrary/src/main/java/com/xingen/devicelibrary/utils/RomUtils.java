package com.xingen.devicelibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

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
     * 华为
     */
    public static final String ROM_HUAWEI="huawei";
}
