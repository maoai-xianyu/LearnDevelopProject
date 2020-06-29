package com.mao.cn.learnDevelopProject.useDesign.d_16_statusPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-06-05 23:04
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        Order order = new Order();
        order.pay();
        order.sendGoods();


    }
}
