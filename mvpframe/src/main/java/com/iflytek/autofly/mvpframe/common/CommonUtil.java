package com.iflytek.autofly.mvpframe.common;

import android.content.Context;
import android.content.Intent;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/8/27 10:04
 */
public class CommonUtil {
    public static final String NET_SETTING_ACTION = "com.android.setting.ACTION_WIFI_SETTING";

    public static void jumptoNetSettingAty(Context context) {
        Intent intent = new Intent();
        intent.setAction(NET_SETTING_ACTION);
        context.startActivity(intent);
    }
}
