package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-05-27 23:44
 * @Description 具体出货的物品 - 塑料夹子的箱子
 */
public class PlasticClampBox implements IBox {

    private int number;
    private String name;

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
