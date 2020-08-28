package com.iflytek.autofly.mvpframe.greendao;

import android.content.Context;
import com.iflytek.autofly.mvpframe.utils.LogHelper;
import com.iflytek.autofly.mvpframe.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/3/26 10:48
 */
public class AlbumDaoUtils {

    private static final String TAG = AlbumDaoUtils.class.getSimpleName();
    private DaoManager mManager;
    private static AlbumDaoUtils mInstance = null;

    private AlbumDaoUtils(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    public static AlbumDaoUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AlbumDaoUtils.class) {
                if (mInstance == null) {
                    mInstance = new AlbumDaoUtils(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 完成AlbumRecord记录的插入和更新，如果表未创建，先创建AlbumRecord表
     *
     * @param albumRecord
     * @return
     */
    public boolean insertAlbumRecord(AlbumRecord albumRecord) {
        boolean flag = false;
        if (albumRecord != null) {
            if (albumRecord.getIsAlbum()) {
                AlbumRecord record = queryAlbumRecordByAlbumId(albumRecord.getAlbumId());
                if (record != null) {
                    deleteAlbumRecord(record);
                }
            }
            if (mManager.getDaoSession() != null) {
                flag = mManager.getDaoSession().getAlbumRecordDao().insertOrReplace(albumRecord) == -1 ? false : true;
                LogHelper.i(TAG, "insert AlbumRecord :" + flag + "-->" + albumRecord.toString());
            }
        }
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     *
     * @param AlbumRecordList
     * @return
     */
    public boolean insertMultAlbumRecord(final List<AlbumRecord> AlbumRecordList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (AlbumRecord AlbumRecord : AlbumRecordList) {
                        mManager.getDaoSession().insertOrReplace(AlbumRecord);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @param albumRecord
     * @return
     */
    public boolean updateAlbumRecord(AlbumRecord albumRecord) {
        boolean flag = false;
        if (albumRecord != null) {
            try {
                mManager.getDaoSession().update(albumRecord);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @param albumRecord
     * @return
     */
    public boolean deleteAlbumRecord(AlbumRecord albumRecord) {
        boolean flag = false;
        if (albumRecord != null) {
            try {
                //按照id删除
                mManager.getDaoSession().delete(albumRecord);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogHelper.i(TAG, "delete AlbumRecord :" + flag + "-->" + albumRecord.toString());
        }
        return flag;
    }

    /**
     * 批量删除记录
     *
     * @param albumKeys
     * @return
     */
    public boolean deleteAlbumRecords(List<String> albumKeys) {
        boolean flag = false;
        List<Long> keys = new ArrayList<>();
        for (String key : albumKeys) {
            keys.add(Long.valueOf(key));
        }
        try {
            mManager.getDaoSession().getAlbumRecordDao().deleteByKeyInTx(keys);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogHelper.i(TAG, "delete AlbumRecords :" + flag);
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(AlbumRecord.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogHelper.i(TAG, "deleteAll :" + flag);
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<AlbumRecord> queryAllAlbumRecord() {
        if (mManager.getDaoSession() != null) {
            return mManager.getDaoSession().loadAll(AlbumRecord.class);
        } else {
            return null;
        }
    }

    /**
     * 根据albumId 查询记录
     */
    public AlbumRecord queryAlbumRecordByAlbumId(long albumId) {
        if (mManager.getDaoSession() != null) {
            //return mManager.getDaoSession().queryBuilder(AlbumRecord.class).where(AlbumRecordDao.Properties.AlbumId.eq(albumId)).unique();
            List<AlbumRecord> list = mManager.getDaoSession().queryBuilder(AlbumRecord.class).where(AlbumRecordDao.Properties.AlbumId.eq(albumId)).list();
            if (!ObjectUtils.isNull(list) && list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public AlbumRecord queryAlbumRecordById(long key) {
        if (mManager.getDaoSession() != null) {
            return mManager.getDaoSession().load(AlbumRecord.class, key);
        } else {
            return null;
        }
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<AlbumRecord> queryAlbumRecordByNativeSql(String sql, String[] conditions) {
        if (mManager.getDaoSession() != null) {
            return mManager.getDaoSession().queryRaw(AlbumRecord.class, sql, conditions);
        } else {
            return null;
        }

    }

}
