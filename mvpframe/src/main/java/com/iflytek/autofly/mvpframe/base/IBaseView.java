package com.iflytek.autofly.mvpframe.base;

public interface IBaseView {

    void showToast(int resId);

    void showLoadingView();

    void hideLoadingView();

    void showLoadNetErroView(boolean isTimeOut);

    void hideLoadNetErrorView();

    void showLoadEmptyView();

    void showLoadEmptyView(String emptyText);

    //void hideLoadEmptyView();


}
