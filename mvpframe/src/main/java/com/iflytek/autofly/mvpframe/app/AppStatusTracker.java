package com.iflytek.autofly.mvpframe.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.iflytek.autofly.mvpframe.utils.Constants;
import com.iflytek.autofly.mvpframe.utils.LogHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lbzhang3 on 2018/1/30.
 */

public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    private static String TAG = "AppStatusTracker";

    private static Activity mCurrentAty;

    private static ArrayList<WeakReference<Activity>> mArrayList = new ArrayList<WeakReference<Activity>>();

    public static Activity getCurrentAty() {
        return mCurrentAty;
    }

    public static List<WeakReference<Activity>> getCurrentActivityList() {
        return mArrayList;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogHelper.e(TAG, "onActivityCreated");
        WeakReference<Activity> ref = new WeakReference<Activity>(activity);
        mCurrentAty = activity;
        mArrayList.add(ref);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogHelper.e(TAG, "onActivityStarted");
        LogHelper.e(TAG, activity.getClass().getName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogHelper.e(TAG, "onActivityResumed");
        Constants.APP_IS_BACKGROUND = false;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogHelper.e(TAG, "onActivityPaused");
        Constants.APP_IS_BACKGROUND = true;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogHelper.e(TAG, "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogHelper.e(TAG, "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogHelper.e(TAG, "onActivityDestroyed");
        Iterator<WeakReference<Activity>> itr = mArrayList.iterator();
        while (itr.hasNext()) {
            WeakReference<Activity> actRef = itr.next();
            if (actRef != null && actRef.get() != null && actRef.get().equals(activity)) {
                itr.remove();
                break;
            }
        }
    }
}
