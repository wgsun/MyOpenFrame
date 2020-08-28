package com.iflytek.autofly.mvpframe.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import com.iflytek.autofly.mvpframe.app.MyApplication;
import com.iflytek.autofly.mvpframe.mvp.model.listener.INetworkListener;
import com.iflytek.autofly.mvpframe.receiver.NetworkReceiver;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.RequiresPermission;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

/**
 * @author wgsun
 * @descrbe 网络状态管理类
 * @since 2020/1/2 19:14
 */
public class NetWorkManager {

    private static final String TAG = "NetWorkManager";

    private ConnectivityManager mConnectManager = null;
    private List<INetworkListener> mNetworkListeners = new ArrayList<>();

    private NetWorkManager() {
        mConnectManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkReceiver.getInstance().setListener(listener);
    }

    public static NetWorkManager getInstance() {
        return NetWrokHolder.mInstence;
    }

    private static class NetWrokHolder {
        public static final NetWorkManager mInstence = new NetWorkManager();
    }

    private INetworkListener listener = new INetworkListener() {

        @Override
        public void onNetworkChanged(boolean isConnected) {
            LogHelper.d(TAG, "onNetworkChange isConnected : " + isConnected);
            for (INetworkListener listener : mNetworkListeners) {
                listener.onNetworkChanged(isConnected);
            }
        }
    };

    public void addListener(INetworkListener listener) {
        if (mNetworkListeners != null && !mNetworkListeners.contains(listener)) {
            mNetworkListeners.add(listener);
        }
    }

    public void removeListener(INetworkListener listener) {
        if (listener != null && mNetworkListeners != null && mNetworkListeners.contains(listener)) {
            mNetworkListeners.remove(listener);
        }
    }

    /**
     * 网络是否已连接
     *
     * @return true:已连接 false:未连接
     */
    @SuppressWarnings("deprecation")
    @RequiresPermission(ACCESS_NETWORK_STATE)
    public boolean isNetworkConnected() {
        if (mConnectManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities = mConnectManager.getNetworkCapabilities(mConnectManager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                }
            } else {
                NetworkInfo networkInfo = mConnectManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * 网络是否可用
     * @return
     */
    public boolean isNetValid() {
        boolean valid = false;
        try{
            valid = mConnectManager.getActiveNetworkInfo().isAvailable();
        }catch (Exception e){
            e.printStackTrace();
        }
        return valid;
    }

    /**
     * Wifi是否已连接
     *
     * @return true:已连接 false:未连接
     */
    @SuppressWarnings("deprecation")
    public boolean isWifiConnected() {
        if (mConnectManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities = mConnectManager.getNetworkCapabilities(mConnectManager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
                }
            } else {
                NetworkInfo networkInfo = mConnectManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            }
        }
        return false;
    }

    /**
     * 是否为流量
     */
    @SuppressWarnings("deprecation")
    public boolean isMobileData() {
        if (mConnectManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities = mConnectManager.getNetworkCapabilities(mConnectManager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
                }
            } else {
                NetworkInfo networkInfo = mConnectManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }

    /**
     * 获取连接的网络类型
     * @param context
     * @return
     */
    public Integer getNetType(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    return activeNetworkInfo.getType();
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
        return -1;
    }

}
