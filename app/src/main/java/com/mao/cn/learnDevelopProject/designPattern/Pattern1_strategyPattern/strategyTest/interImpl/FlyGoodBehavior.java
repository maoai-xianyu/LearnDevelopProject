package com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.interImpl;

import com.mao.cn.learnDevelopProject.designPattern.Pattern1_strategyPattern.strategyTest.inter.FlyBehavior;

/**
 * @author zhangkun
 * @time 2020-02-15 18:12
 */
public class FlyGoodBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("***FlyGoodBehavior***");
    }
}
