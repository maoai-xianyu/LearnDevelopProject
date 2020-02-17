package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.simpleFactoryPizza;

import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhangkun
 * @time 2020-02-17 11:23
 */
public class OrderPizzaSPF {
    private SimplePizzaFactory simplePizzaFactory;

    public OrderPizzaSPF(SimplePizzaFactory simplePizzaFactory) {

        setFactory(simplePizzaFactory);
    }

    private void setFactory(SimplePizzaFactory factory) {
        Pizza pizza = null;
        String orderType;
        this.simplePizzaFactory = factory;
        do {
            orderType = gettype();
            pizza = simplePizzaFactory.createPizza(orderType);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                break;
            }
        } while (true);
    }

    private String gettype() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza type:");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
