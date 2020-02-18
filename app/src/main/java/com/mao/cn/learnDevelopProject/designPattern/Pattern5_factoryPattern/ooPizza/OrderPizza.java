package com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.ooPizza;

import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.CheesePizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.ChinesePizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.GreekPizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.PepperPizza;
import com.mao.cn.learnDevelopProject.designPattern.Pattern5_factoryPattern.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhangkun
 * @time 2020-02-17 10:40
 */
public class OrderPizza {

    public OrderPizza() {
        Pizza pizza = null;
        String orderType;

        label:
        do {
            orderType = gettype();
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
                default:
                    break label;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
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
