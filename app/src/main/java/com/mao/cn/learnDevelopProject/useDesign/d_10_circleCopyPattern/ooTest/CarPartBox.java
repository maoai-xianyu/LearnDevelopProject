package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-05-27 23:46
 * @Description 具体出货的物品 - 汽车的零件
 */
public class CarPartBox implements IBox {

    private int number;
    private String name;
    private String carBand; // 品牌

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

    public String getCarBand() {
        return carBand;
    }

    public void setCarBand(String carBand) {
        this.carBand = carBand;
    }
}
