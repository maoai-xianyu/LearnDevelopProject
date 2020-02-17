package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza;

/**
 * @author zhangkun
 * @time 2020-02-17 10:46
 */
public class ChinesePizza extends Pizza {

    @Override
    public void prepare() {
        super.setName("ChinesePizza");
        System.out.println("ChinesePizza prepare...");
    }
}
