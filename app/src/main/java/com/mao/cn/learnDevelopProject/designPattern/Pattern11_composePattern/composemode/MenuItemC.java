package com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode;

/**
 * @author zhangkun
 * @time 2020-02-23 11:12
 */
public class MenuItemC extends MenuComponent {
    private String name, description;
    private boolean vegetable;
    private float price;

    public MenuItemC(String name, String description, boolean vegetable, float price) {
        this.name = name;
        this.description = description;
        this.vegetable = vegetable;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public boolean isVegetable() {
        return vegetable;
    }

    @Override
    public void print() {
        System.out.println(getName() + "****" + getPrice() + "*****" + getDescription());
    }

    // 子菜单没有菜单项，不用实现父类的getIterator()方法，所以菜单项获取的是空迭代器
}
