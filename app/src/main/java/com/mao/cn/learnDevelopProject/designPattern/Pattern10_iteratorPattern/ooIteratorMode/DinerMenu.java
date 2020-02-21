package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.ooIteratorMode;

/**
 * @author zhangkun
 * @time 2020-02-21 10:54
 */
public class DinerMenu {
    private final static int Max_Items = 5;
    public int numberOfItems = 0;
    private MenuItem[] mMenuItems;

    public DinerMenu() {
        mMenuItems = new MenuItem[Max_Items];
        mMenuItems = new MenuItem[Max_Items];
        addItem("vegetable ", "boiled egg", true, 3.99f);
        addItem("Blt", "fried egg", true, 6.99f);
        addItem("bean soup", "fresh egg", false, 1.99f);
        addItem("hot dog", "toast", false, 2.99f);
    }

    private void addItem(String name, String description, boolean vegetable, float price) {
        MenuItem menuItem = new MenuItem(name, description, vegetable, price);

        if (numberOfItems >= Max_Items) {
            System.out.println("sorry,menu is full!");
        }else {
            mMenuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    public MenuItem[] getMenuItems() {
        return mMenuItems;
    }
}
