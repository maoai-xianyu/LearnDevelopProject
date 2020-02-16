package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern;

/**
 * @author zhangkun
 * @time 2020-02-16 17:10
 */
public abstract class Drink {
    public String description = "";
    private float price = 0f;

    public String getDescription() {
        return description + "-" + this.getPrice();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract float cost();
}
