package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.ooIteratorMode;

/**
 * @author zhangkun
 * @time 2020-02-21 10:46
 */
public class MenuItem {
    private String name, description;
    private boolean vegetable;
    private float price;

    public MenuItem(String name, String description, boolean vegetable, float price) {
        this.name = name;
        this.description = description;
        this.vegetable = vegetable;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public boolean isVegetable() {
        return vegetable;
    }

    public float getPrice() {
        return price;
    }
}
