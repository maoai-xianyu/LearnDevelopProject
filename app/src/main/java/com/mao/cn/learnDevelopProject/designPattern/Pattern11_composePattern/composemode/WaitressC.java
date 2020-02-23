package com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author zhangkun
 * @time 2020-02-21 14:28
 */
public class WaitressC {
    private ArrayList<MenuComponent> mIterators = new ArrayList<>();

    public WaitressC() {
    }

    public void addIterator(MenuComponent menuComponent) {
        mIterators.add(menuComponent);
    }

    public void printMenu() {
        Iterator iterator;
        MenuComponent menuItem;
        for (int i = 0; i < mIterators.size(); i++) {
            mIterators.get(i).print();
            iterator = mIterators.get(i).getIterator();
            while (iterator.hasNext()) {
                menuItem = (MenuComponent) iterator.next();
                menuItem.print();
            }
        }
    }

    public void printVegetableMenu() {
        Iterator iterator;
        MenuComponent menuItem;
        for (int i = 0; i < mIterators.size(); i++) {
            mIterators.get(i).print();
            iterator = mIterators.get(i).getIterator();
            while (iterator.hasNext()) {
                menuItem = (MenuComponent) iterator.next();
                if (menuItem.isVegetable()) {
                    menuItem.print();
                }
            }
        }
    }
}
