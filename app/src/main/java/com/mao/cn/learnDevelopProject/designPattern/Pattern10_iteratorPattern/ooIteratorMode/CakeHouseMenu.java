package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.ooIteratorMode;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-02-21 10:48
 */
public class CakeHouseMenu {

    private ArrayList<MenuItem> mMenuItems;

    public CakeHouseMenu() {
        mMenuItems = new ArrayList<>();
        addItem("KFC Cake Breakfast","boiled egg",false,3.99f);
        addItem("MDL Cake Breakfast","fried egg",false,4.99f);
        addItem("Stawberry Cake Breakfast","fresh egg",true,1.99f);
        addItem("Regular Cake Breakfast","toast",true,2.99f);

    }

    private void addItem(String name, String description, boolean vegetable, float price) {
        MenuItem menuItem = new MenuItem(name, description, vegetable, price);
        mMenuItems.add(menuItem);
    }

    public ArrayList<MenuItem> getMenuItems() {
        return mMenuItems;
    }
}
