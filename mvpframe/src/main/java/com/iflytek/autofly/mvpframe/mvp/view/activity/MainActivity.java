package com.iflytek.autofly.mvpframe.mvp.view.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.iflytek.autofly.mvpframe.R;
import com.iflytek.autofly.mvpframe.base.BaseActivity;
import com.iflytek.autofly.mvpframe.base.RetryLoadInterface;
import com.iflytek.autofly.mvpframe.mvp.model.bean.NaviBean;
import com.iflytek.autofly.mvpframe.mvp.model.iview.IMainAtyView;
import com.iflytek.autofly.mvpframe.mvp.presenter.MainPresenter;
import com.iflytek.autofly.mvpframe.utils.Constants;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import com.iflytek.autofly.mvpframe.utils.NoDoubleClickUtil;
import com.iflytek.autofly.mvpframe.utils.RxjavaZipTest;
import java.util.List;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainAtyView, RetryLoadInterface {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tv_net_load)
    TextView tvNetLoad;

    @BindView(R.id.iv_glide)
    ImageView ivGlide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermessions();
    }

    private void requestPermessions() {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewAndListener() {
        Glide.with(this)
                .load("http://pic1.iqiyipic.com/image/20191106/1b/a8/v_140690116_m_601_m1.jpg")
                .centerCrop()
                .placeholder(R.mipmap.cover_default)
                .error(R.mipmap.cover_default)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(Constants.ROUND_RADIUS)))
                .into(ivGlide);
    }

    @Override
    protected void initData() {
        mPresenter.reqNaviInfo();
        RxjavaZipTest.zipTest();
    }

    @OnClick({R.id.tv_net_load})
    public void onClick(View view) {
        if (NoDoubleClickUtil.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_net_load:
                LogHelper.d(TAG, "btn net click");
                mPresenter.reqNaviInfo();
                break;
        }
    }

    @Override
    protected RetryLoadInterface setRetryLoad() {
        return this;
    }

    @Override
    protected MainPresenter cretaePresenter() {
        return new MainPresenter();
    }

    @Override
    public void initHomeTop(List<NaviBean> naviBeans) {
        LogHelper.d(TAG, "initHomeTop : " + naviBeans.toString());
    }

    @Override
    public void retryLoad() {
        mPresenter.reqNaviInfo();
    }
}