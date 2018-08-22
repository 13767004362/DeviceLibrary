package com.xingen.devicelibrary.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Author by {xinGen}
 * Date on 2018/8/13 14:53
 *
 * 正则表达式 匹配类
 */
public class RegularUtils {
    /**
     * 是否匹配数字
     * @param content
     * @return
     */
    public static boolean isMatchesDigital(String content){
        if (TextUtils.isEmpty(content)){
            return false;
        }
       return Pattern.matches("^[0-9]*$",content);
    }
}
