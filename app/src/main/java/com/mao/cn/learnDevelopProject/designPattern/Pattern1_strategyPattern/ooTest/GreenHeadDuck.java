package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-02-14 21:06
 */
public class GreenHeadDuck extends Duck {

    @Override
    public void display() {
        System.out.println("***GreenHead***");
    }

    @Override
    public void fly() {
        System.out.println("***GreenHead no fly***");
    }
}
