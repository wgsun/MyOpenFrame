package com.iflytek.autofly.mvpframe.base;

import com.iflytek.autofly.mvpframe.retrofit.NetCacheInterceptor;
import com.iflytek.autofly.mvpframe.retrofit.OfflineCacheInterceptor;
import com.iflytek.autofly.mvpframe.retrofit.RetrofitApiService;
import com.iflytek.autofly.mvpframe.retrofit.RetrofitManager;
import com.iflytek.autofly.mvpframe.utils.Constants;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import com.iflytek.autofly.mvpframe.utils.ObjectUtils;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V extends IBaseView> {

    public static final String TAG = BasePresenter.class.getSimpleName();

    private V mView;

    private CompositeDisposable compositeDisposable;

    /**
     * 注入View，使之能够与View相互响应
     */
    public void attachView(V view) {
        mView = view;
    }

    /**
     * 释放资源
     */
    public void detachView() {
        mView = null;
        if (!ObjectUtils.isNull(compositeDisposable)) {
            LogHelper.d(TAG, "detachView and clear dispose");
            compositeDisposable.clear();
        }
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return mView;
    }

    //在网络还在请求时，多次点击只请求一次
    public void addOneNet(String tag) {
        if (RetrofitManager.getInstance().getOneNetList().contains(tag)) {
            return;
        } else {
            RetrofitManager.getInstance().getOneNetList().add(tag);
        }
    }

    public void removeTag(String tag) {
        RetrofitManager.getInstance().getOneNetList().remove(tag);
    }

    //设置在线网络缓存
    public void setOnlineCacheTime() {
        NetCacheInterceptor.getInstance().setOnlineTime(Constants.ONLINE_TIME_OUT);
    }

    //设置离线网络缓存
    public void setOfflineCacheTime(int time) {
        OfflineCacheInterceptor.getInstance().setOfflineCacheTime(time);
    }

    public CompositeDisposable getCompositeDisposable() {
        if (ObjectUtils.isNull(compositeDisposable)) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    public RetrofitApiService apiService() {
        return RetrofitManager.getInstance().getRetrofitApiService();
    }

    /**
     * 下面都是为了偷懒，方便封装的。，，
     */

    public <T> Observable<T> observe(Observable<T> observable) {
        return observe(observable, true);
    }

    public <T> Observable<T> observe(Observable<T> observable, boolean showLoading) {
        return observe(observable, showLoading, true);
    }

    public <T> Observable<T> observe(Observable<T> observable, final boolean showLoading, final boolean hideLoadingAuto) {

        return observable.subscribeOn(Schedulers.io())
                .timeout(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (showLoading) {
                            showLoadingView();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (showLoading && hideLoadingAuto) {
                            hideLoadingView();
                        }
                    }
                });
                //防止RxJava内存泄漏
                //.compose(mView.bindLifecycle());
    }

    public void showLoadingView() {
        if (isViewAttached()) {
            getView().showLoadingView();
        }
    }

    public void hideLoadingView () {
        if (isViewAttached()) {
            getView().hideLoadingView();
        }
    }

    public void showLoadNetErroView(boolean isTimeOut) {
        if (isViewAttached()) {
            getView().showLoadNetErroView(isTimeOut);
        }
    }

    public void showLoadEmptyView() {
        if (isViewAttached()) {
            getView().showLoadEmptyView();
        }
    }

    public void showLoadEmptyView(String emptyText) {
        if (isViewAttached()) {
            getView().showLoadEmptyView(emptyText);
        }
    }

}
