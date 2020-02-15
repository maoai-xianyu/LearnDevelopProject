package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.interImpl;

import com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.inter.QuackBehavior;

/**
 * @author zhangkun
 * @time 2020-02-15 18:14
 */
public class QuackGeGeBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("***QuackGeGeBehavior****");
    }
}
