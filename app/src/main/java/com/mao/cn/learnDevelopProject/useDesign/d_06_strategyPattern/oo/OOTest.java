package com.mao.cn.learnDevelopProject.useDesign.d_06_strategyPattern.oo;

/**
 * @author zhangkun
 * @time 2020-05-20 10:57
 * @Description
 */
public class OOTest {

    public static void main(String[] args) {
        OOFinance ooFinance = new OOFinance();

        float finance = ooFinance.finance(3, 10000, OOFinance.Finance.ZHI_FU_BAO);
        System.out.println("收益 " + finance);
    }
}
