package com.mao.cn.learnDevelopProject.useDesign.d_06_strategyPattern;

/**
 * @author zhangkun
 * @time 2020-05-20 11:02
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        // 策略模式，在原来的基础上进行了分离
        IFinance iFinance = new QQFinance();
        float finance = iFinance.finance(3, 10000);
        System.out.println("QQ 收益 " + finance);


        IFinance iFinanceZ = new ZhifubaoFinance();

        FinanceContext financeContext = new FinanceContext(iFinanceZ);

        float finance1 = financeContext.finance(3, 10000);
        System.out.println("支付包 收益  "+finance1);
    }
}
