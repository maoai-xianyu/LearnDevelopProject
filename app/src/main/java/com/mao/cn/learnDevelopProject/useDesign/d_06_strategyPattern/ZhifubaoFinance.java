package com.mao.cn.learnDevelopProject.useDesign.d_06_strategyPattern;

/**
 * @author zhangkun
 * @time 2020-05-20 11:00
 * @Description
 */
public class ZhifubaoFinance implements IFinance {

    @Override
    public float finance(int month, int money) {
        if (month == 3) {
            return money + money * 0.047f / 12 * month;
        }

        if (month == 6) {
            return money + money * 0.05f / 12 * month;
        }

        if (month == 12) {
            return money + money * 0.06f / 12 * month;
        }
        throw new IllegalArgumentException("月份不对");
    }
}
