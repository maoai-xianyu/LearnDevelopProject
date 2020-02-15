package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest;

import com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.interImpl.FlyBadBehavior;
import com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.interImpl.QuackGeGeBehavior;

/**
 * @author zhangkun
 * @time 2020-02-15 18:24
 */
public class StrategyGreenHeadDuck extends StrategyDuck {

    public StrategyGreenHeadDuck() {
        flyBehavior = new FlyBadBehavior();
        quackBehavior = new QuackGeGeBehavior();
    }

    @Override
    public void display() {
        System.out.println("***StrategyGreenHeadDuck***");
    }
}
