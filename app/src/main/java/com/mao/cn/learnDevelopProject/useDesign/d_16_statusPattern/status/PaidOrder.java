package com.mao.cn.learnDevelopProject.useDesign.d_16_statusPattern.status;

/**
 * @author zhangkun
 * @time 2020-06-05 23:31
 * @Description
 */
public class PaidOrder implements OrderOperate {

    @Override
    public void pay() {

    }

    @Override
    public void sendGoods() {
        System.out.println("发货");
    }
}
