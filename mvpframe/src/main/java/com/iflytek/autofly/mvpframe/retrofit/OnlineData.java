package com.iflytek.autofly.mvpframe.retrofit;

import android.text.TextUtils;

import com.iflytek.autofly.mvpframe.utils.AndroidUtils;
import com.iflytek.autofly.mvpframe.utils.Constants;

public class OnlineData {

    private static final String DEFAULT_URL_HEAD = "https://";
    private static final String DEFAULT_URL = "autodev.openspeech.cn";
    private static final String DEFAULT_URL_TAIL = "/api-gw/api/v1.0/video/iqiyi/";
    private static final String DEFAULT_URL_POLICY = "/autoh5static/resource/car/t1d/v1/staticAssets/iQIYI/index.html";

    public static String getBaseUrl() {
        String url;
        String str = AndroidUtils.getStringProp(Constants.URL_KEY);
        if (TextUtils.isEmpty(str)) {
            url = DEFAULT_URL_HEAD + DEFAULT_URL + DEFAULT_URL_TAIL;
        } else {
            url = DEFAULT_URL_HEAD + str + DEFAULT_URL_TAIL;
        }
        return url;
    }

    public static String getPrivacyPolicyUrl() {
        String url;
        String str = AndroidUtils.getStringProp(Constants.URL_KEY);
        if (TextUtils.isEmpty(str)) {
            url = DEFAULT_URL_HEAD + DEFAULT_URL + DEFAULT_URL_POLICY;
        } else {
            url = DEFAULT_URL_HEAD + str + DEFAULT_URL_POLICY;
        }
        return url;
    }

    public static final int PAGE_NUM = 20;
    public static final int PAGE_NUM_DRAMA = 50;
    public static final int PAGE_NUM_RANK = 3;

    /**
     * 爱奇艺分配的硬件型号UUID
     */
    public static final String DEVICE_UUID = "20191210152723452DVqdzifTL101484";
    public static final String SN = "1615300e82964d6c";


}
