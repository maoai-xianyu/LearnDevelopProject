package com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.abstractFactory;

import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.NYCheesePizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.NYPepperPizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.Pizza;

/**
 * @author zhangkun
 * @time 2020-02-17 15:27
 */
public class NYPizzaFactory implements AbsPizzaFactory {
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
