package com.iflytek.autofly.mvpframe.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.iflytek.autofly.mvpframe.R;

public abstract class BaseDialogFragment<T extends BasePresenter> extends DialogFragment implements IBaseView {

    public static final String TAG = BaseDialogFragment.class.getSimpleName();

    private Unbinder bind;

    public T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.fragmentDialogStyle);
        LogHelper.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogHelper.d(TAG, "onCreateView");
        View rootView = inflater.inflate(getContentViewId(), container, false);
        bind = ButterKnife.bind(this, rootView);
        mPresenter = cretaePresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initViewAndListener();
        initData();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogHelper.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogHelper.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogHelper.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogHelper.d(TAG, "onStop");
    }

    public abstract int getContentViewId();

    protected abstract void initData();

    protected abstract void initViewAndListener();

    protected abstract T cretaePresenter();

    @Override
    public void showToast(int resId) {
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showLoadEmptyView() {

    }

    @Override
    public void showLoadEmptyView(String emptyText) {

    }

    @Override
    public void showLoadNetErroView(boolean isTimeOut) {

    }

    @Override
    public void hideLoadNetErrorView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogHelper.d(TAG, "onDestroyView");
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
