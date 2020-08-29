package com.iflytek.autofly.mvpframe.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "ALBUM_RECORD".
*/
public class AlbumRecordDao extends AbstractDao<AlbumRecord, Long> {

    public static final String TABLENAME = "ALBUM_RECORD";

    /**
     * Properties of entity AlbumRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property IsAlbum = new Property(0, boolean.class, "isAlbum", false, "IS_ALBUM");
        public final static Property AlbumId = new Property(1, long.class, "albumId", false, "ALBUM_ID");
        public final static Property QipuId = new Property(2, Long.class, "qipuId", true, "_id");
        public final static Property AlbumPic = new Property(3, String.class, "albumPic", false, "ALBUM_PIC");
        public final static Property AlbumName = new Property(4, String.class, "albumName", false, "ALBUM_NAME");
        public final static Property Order = new Property(5, int.class, "order", false, "ORDER");
        public final static Property Playtime = new Property(6, int.class, "playtime", false, "PLAYTIME");
        public final static Property AddTime = new Property(7, long.class, "addTime", false, "ADD_TIME");
        public final static Property IsVip = new Property(8, boolean.class, "isVip", false, "IS_VIP");
        public final static Property Len = new Property(9, long.class, "len", false, "LEN");
    }


    public AlbumRecordDao(DaoConfig config) {
        super(config);
    }
    
    public AlbumRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ALBUM_RECORD\" (" + //
                "\"IS_ALBUM\" INTEGER NOT NULL ," + // 0: isAlbum
                "\"ALBUM_ID\" INTEGER NOT NULL ," + // 1: albumId
                "\"_id\" INTEGER PRIMARY KEY ," + // 2: qipuId
                "\"ALBUM_PIC\" TEXT," + // 3: albumPic
                "\"ALBUM_NAME\" TEXT," + // 4: albumName
                "\"ORDER\" INTEGER NOT NULL ," + // 5: order
                "\"PLAYTIME\" INTEGER NOT NULL ," + // 6: playtime
                "\"ADD_TIME\" INTEGER NOT NULL ," + // 7: addTime
                "\"IS_VIP\" INTEGER NOT NULL ," + // 8: isVip
                "\"LEN\" INTEGER NOT NULL );"); // 9: len
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ALBUM_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AlbumRecord entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getIsAlbum() ? 1L: 0L);
        stmt.bindLong(2, entity.getAlbumId());
 
        Long qipuId = entity.getQipuId();
        if (qipuId != null) {
            stmt.bindLong(3, qipuId);
        }
 
        String albumPic = entity.getAlbumPic();
        if (albumPic != null) {
            stmt.bindString(4, albumPic);
        }
 
        String albumName = entity.getAlbumName();
        if (albumName != null) {
            stmt.bindString(5, albumName);
        }
        stmt.bindLong(6, entity.getOrder());
        stmt.bindLong(7, entity.getPlaytime());
        stmt.bindLong(8, entity.getAddTime());
        stmt.bindLong(9, entity.getIsVip() ? 1L: 0L);
        stmt.bindLong(10, entity.getLen());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AlbumRecord entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getIsAlbum() ? 1L: 0L);
        stmt.bindLong(2, entity.getAlbumId());
 
        Long qipuId = entity.getQipuId();
        if (qipuId != null) {
            stmt.bindLong(3, qipuId);
        }
 
        String albumPic = entity.getAlbumPic();
        if (albumPic != null) {
            stmt.bindString(4, albumPic);
        }
 
        String albumName = entity.getAlbumName();
        if (albumName != null) {
            stmt.bindString(5, albumName);
        }
        stmt.bindLong(6, entity.getOrder());
        stmt.bindLong(7, entity.getPlaytime());
        stmt.bindLong(8, entity.getAddTime());
        stmt.bindLong(9, entity.getIsVip() ? 1L: 0L);
        stmt.bindLong(10, entity.getLen());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2);
    }    

    @Override
    public AlbumRecord readEntity(Cursor cursor, int offset) {
        AlbumRecord entity = new AlbumRecord( //
            cursor.getShort(offset + 0) != 0, // isAlbum
            cursor.getLong(offset + 1), // albumId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // qipuId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // albumPic
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // albumName
            cursor.getInt(offset + 5), // order
            cursor.getInt(offset + 6), // playtime
            cursor.getLong(offset + 7), // addTime
            cursor.getShort(offset + 8) != 0, // isVip
            cursor.getLong(offset + 9) // len
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AlbumRecord entity, int offset) {
        entity.setIsAlbum(cursor.getShort(offset + 0) != 0);
        entity.setAlbumId(cursor.getLong(offset + 1));
        entity.setQipuId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setAlbumPic(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAlbumName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setOrder(cursor.getInt(offset + 5));
        entity.setPlaytime(cursor.getInt(offset + 6));
        entity.setAddTime(cursor.getLong(offset + 7));
        entity.setIsVip(cursor.getShort(offset + 8) != 0);
        entity.setLen(cursor.getLong(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AlbumRecord entity, long rowId) {
        entity.setQipuId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AlbumRecord entity) {
        if(entity != null) {
            return entity.getQipuId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AlbumRecord entity) {
        return entity.getQipuId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}