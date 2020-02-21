package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.iteratorMode;

import com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.ooIteratorMode.MenuItem;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-02-21 10:48
 */
public class CakeHouseMenuIt {

    private ArrayList<MenuItem> mMenuItems;

    public CakeHouseMenuIt() {
        mMenuItems = new ArrayList<>();
        addItem("KFC Cake Breakfast", "boiled egg", false, 3.99f);
        addItem("MDL Cake Breakfast", "fried egg", false, 4.99f);
        addItem("Stawberry Cake Breakfast", "fresh egg", true, 1.99f);
        addItem("Regular Cake Breakfast", "toast", true, 2.99f);

    }

    private void addItem(String name, String description, boolean vegetable, float price) {
        MenuItem menuItem = new MenuItem(name, description, vegetable, price);
        mMenuItems.add(menuItem);
    }

    public Iterator getIterator() {
        return new CakeHouseIterator();
    }

    class CakeHouseIterator implements Iterator {
        private int position = 0;

        @Override
        public boolean hasNext() {
            if (position < mMenuItems.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            MenuItem menuItem = mMenuItems.get(position);
            position++;
            return menuItem;
        }
    }
}
