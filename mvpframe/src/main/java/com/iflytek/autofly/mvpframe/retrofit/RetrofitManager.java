package com.iflytek.autofly.mvpframe.retrofit;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wgsun
 * @descrbe 接口请求管理类
 * @since 2019/12/16 15:30
 */
public class RetrofitManager {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private RetrofitApiService retrofitApiService;
    private ArrayList<String> oneNetList;
    private static final String HTTP_CACHE_PATH = "/IqiyiCache/okhttp_cache";

    public static RetrofitManager getInstance() {
        return RetrofitHolder.mInstance;
    }

    private static class RetrofitHolder{
        public static final RetrofitManager mInstance = new RetrofitManager();
    }

    public RetrofitManager() {
        oneNetList = new ArrayList<>();
        initOkHttpClient();
        initRetrofit();
    }

    public ArrayList<String> getOneNetList() {
        return oneNetList;
    }

    public RetrofitApiService getRetrofitApiService() {
        return retrofitApiService;
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(OnlineData.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        retrofitApiService = retrofit.create(RetrofitApiService.class);
    }

    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient.Builder()
                //设置缓存文件路径，和文件大小
                .cache(new Cache(new File(Environment.getExternalStorageDirectory() + HTTP_CACHE_PATH), 50 * 1024 * 1024))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new CommonReqParamInterceptor())
                .addInterceptor(new HttpLogInterceptor())
                // 设置在线和离线缓存
                .addInterceptor(OfflineCacheInterceptor.getInstance())
                //.addNetworkInterceptor(NetCacheInterceptor.getInstance())
                .build();
    }

}
