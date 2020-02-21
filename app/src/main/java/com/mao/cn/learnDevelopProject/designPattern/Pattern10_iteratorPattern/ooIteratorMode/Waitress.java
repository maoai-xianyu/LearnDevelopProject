package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.ooIteratorMode;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-02-21 14:09
 */
public class Waitress {

    private DinerMenu mDinerMenu;
    private CakeHouseMenu mCakeHouseMenu;
    private ArrayList<MenuItem> cakeitems;
    private MenuItem[] dineritems;


    public Waitress() {
        mCakeHouseMenu = new CakeHouseMenu();
        cakeitems = mCakeHouseMenu.getMenuItems();

        mDinerMenu = new DinerMenu();
        dineritems = mDinerMenu.getMenuItems();
    }

    public void printMenu() {
        MenuItem menuItem;

        for (int i = 0; i < cakeitems.size(); i++) {
            menuItem = cakeitems.get(i);
            System.out.println(menuItem.getName()+"**"+menuItem.getPrice()+"**"+menuItem.getDescription());
        }

        for (int i = 0; i <mDinerMenu.numberOfItems; i++) {
            menuItem = dineritems[i];
            System.out.println(menuItem.getName()+"--"+menuItem.getPrice()+"--"+menuItem.getDescription());
        }

    }

    public void printBreakfastMenu() {

    }

    public void printLunchMenu() {

    }

    public void printVegetbaleMenu() {

    }
}
