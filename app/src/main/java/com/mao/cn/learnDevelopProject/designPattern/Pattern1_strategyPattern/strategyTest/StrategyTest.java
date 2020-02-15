package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest;

/**
 * @author zhangkun
 * @time 2020-02-15 18:08
 */
public class StrategyTest {

    public static void main(String[] args) {

        StrategyDuck strategyDuckRed = new StrategyRedHeadDuck();
        StrategyDuck strategyDuckGreen = new StrategyGreenHeadDuck();

        strategyDuckRed.display();
        strategyDuckRed.fly();
        strategyDuckRed.quack();

        strategyDuckGreen.display();
        strategyDuckGreen.fly();
        strategyDuckGreen.quack();

    }
}
