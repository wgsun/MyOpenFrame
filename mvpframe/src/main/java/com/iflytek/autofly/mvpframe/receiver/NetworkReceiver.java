package com.iflytek.autofly.mvpframe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.iflytek.autofly.mvpframe.app.MyApplication;
import com.iflytek.autofly.mvpframe.manager.NetWorkManager;
import com.iflytek.autofly.mvpframe.mvp.model.listener.INetworkListener;
import com.iflytek.autofly.mvpframe.utils.LogHelper;

public class NetworkReceiver {

    private static final String TAG = NetworkReceiver.class.getSimpleName();

    private INetworkListener listener = null;

    private NetworkReceiver() {
    }

    private static NetworkReceiver instance = null;

    public static NetworkReceiver getInstance() {
        return ReceiverHolder.mInstance;
    }

    private static class ReceiverHolder{
        public static final NetworkReceiver mInstance = new NetworkReceiver();
    }

    public void setListener(INetworkListener listener) {
        this.listener = listener;
    }

    public void registerBroadCastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        MyApplication.getContext().registerReceiver(receiver, intentFilter);
        LogHelper.d(TAG, "registerBroadCastReceiver network change receiver");
    }

    public void unregisterBroadCastReceiver() {
        MyApplication.getContext().unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogHelper.d(TAG, "network change onReceive action " + action);
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (listener != null) {
                    listener.onNetworkChanged(NetWorkManager.getInstance().isNetValid());
                }
            }
        }
    };

}
