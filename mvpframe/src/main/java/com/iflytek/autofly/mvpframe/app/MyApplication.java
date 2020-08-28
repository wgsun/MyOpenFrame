package com.iflytek.autofly.mvpframe.app;

import android.app.Application;
import android.content.Context;
import com.iflytek.autofly.mvpframe.greendao.DaoManager;
import com.iflytek.autofly.mvpframe.receiver.NetworkReceiver;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * @author wgsun
 * @descrbe 应用入口
 * @since 2019/12/16 20:06
 */
public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    private static Context mContext = null;

    private AppStatusTracker tracker = new AppStatusTracker();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LogHelper.setDebug(true);
        registerActivityLifecycleCallbacks(tracker);
        initGreenDao();
        NetworkReceiver.getInstance().registerBroadCastReceiver();
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable != null) {
                    LogHelper.d(TAG, "catch rxjava exception :" + throwable.toString());
                }
            }
        });
    }

    private void initGreenDao() {
        DaoManager daoManager = DaoManager.getInstance();
        daoManager.init(this);
    }

    public static Context getContext() {
        return mContext;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        LogHelper.d(TAG, "onTerminate");
        NetworkReceiver.getInstance().unregisterBroadCastReceiver();
    }
}
