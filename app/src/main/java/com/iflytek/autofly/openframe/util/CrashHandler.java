package com.iflytek.autofly.openframe.util;

import android.content.Context;

/**
 * @author wgsun
 * @descrbe 自定义错误日志系统
 * @since 2018/10/8
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = CrashHandler.class.getSimpleName();
    private static CrashHandler mInstance = null;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    public static CrashHandler getInstance() {
        if (null == mInstance) {
            synchronized (CrashHandler.class) {
                if (null == mInstance) {
                    mInstance = new CrashHandler();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        mContext = context;
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //自定义错误日志处理操作
        LogTools.d(TAG, "uncaughtException :" + e.getMessage());
        mUncaughtExceptionHandler.uncaughtException(t, e);
    }

}
