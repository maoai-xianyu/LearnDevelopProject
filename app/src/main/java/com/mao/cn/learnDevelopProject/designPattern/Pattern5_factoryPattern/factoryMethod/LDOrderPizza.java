package com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.factoryMethod;

import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.LDCheesePizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.LDPepperPizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.Pizza;

/**
 * @author zhangkun
 * @time 2020-02-17 15:05
 */
public class LDOrderPizza extends OrderPizza {

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
