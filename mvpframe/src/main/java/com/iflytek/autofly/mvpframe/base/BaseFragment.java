package com.iflytek.autofly.mvpframe.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;
import com.iflytek.autofly.mvpframe.R;
import com.iflytek.autofly.mvpframe.common.CommonUtil;
import com.iflytek.autofly.mvpframe.manager.NetWorkManager;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import com.iflytek.autofly.mvpframe.utils.NoDoubleClickUtil;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView{

    public static final String TAG = BaseFragment.class.getSimpleName();

    private Unbinder bind;

    public T mPresenter;

    @Nullable
    @BindView(R.id.rl_loading)
    RelativeLayout rlLoading;

    @Nullable
    @BindView(R.id.iv_loading)
    ImageView ivLoading;

    @Nullable
    @BindView(R.id.tv_loading)
    TextView tvLoading;

    @Nullable
    @BindView(R.id.rl_net_error)
    RelativeLayout rlNetError;

    @Nullable
    @BindView(R.id.tv_net_error)
    TextView tvNetError;

    @Nullable
    @BindView(R.id.tv_net_error_tips)
    TextView tvNetErrorTips;

    @Nullable
    @BindView(R.id.btn_net_check)
    Button btnNetCheck;

    private boolean isNetConnected = false;

    private RetryLoadInterface mRetryLoadInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogHelper.d(TAG, "onCreateView");
        View rootView = inflater.inflate(getContentViewId(), container, false);
        bind = ButterKnife.bind(this, rootView);
        mPresenter = cretaePresenter();
        mRetryLoadInterface = setRetryLoad();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initViewAndListener();
        initData();
        return rootView;
    }

    @Override
    public void onStart() {
        LogHelper.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogHelper.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogHelper.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogHelper.d(TAG, "onStop");
        super.onStop();
    }

    public abstract int getContentViewId();

    protected abstract void initData();

    protected abstract RetryLoadInterface setRetryLoad();

    protected abstract void initViewAndListener();

    protected abstract T cretaePresenter();

    @Override
    public void showToast(int resId) {
        Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingView() {
        if (rlLoading != null) {
            /*if (MyAccountManager.getInstance().isVip()) {
                ivLoading.setImageResource(R.mipmap.icon_loading_vip);
            } else {
                ivLoading.setImageResource(R.mipmap.icon_loading_normal);
            }*/
            ivLoading.setImageResource(R.mipmap.icon_loading_default);
            tvLoading.setText(getString(R.string.text_loading));
            rlLoading.setVisibility(View.VISIBLE);
        }
        if (rlNetError != null) {
            rlNetError.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideLoadingView() {
        if (rlLoading != null && rlLoading.getVisibility() == View.VISIBLE) {
            rlLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoadEmptyView() {
        if (rlLoading != null) {
            ivLoading.setImageResource(R.mipmap.icon_loading_error);
            tvLoading.setText(getString(R.string.text_loading_empty_tips));
            rlLoading.setVisibility(View.VISIBLE);
        }
        if (rlNetError != null) {
            rlNetError.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoadEmptyView(String emptyText) {
        if (rlLoading != null) {
            ivLoading.setImageResource(R.mipmap.icon_loading_empty);
            tvLoading.setText(emptyText);
            rlLoading.setVisibility(View.VISIBLE);
        }
        if (rlNetError != null) {
            rlNetError.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoadNetErroView(boolean isTimeOut) {
        if (rlLoading != null && rlLoading.getVisibility() == View.VISIBLE) {
            rlLoading.setVisibility(View.GONE);
        }
        if (rlNetError != null) {
            if (NetWorkManager.getInstance().isNetValid()) {
                isNetConnected = true;
                btnNetCheck.setVisibility(View.GONE);
                if (isTimeOut) {
                    tvNetError.setText(getString(R.string.net_error_tip_timeout));
                } else {
                    tvNetError.setText(getString(R.string.net_error_tip));
                }
                tvNetErrorTips.setText(getString(R.string.net_refrsh_tip));
            } else {
                isNetConnected = false;
                btnNetCheck.setVisibility(View.VISIBLE);
                tvNetError.setText(getString(R.string.net_dis_connect));
                tvNetErrorTips.setText(getString(R.string.net_connect_check));
            }
            rlNetError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoadNetErrorView() {
        if (rlNetError != null && rlNetError.getVisibility() == View.VISIBLE) {
            rlNetError.setVisibility(View.GONE);
        }
    }

    @Optional
    @OnClick(R.id.rl_net_error)
    public void netErrotClick() {
        if (NoDoubleClickUtil.isFastClick()) {
            return;
        }
        if (isNetConnected) {
            //刷新界面
            if (mRetryLoadInterface != null) {
                mRetryLoadInterface.retryLoad();
            }
        } else {
            CommonUtil.jumptoNetSettingAty(getActivity());
        }
    }

    @Optional
    @OnClick(R.id.btn_net_check)
    public void netCheck() {
        if (NoDoubleClickUtil.isFastClick()) {
            return;
        }
        CommonUtil.jumptoNetSettingAty(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogHelper.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogHelper.d(TAG, "onDestroy");
        if (bind != null) {
            bind.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
