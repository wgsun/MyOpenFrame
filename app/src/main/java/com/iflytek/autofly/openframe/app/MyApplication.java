package com.iflytek.autofly.openframe.app;

import android.app.Application;

import com.iflytek.autofly.openframe.util.CrashHandler;
import com.iflytek.autofly.openframe.util.LogTools;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogTools.setDebug(true);
        LogTools.d(TAG, "onCreate");
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
