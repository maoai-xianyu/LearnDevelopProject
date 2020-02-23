package com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author zhangkun
 * @time 2020-02-23 11:14
 */
public class SubMenuC extends MenuComponent {
    private ArrayList<MenuComponent> menuItems;

    public SubMenuC() {
        this.menuItems = new ArrayList<>();
        addItem("Apple Cookies", "Apple Cookies", true, 1.99f);
        addItem("Banana Cookies", "Banana Cookies", false, 4.99f);
        addItem("Orange Cookies", "Orange Cookies", true, 1.99f);
    }

    private void addItem(String name, String description, boolean vegetable, float price) {
        MenuItemC menuItemC = new MenuItemC(name, description, vegetable, price);
        menuItems.add(menuItemC);
    }

    @Override
    public void print() {
        System.out.println("***this is a submenuC");
    }

    @Override
    public Iterator getIterator() {
        return new ComposeIterator(menuItems.iterator());
    }
}
