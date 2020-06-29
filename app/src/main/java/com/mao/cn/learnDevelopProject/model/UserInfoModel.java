package com.mao.cn.learnDevelopProject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author zhangkun
 * @time 2020-06-04 14:33
 * @Description
 */
public class UserInfoModel implements Parcelable {

    private String userName;
    private String where;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    protected UserInfoModel(Parcel in) {
        userName = in.readString();
        where = in.readString();
    }


    /**
     * 1. 将对象映射成Parcel对象 写  读写的顺序一定要一致
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(where);
    }

    /**
     * public static final 一个不能少，内部对象CREATOR的名称也不能改变，必须全部大写
     *
     *
     *  createFromParcel(Parcel in) 实现从Parcel容器中读取传递数据值，封装成Parcelable对象
     *  newArray(int size) 创建一个类型为T,长度为size的数据，供外部类反序列化本类数组使用
     */
    public static final Creator<UserInfoModel> CREATOR = new Creator<UserInfoModel>() {

        /**
         * 2. 从序列化的对象中创建原始对象
         * 可以把 Parcel 想象成为读写流 读
         * @param in
         * @return
         */
        @Override
        public UserInfoModel createFromParcel(Parcel in) {
            return new UserInfoModel(in);
        }

        /**
         * 创建指定长度的原始对象数组
         * @param size
         * @return
         */
        @Override
        public UserInfoModel[] newArray(int size) {
            return new UserInfoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
