package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.abstractFactory;

import com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhangkun
 * @time 2020-02-17 15:29
 */
public class OrderPizzaAbsFactory {

    AbsPizzaFactory mAbsPizzaFactory;

    public OrderPizzaAbsFactory(AbsPizzaFactory absPizzaFactory) {
        createPizza(absPizzaFactory);
    }

    private void createPizza(AbsPizzaFactory absPizzaFactory) {

        Pizza pizza = null;
        String orderType;
        this.mAbsPizzaFactory = absPizzaFactory;
        do {
            orderType = gettype();
            pizza = mAbsPizzaFactory.createPizza(orderType);
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
