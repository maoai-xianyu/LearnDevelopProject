package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.circleCopyClone;

/**
 * @author zhangkun
 * @time 2020-05-27 23:48
 * @Description 装箱子的卡车
 */
public class TruckCar {

    public IBox mIBox;

    public void addBox(IBox box) {
        this.mIBox = box;
    }

    public IBox remove() {
        return mIBox;
    }
}
