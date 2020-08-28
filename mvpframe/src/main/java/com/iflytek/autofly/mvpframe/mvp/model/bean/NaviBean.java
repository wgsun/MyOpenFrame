package com.iflytek.autofly.mvpframe.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

/**
 * @author wgsun
 * @descrbe 导航栏实体类
 * @since 2019/12/17 19:59
 */
public class NaviBean implements Parcelable {

    public static final int NAVI_RECOMMEND = 0;

    public static final int NAVI_RANK = -1;

    public static final int NAVI_VIP = 214358312;

    /**
     * 频道id
     */
    private int id;

    /**
     * 频道名称
     */
    private String name;

    public NaviBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected NaviBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<NaviBean> CREATOR = new Creator<NaviBean>() {
        @Override
        public NaviBean createFromParcel(Parcel in) {
            return new NaviBean(in);
        }

        @Override
        public NaviBean[] newArray(int size) {
            return new NaviBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NaviBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof NaviBean) {
            NaviBean temp = (NaviBean) obj;
            return temp.getId() == id;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}
