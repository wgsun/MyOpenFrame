package com.iflytek.autofly.mvpframe.retrofit;
import com.iflytek.autofly.mvpframe.mvp.model.response.NaviResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author wgsun
 * @descrbe 接口请求配置
 * @since 2019/12/16 19:02
 */
public interface RetrofitApiService {

    /**
     * 导航接口
     */
    @GET("navlist")
    Observable<NaviResponse> naviRequest();

}
