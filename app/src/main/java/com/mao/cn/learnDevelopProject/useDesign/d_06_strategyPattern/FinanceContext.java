package com.mao.cn.learnDevelopProject.useDesign.d_06_strategyPattern;

/**
 * @author zhangkun
 * @time 2020-05-20 11:03
 * @Description 策略模式的上下文，有点类似 android  里面的 context
 * 可以获取一些额外的信息, 这个类有点多余，可以不写
 */
public class FinanceContext {

    IFinance mIFinance;

    public FinanceContext(IFinance IFinance) {
        mIFinance = IFinance;
    }

    public float finance(int month, int money) {
        return mIFinance.finance(month, money);
    }
}
