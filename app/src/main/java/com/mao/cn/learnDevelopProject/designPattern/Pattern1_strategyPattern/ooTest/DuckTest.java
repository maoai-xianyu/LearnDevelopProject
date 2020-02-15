package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-02-14 21:07
 */
public class DuckTest {

    public static void main(String[] args) {
        RedHeadDuck redHeadDuck = new RedHeadDuck();
        redHeadDuck.display();
        redHeadDuck.quack();
        redHeadDuck.swim();
        redHeadDuck.fly();

        GreenHeadDuck greenHeadDuck = new GreenHeadDuck();
        greenHeadDuck.display();
        greenHeadDuck.quack();
        greenHeadDuck.swim();
        greenHeadDuck.fly();

    }
}
