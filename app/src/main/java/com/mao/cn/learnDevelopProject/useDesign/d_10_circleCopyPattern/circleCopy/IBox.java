package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.circleCopy;

/**
 * @author zhangkun
 * @time 2020-05-27 23:39
 * @Description 出货的箱子接口
 */
public interface IBox {

    void setNumber(int number); // 设置箱子的数量

    int getNumber();//有多少货

    // 新增一个方法 - copy
    IBox copy();
}
