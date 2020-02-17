package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza;

/**
 * @author zhangkun
 * @time 2020-02-16 22:01
 */
public class NYCheesePizza extends Pizza {
    @Override
    public void prepare() {
        super.setName("NYCheesePizza");
        System.out.println(name+" preparing...");
    }
}
