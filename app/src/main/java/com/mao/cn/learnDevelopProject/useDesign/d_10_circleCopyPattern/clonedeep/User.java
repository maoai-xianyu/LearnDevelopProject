package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.clonedeep;

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

        User clone = (User) super.clone();
        // 把地址也做一次克隆，达到深拷贝
        clone.mAddress = mAddress.clone();
        return clone;
    }
}
