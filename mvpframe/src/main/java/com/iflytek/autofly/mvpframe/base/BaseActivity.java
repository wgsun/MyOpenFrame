package com.iflytek.autofly.mvpframe.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public static final String TAG = BaseActivity.class.getSimpleName();

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        setStateBar();
        bind = ButterKnife.bind(this);
        mPresenter = cretaePresenter();
        mRetryLoadInterface = setRetryLoad();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initViewAndListener();
        initData();
    }

    private void setStateBar() {
        try {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            /*ViewGroup content = decorView.findViewById(android.R.id.content);
            ViewGroup child = content != null ? (ViewGroup) content.getChildAt(0) : null;
            if (child != null) {
                child.setBackgroundResource(R.mipmap.icon_bg);
            }*/
        } catch (Exception e) {
        }
    }

    public abstract int getContentViewId();

    protected abstract void initViewAndListener();

    protected abstract void initData();

    protected abstract RetryLoadInterface setRetryLoad();

    protected abstract T cretaePresenter();

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogHelper.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingView() {
        if (rlLoading != null) {
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
            ivLoading.setImageResource(R.mipmap.icon_loading_empty);
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

    /**
     * DialogFragment使用示例
     */
    /*@Override
    public void showLoginQrCode(String code) {
        LogHelper.d(TAG, "showLoginQrCode");
        if (mQrcodeFragment != null && mQrcodeFragment.isVisible()) {
            mQrcodeFragment.showQrCode(code);
        } else {
            mQrcodeFragment = new QrcodeFragment();
            mQrcodeFragment.show(getSupportFragmentManager(), QrcodeFragment.class.getSimpleName());
            mQrcodeFragment.showQrCode(code);
        }
    }*/


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
            CommonUtil.jumptoNetSettingAty(this);
        }
    }

    @Optional
    @OnClick(R.id.btn_net_check)
    public void netCheck() {
        if (NoDoubleClickUtil.isFastClick()) {
            return;
        }
        CommonUtil.jumptoNetSettingAty(this);
    }

    @Override
    protected void onDestroy() {
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
