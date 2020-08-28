package com.iflytek.autofly.mvpframe.mvp.model.iview;

import com.iflytek.autofly.mvpframe.base.IBaseView;
import com.iflytek.autofly.mvpframe.mvp.model.bean.NaviBean;
import java.util.List;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/1/2 13:42
 */
public interface IMainAtyView extends IBaseView {

    /**
     * 导航栏及主页加载
     */
    void initHomeTop(List<NaviBean> naviBeans);

}
