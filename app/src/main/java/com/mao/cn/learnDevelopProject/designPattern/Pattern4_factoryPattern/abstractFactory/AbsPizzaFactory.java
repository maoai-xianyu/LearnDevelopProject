package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.abstractFactory;

import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.Pizza;

/**
 * @author zhangkun
 * @time 2020-02-17 15:26
 */
public interface AbsPizzaFactory {
    public Pizza createPizza(String orderType);
}
