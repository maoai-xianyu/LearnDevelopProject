package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.abstractFactory;

import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.LDCheesePizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.LDPepperPizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.Pizza;

/**
 * @author zhangkun
 * @time 2020-02-17 15:27
 */
public class LDPizzaFactory implements AbsPizzaFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")){
            pizza =new LDCheesePizza();
        }else if (orderType.equals("pepper")){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
