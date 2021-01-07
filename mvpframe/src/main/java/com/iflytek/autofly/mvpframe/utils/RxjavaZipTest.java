package com.iflytek.autofly.mvpframe.utils;

import android.os.SystemClock;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wgsun
 * @descrbe
 * @since 2021/1/7 17:15
 */
public class RxjavaZipTest {

    public static final String ZIP_TAG = RxjavaZipTest.class.getSimpleName();

    public static void zipTest() {
        Observable<Integer> intObservable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.d(ZIP_TAG, "1");
                e.onNext(1);
                SystemClock.sleep(1000);
                Log.d(ZIP_TAG, "2");
                e.onNext(2);
                SystemClock.sleep(1000);
                Log.d(ZIP_TAG, "3");
                e.onNext(3);
                SystemClock.sleep(1000);
                Log.d(ZIP_TAG, "4");
                e.onNext(4);
                SystemClock.sleep(1000);
                Log.d(ZIP_TAG, "onComplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.d(ZIP_TAG, "A");
                e.onNext("A");
                SystemClock.sleep(1000);
                Log.d(ZIP_TAG, "B");
                e.onNext("B");
                SystemClock.sleep(1000);
                Log.d(ZIP_TAG, "C");
                e.onNext("C");
                SystemClock.sleep(1000);
                Log.d(ZIP_TAG, "onComplete");
                e.onComplete();
            }
        });

        Observable.zip(intObservable, stringObservable, new BiFunction<Integer, String, String>() {

            @NonNull
            @Override
            public String apply(@NonNull Integer a, @NonNull String b) throws Exception {
                return a + b;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(ZIP_TAG, "result=" + s);
            }
        });
    }


}
