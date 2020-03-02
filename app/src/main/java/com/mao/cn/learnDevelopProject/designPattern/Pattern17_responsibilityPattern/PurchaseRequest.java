package com.mao.cn.learnDevelopProject.designPattern.Pattern17_responsibilityPattern;

/**
 * @author zhangkun
 * @time 2020-03-02 11:29
 */
public class PurchaseRequest {
    private int Type = 0;
    private int Number = 0;
    private float Price = 0;
    private int ID = 0;

    public PurchaseRequest(int type, int number, float price) {
        Type = type;
        Number = number;
        Price = price;
    }

    public int getType() {
        return Type;
    }


    public float getSum() {
        return Number * Price;
    }

    public int getID() {
        return (int) (Math.random() * 1000);
    }
}
