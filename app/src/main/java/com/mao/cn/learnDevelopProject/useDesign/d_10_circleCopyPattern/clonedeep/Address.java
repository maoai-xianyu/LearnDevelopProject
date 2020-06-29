package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.clonedeep;

import androidx.annotation.NonNull;

/**
 * @author zhangkun
 * @time 2020-05-28 11:58
 * @Description
 */
public class Address implements Cloneable {
    public String addressName;
    public String city;

    public Address(String addressName, String city) {
        this.addressName = addressName;
        this.city = city;
    }

    @NonNull
    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}
