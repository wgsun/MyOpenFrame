package com.iflytek.autofly.mvpframe.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.lang.reflect.Method;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/3/6 14:46
 */
public class AndroidUtils {

    public static final String TAG = AndroidUtils.class.getSimpleName();
    public static final String SYSTEM_PROPERTIES = "android.os.SystemProperties";
    public static final String METHOD_GET = "get";

    /**
     * 获取版本号名称
     *
     * @return 当前应用的版本号
     */
    public static String getVersionName(Context context) {
        return getVersionName(context, context.getPackageName());
    }

    /**
     * 获取版本号名称
     *
     * @param context
     * @param packageName
     * @return 应用的版本号
     */
    public static String getVersionName(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(packageName, 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        return getVersionCode(context, context.getPackageName());
    }

    /**
     * 获取版本号
     *
     * @param context
     * @param packageName
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(packageName, 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取Unix时间戳
     */
    public static long getUnixTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getStringProp(String key) {
        if (TextUtils.isEmpty(key)) {
            return "";
        }
        String value = "";
        try {
            Class<?> c = Class.forName(SYSTEM_PROPERTIES);
            Method getMethod = c.getMethod(METHOD_GET, String.class,
                    String.class);
            value = (String) (getMethod.invoke(c, key, ""));
        } catch (Exception e) {
            LogHelper.e(TAG, "get property error, " + e.getMessage());
            return value;
        }
        return value;
    }
}
