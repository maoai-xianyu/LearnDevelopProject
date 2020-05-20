package com.mao.cn.learnDevelopProject.useDesign.d_06_strategyPattern.oo;

/**
 * @author zhangkun
 * @time 2020-05-20 10:52
 * @Description
 */
public class OOFinance {

    public enum Finance {

        ZHI_FU_BAO, QQ
    }

    public float zhifubaoFinance(int month, int money) {
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


    public float qqFinance(int month, int money) {
        if (month == 3) {
            return money + money * 0.09f / 12 * month;
        }

        if (month == 6) {
            return money + money * 0.12f / 12 * month;
        }

        if (month == 12) {
            return money + money * 0.15f / 12 * month;
        }
        throw new IllegalArgumentException("月份不对");
    }


    public float finance(int month, int money, Finance finance) {
        switch (finance) {
            case ZHI_FU_BAO:
                return zhifubaoFinance(month, money);

            case QQ:
                return qqFinance(month, money);
            default:
                return 0f;
        }
    }


}
