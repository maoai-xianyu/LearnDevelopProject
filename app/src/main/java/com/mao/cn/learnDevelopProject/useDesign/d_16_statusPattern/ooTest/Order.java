package com.mao.cn.learnDevelopProject.useDesign.d_16_statusPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-06-05 22:12
 * @Description
 */
public class Order {


    public final int OBLIGATION = 1;
    public final int PAID = 2;
    public final int RECEIVING_GOOD = 3;//待收货
    public final int COMMIT_GOOD = 4;//发表

    // 订单状态
    public int mStatus;

    // 付款
    public void pay() {
        if (mStatus == OBLIGATION) {
            mStatus = PAID;
            System.out.println("付款");
        } else {
            System.out.println("不在状态");
        }
    }


    public void sendGoods() {
        if (mStatus == PAID) {
            mStatus = RECEIVING_GOOD  ;
            System.out.println("发货");
        } else {
            System.out.println("不在状态");
        }
    }


}

