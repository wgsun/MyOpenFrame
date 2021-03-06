package com.iflytek.autofly.openframe.util;

import android.util.Log;

/**
 * @author wgsun
 * @descrbe 日志管理类
 * @since 2018/10/8
 */
public class LogTools {

    private static final int V = 5;
    private static final int D = 4;
    private static final int I = 3;
    private static final int W = 2;
    private static final int E = 1;
    private static final int N = 6;
    //更改level
    private static int level = 7;

    /**
     * @param debug true 开发 false 发布
     */
    public static void setDebug(boolean debug) {
        if (debug) {
            level = N;
        } else {
            level = 0;
        }
    }

    public static void v(String tag, String msg) {
        if (level >= V) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tw) {
        if (level >= V) {
            Log.v(tag, msg, tw);
        }
    }

    public static void d(String tag, String msg) {
        if (level >= D) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tw) {
        if (level >= D) {
            Log.d(tag, msg, tw);
        }
    }

    public static void i(String tag, String msg) {
        if (level >= I) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tw) {
        if (level >= I) {
            Log.i(tag, msg, tw);
        }
    }

    public static void w(String tag, String msg) {
        if (level >= W) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tw) {
        if (level >= W) {
            Log.w(tag, msg, tw);
        }
    }

    public static void e(String tag, String msg) {
        if (level >= E) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, Throwable t) {
        if (level >= E) {
            Log.e(tag, "", t);
        }
    }

    public static void e(String tag, String msg, Throwable tw) {
        if (level >= E) {
            Log.e(tag, msg, tw);
        }
    }
}
