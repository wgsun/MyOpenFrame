package com.iflytek.autofly.mvpframe.greendao;

import android.content.Context;

import com.iflytek.autofly.mvpframe.utils.LogHelper;

/**
 * @author wgsun
 * @descrbe 创建数据库、创建数据库表、包含增删改查的操作以及数据库的升级
 * @since 2020/3/26 10:34
 */
public class DaoManager {
    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "albumDao";

    private Context context;

    //多线程中要被共享的使用volatile关键字修饰
    private volatile static DaoManager manager = new DaoManager();
    private static DaoMaster sDaoMaster;
    private static DaoMaster.DevOpenHelper sHelper;
    private static DaoSession sDaoSession;

    /**
     * 单例模式获得操作数据库对象
     *
     * @return
     */
    public static DaoManager getInstance() {
        return manager;
    }

    private DaoManager() {
    }

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (sDaoMaster == null) {
            try {
                DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
                sDaoMaster = new DaoMaster(helper.getWritableDatabase());
            } catch (Exception e) {
                if (e != null) {
                    LogHelper.e(TAG, "getDaoMaster fail : " + e.getMessage());
                }
                sDaoMaster = null;
            }
        }
        return sDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (sDaoSession == null) {
            sDaoMaster = getDaoMaster();
            if (sDaoMaster != null) {
                sDaoSession = sDaoMaster.newSession();
            }
        }
        return sDaoSession;
    }


    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (sHelper != null) {
            sHelper.close();
            sHelper = null;
        }
    }

    public void closeDaoSession() {
        if (sDaoSession != null) {
            sDaoSession.clear();
            sDaoSession = null;
        }
    }
}