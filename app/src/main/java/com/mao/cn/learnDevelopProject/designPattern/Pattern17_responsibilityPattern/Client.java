package com.mao.cn.learnDevelopProject.designPattern.Pattern17_responsibilityPattern;

/**
 * @author zhangkun
 * @time 2020-03-02 11:43
 */
public class Client {

    public Client() {

    }

    public PurchaseRequest setRequest(int type, int number, float price) {
        return new PurchaseRequest(type, number, price);
    }
}
