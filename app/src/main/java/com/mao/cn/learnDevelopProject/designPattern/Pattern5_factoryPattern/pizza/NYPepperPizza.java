package com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza;

/**
 * @author zhangkun
 * @time 2020-02-16 22:01
 */
public class NYPepperPizza extends Pizza {
    @Override
    public void prepare() {
        super.setName("NYPepperPizza");
        System.out.println(name+" preparing...");
    }
}
