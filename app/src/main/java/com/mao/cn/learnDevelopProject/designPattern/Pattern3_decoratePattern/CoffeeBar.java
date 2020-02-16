package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.coffee.Decaf;
import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.coffee.LongBlack;
import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.decortor.Chocolate;
import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.decortor.Milk;
import com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.decortor.Soy;

/**
 * @author zhangkun
 * @time 2020-02-16 17:47
 */
public class CoffeeBar {

    public static void main(String[] args) {
        Drink drink = new Decaf();
        System.out.println("order1 price: " + drink.cost());
        System.out.println("order1 description: " + drink.getDescription());

        System.out.println("***************");

        drink = new LongBlack();
        drink = new Chocolate(drink);
        drink = new Milk(drink);
        drink = new Soy(drink);

        System.out.println("order2 price:" + drink.cost());
        System.out.println("order2 description:" + drink.getDescription());
    }
}
