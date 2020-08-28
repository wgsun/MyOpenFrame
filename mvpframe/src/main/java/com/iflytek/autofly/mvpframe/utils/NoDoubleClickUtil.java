package com.iflytek.autofly.mvpframe.utils;

/**
 * @author wgsun
 * @descrbe 按键重复点击检测
 * @since 2020/5/29 14:39
 */
public class NoDoubleClickUtil {

    private static final int MIN_DELAY_TIME = 800;
    private static final int MIN_DELAY_LONG = 3000;
    private static long lastClickTime;
    private static long lastLongClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            lastClickTime = currentClickTime;
            flag = false;
        }
        return flag;
    }

    public static boolean isFastLongClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastLongClickTime) >= MIN_DELAY_LONG) {
            lastLongClickTime = currentClickTime;
            flag = false;
        }
        return flag;
    }

}
