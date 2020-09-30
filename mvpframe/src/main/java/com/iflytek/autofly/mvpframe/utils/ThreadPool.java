package com.iflytek.autofly.mvpframe.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private volatile static ThreadPool instance;
    private ExecutorService mExecutorService;

    private ThreadPool() {
        mExecutorService = Executors.newCachedThreadPool();
    }

    public static ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }


    public void execute(Runnable runnable) {
        mExecutorService.execute(runnable);
    }

}
