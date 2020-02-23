package com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author zhangkun
 * @time 2020-02-21 10:48
 */
public class CakeHouseMenuC extends MenuComponent {

    private ArrayList<MenuComponent> mMenuItems;

    public CakeHouseMenuC() {
        mMenuItems = new ArrayList<>();
        addItem("KFC Cake Breakfast", "boiled egg", false, 3.99f);
        addItem("MDL Cake Breakfast", "fried egg",  false, 4.99f);
        addItem("Stawberry Cake Breakfast", "fresh egg", true, 1.99f);
        addItem("Regular Cake Breakfast", "toast", true, 2.99f);

    }

    private void addItem(String name, String description, boolean vegetable, float price) {
        MenuItemC menuItem = new MenuItemC(name, description, vegetable, price);
        mMenuItems.add(menuItem);
    }

    @Override
    public void print() {
        System.out.println("***this is a CakeHouseMenuC****");
    }


    public Iterator getIterator() {
        return new ComposeIterator(mMenuItems.iterator());
    }

}
