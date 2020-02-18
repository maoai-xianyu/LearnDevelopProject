package com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.simpleFactoryPizza;

import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.CheesePizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.ChinesePizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.GreekPizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.PepperPizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.Pizza;

/**
 * @author zhangkun
 * @time 2020-02-17 11:19
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        switch (orderType) {
            case "cheese":
                pizza = new CheesePizza();
                break;
            case "greek":
                pizza = new GreekPizza();
                break;
            case "pepper":
                pizza = new PepperPizza();
                break;
            case "chinese":
                pizza = new ChinesePizza();
                break;

        }
        return pizza;
    }

}
