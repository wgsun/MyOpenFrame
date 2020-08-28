package com.iflytek.autofly.mvpframe.mvp.presenter;

import com.google.gson.Gson;
import com.iflytek.autofly.mvpframe.app.MyApplication;
import com.iflytek.autofly.mvpframe.base.BasePresenter;
import com.iflytek.autofly.mvpframe.mvp.model.iview.IMainAtyView;
import com.iflytek.autofly.mvpframe.mvp.model.response.NaviResponse;
import com.iflytek.autofly.mvpframe.utils.Constants;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import com.iflytek.autofly.mvpframe.utils.ObjectUtils;
import java.util.concurrent.TimeoutException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/8/27 14:21
 */
public class MainPresenter extends BasePresenter<IMainAtyView> {

    public static final String TAG = MainPresenter.class.getSimpleName();

    public void reqNaviInfo() {
        if (!isViewAttached()) {
            LogHelper.d(TAG, "isViewAttached false");
            return;
        }
        addOneNet("navi");
        setOfflineCacheTime(Constants.OFFLINE_TIME_OUT_DAY);
        observe(apiService().naviRequest())
                .subscribe(new Observer<NaviResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogHelper.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(NaviResponse naviResponse) {
                        LogHelper.d(TAG, "onNext");
                        if (naviResponse != null && naviResponse.isSuccess() && naviResponse.getData() != null) {
                            getView().initHomeTop(naviResponse.getData());
                        } else {
                            showLoadNetErroView(false);
                        }
                        removeTag("navi");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogHelper.d(TAG, "onError");
                        if (throwable != null) {
                            LogHelper.d(TAG, "reqNaviInfo fail : " + throwable.toString());
                        }
                        if (!ObjectUtils.isNull(throwable) && throwable instanceof TimeoutException) {
                            showLoadNetErroView(true);
                        } else {
                            showLoadNetErroView(false);
                        }
                        removeTag("navi");
                    }

                    @Override
                    public void onComplete() {
                        LogHelper.d(TAG, "onComplete");
                    }
                });
    }

}
