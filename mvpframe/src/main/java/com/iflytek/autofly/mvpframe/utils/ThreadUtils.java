package com.iflytek.autofly.mvpframe.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wgsun
 * @descrbe 线程切换工具类
 * @since 2020/6/24 10:14
 */
public class ThreadUtils {

    public static final String TAG = ThreadUtils.class.getSimpleName();

    /**
     * 主线程做操作
     * @param uiTask
     */
    public static void doOnUIThread(UITask uiTask) {
        LogHelper.d(TAG, "doOnUIThread");
        Observable.just(uiTask)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UITask>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UITask uiTask) {
                        LogHelper.d(TAG, "onNext");
                        uiTask.doOnUI();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 主线程延时做操作
     * @param uiTask
     */
    public static void doOnUIThread(final UITask uiTask, long timeDelay) {
        LogHelper.d(TAG, "doOnUIThread delay");
        Observable.timer(timeDelay, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogHelper.d(TAG, "onNext");
                        uiTask.doOnUI();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * io线程做操作
     * @param threadTask
     */
    public static void doOnThread(ThreadTask threadTask) {
        LogHelper.d(TAG, "doOnThread");
        Observable.just(threadTask)
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ThreadTask>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ThreadTask threadTask) {
                        LogHelper.d(TAG, "onNext");
                        threadTask.doOnThread();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * io线程延时做操作
     * @param threadTask
     */
    public static void doOnThread(final ThreadTask threadTask, long timeDelay) {
        LogHelper.d(TAG, "doOnThread delay");
        Observable.timer(timeDelay, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogHelper.d(TAG, "onNext");
                        threadTask.doOnThread();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface ThreadTask {
        void doOnThread();
    }

    public interface UITask {
        void doOnUI();
    }
}

