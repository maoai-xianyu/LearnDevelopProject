package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.circleCopyClone;

import androidx.annotation.NonNull;

/**
 * @author zhangkun
 * @time 2020-05-27 23:39
 * @Description 出货的箱子接口
 */
public abstract class IBox implements Cloneable{

    abstract void setNumber(int number); // 设置箱子的数量

    abstract int getNumber();//有多少货

    @NonNull
    @Override
    protected IBox clone() throws CloneNotSupportedException {
        return (IBox) super.clone();
    }


}
