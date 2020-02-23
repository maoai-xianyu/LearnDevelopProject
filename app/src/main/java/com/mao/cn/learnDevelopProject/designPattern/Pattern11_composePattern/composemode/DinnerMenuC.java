package com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode;

import java.util.Iterator;


/**
 * @author zhangkun
 * @time 2020-02-21 14:57
 */
public class DinnerMenuC extends MenuComponent {

    private final static int Max_Items = 5;
    public int numberOfItems = 0;
    private MenuComponent[] mMenuItems;

    public DinnerMenuC() {
        mMenuItems = new MenuComponent[Max_Items];
        addItem("vegetable ", "boiled egg", true, 3.99f);
        addItem("Blt", "fried egg", true, 6.99f);
        addItem("bean soup", "fresh egg", false, 1.99f);
        addItem("hot dog", "toast", false, 2.99f);
        addSubMenu(new SubMenuC());
    }

    private void addSubMenu(MenuComponent subMenuC) {
        if (numberOfItems >= Max_Items) {
            System.out.println("sorry,menu is full!can not add anther is full!");
        } else {
            mMenuItems[numberOfItems] = subMenuC;
            numberOfItems++;
        }
    }

    private void addItem(String name, String description, boolean vegetable, float price) {
        MenuItemC menuItem = new MenuItemC(name, description, vegetable, price);

        if (numberOfItems >= Max_Items) {
            System.out.println("sorry,menu is full!");
        } else {
            mMenuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    @Override
    public void print() {
        System.out.println("***this is a DinnerMenuC****");
    }

    public Iterator getIterator() {
        return new ComposeIterator(new DinnerJavaCIterator());
    }

    private class DinnerJavaCIterator implements Iterator {

        private int position;

        public DinnerJavaCIterator() {
            this.position = 0;
        }

        @Override
        public boolean hasNext() {
            if (position < numberOfItems) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {

            MenuComponent menuItem = mMenuItems[position];
            position++;
            return menuItem;
        }

    }
}
