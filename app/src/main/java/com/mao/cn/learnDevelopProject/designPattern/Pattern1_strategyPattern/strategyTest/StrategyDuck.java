package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest;

import com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.inter.FlyBehavior;
import com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.inter.QuackBehavior;

/**
 * @author zhangkun
 * @time 2020-02-15 18:21
 */
public abstract class StrategyDuck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public StrategyDuck() {
    }

    public abstract void display();

    public void fly() {
        flyBehavior.fly();
    }

    public void quack() {
        quackBehavior.quack();
    }

    // 动态改变
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
