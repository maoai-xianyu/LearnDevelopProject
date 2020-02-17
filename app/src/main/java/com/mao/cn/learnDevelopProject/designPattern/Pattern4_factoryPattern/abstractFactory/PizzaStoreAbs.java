package com.mao.cn.learnDevelopProject.designPattern.Pattern4_factoryPattern.abstractFactory;

/**
 * @author zhangkun
 * @time 2020-02-17 15:35
 */
public class PizzaStoreAbs {
    public static void main(String[] args) {
        AbsPizzaFactory absPizzaFactory = new NYPizzaFactory();
        OrderPizzaAbsFactory absFactory = new OrderPizzaAbsFactory(absPizzaFactory);
    }
}
