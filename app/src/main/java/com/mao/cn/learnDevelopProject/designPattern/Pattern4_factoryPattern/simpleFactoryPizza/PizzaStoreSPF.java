package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.simpleFactoryPizza;

/**
 * @author zhangkun
 * @time 2020-02-17 10:51
 */
public class PizzaStoreSPF {
    public static void main(String[] args) {
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        OrderPizzaSPF orderPizza = new OrderPizzaSPF(simplePizzaFactory);

    }
}
