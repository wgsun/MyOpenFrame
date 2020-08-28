package com.iflytek.autofly.mvpframe.retrofit;

import com.iflytek.autofly.mvpframe.utils.AndroidUtils;
import com.iflytek.autofly.mvpframe.utils.Constants;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wgsun
 * @descrbe 公共请求参数封装
 * @since 2020/1/17 14:42
 */
public class CommonReqParamInterceptor implements Interceptor {

    public static final String TAG = CommonReqParamInterceptor.class.getSimpleName();

    /**
     * openId
     */
    public static final String PARAMS_OPENID = "openId";

    /**
     * A
     * 设备ID
     */
    public static final String PARAMS_DID = "dId";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        /**
         * 添加公共参数
         */
        HttpUrl.Builder authorizedUrlBuilder;
        authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter(PARAMS_OPENID, "5b319aaa")
                .addQueryParameter(PARAMS_DID, "24512563281433");
                //.addQueryParameter(PARAMS_OPENID, AndroidUtils.getStringProp(Constants.PROP_APPID))
                //.addQueryParameter(PARAMS_DID, AndroidUtils.getStringProp(Constants.PROP_DUID));
        /**
         * 建立新的请求
         */
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();
        if (newRequest != null && newRequest.url() != null) {
            LogHelper.d(TAG, "newRequest=" + newRequest.url().toString());
        }
        return chain.proceed(newRequest);
    }
}
