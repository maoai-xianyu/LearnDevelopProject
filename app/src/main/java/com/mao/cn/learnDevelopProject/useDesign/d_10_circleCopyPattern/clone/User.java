package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.clone;

import androidx.annotation.NonNull;

/**
 * @author zhangkun
 * @time 2020-05-28 11:21
 * @Description
 */
public class User implements Cloneable {

    public String userName;
    public int age;
    public Address mAddress;

    @NonNull
    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }


    public static class Address {
        public String addressName;
        public String city;

        public Address(String addressName, String city) {
            this.addressName = addressName;
            this.city = city;
        }
    }
}
