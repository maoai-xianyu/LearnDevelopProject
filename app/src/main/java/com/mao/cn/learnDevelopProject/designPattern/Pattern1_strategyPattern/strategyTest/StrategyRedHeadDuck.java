package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest;

import com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.interImpl.FlyGoodBehavior;
import com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.interImpl.QuackGaGaBehavior;

/**
 * @author zhangkun
 * @time 2020-02-15 18:24
 */
public class StrategyRedHeadDuck extends StrategyDuck {

    public StrategyRedHeadDuck() {
        flyBehavior = new FlyGoodBehavior();
        quackBehavior = new QuackGaGaBehavior();
    }

    @Override
    public void display() {
        System.out.println("***StrategyRedHeadDuck***");
    }
}
