package com.iflytek.autofly.mvpframe.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * @author wgsun
 * @descrbe 播放记录实体类
 * @since 2020/3/6 14:37
 */
@Entity
public class AlbumRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 播放的是否是专辑
     */
    private boolean isAlbum;
    /**
     * 专辑id
     */
    private long albumId;
    /**
     * 视频id
     */
    @Id
    private Long qipuId;

    /**
     * 封面url
     */
    private String albumPic;

    /**
     * 专辑名称
     */
    private String albumName;

    /**
     * 当前播放集数
     */
    private int order;

    /**
     * 视频已播放时长
     */
    private int playtime;

    /**
     * 记录产生的时间（超过3天不合法）
     */
    private long addTime;

    /**
     * 是否需要vip
     */
    private boolean isVip;

    /**
     * 视频时长
     */
    private long len;

    @Generated(hash = 813496173)
    public AlbumRecord(boolean isAlbum, long albumId, Long qipuId, String albumPic,
                       String albumName, int order, int playtime, long addTime, boolean isVip,
                       long len) {
        this.isAlbum = isAlbum;
        this.albumId = albumId;
        this.qipuId = qipuId;
        this.albumPic = albumPic;
        this.albumName = albumName;
        this.order = order;
        this.playtime = playtime;
        this.addTime = addTime;
        this.isVip = isVip;
        this.len = len;
    }

    @Generated(hash = 505185999)
    public AlbumRecord() {
    }

    public boolean getIsAlbum() {
        return this.isAlbum;
    }

    public void setIsAlbum(boolean isAlbum) {
        this.isAlbum = isAlbum;
    }

    public long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public Long getQipuId() {
        return this.qipuId;
    }

    public void setQipuId(Long qipuId) {
        this.qipuId = qipuId;
    }

    public String getAlbumPic() {
        return this.albumPic;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPlaytime() {
        return this.playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public long getAddTime() {
        return this.addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public boolean getIsVip() {
        return this.isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    @Override
    public String toString() {
        return "AlbumRecord{" +
                "isAlbum=" + isAlbum +
                ", albumId=" + albumId +
                ", qipuId=" + qipuId +
                ", albumPic='" + albumPic + '\'' +
                ", albumName='" + albumName + '\'' +
                ", order=" + order +
                ", playtime=" + playtime +
                ", addTime=" + addTime +
                ", isVip=" + isVip +
                '}';
    }

    public long getLen() {
        return this.len;
    }

    public void setLen(long len) {
        this.len = len;
    }
}
