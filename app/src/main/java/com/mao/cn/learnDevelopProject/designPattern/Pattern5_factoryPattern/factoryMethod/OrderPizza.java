package com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.factoryMethod;

import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhangkun
 * @time 2020-02-17 14:46
 */
public abstract class OrderPizza {

    public OrderPizza() {

        Pizza pizza = null;
        String orderType;

        do {
            orderType = gettype();
            pizza = createPizza(orderType);
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


    public abstract Pizza createPizza(String orderType);

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
