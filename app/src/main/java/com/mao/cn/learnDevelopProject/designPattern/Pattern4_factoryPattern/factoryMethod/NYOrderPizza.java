package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.factoryMethod;

import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.NYCheesePizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.NYPepperPizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.Pizza;

/**
 * @author zhangkun
 * @time 2020-02-17 15:05
 */
public class NYOrderPizza extends OrderPizza {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new NYCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new NYPepperPizza();
        }
        return pizza;
    }
}
