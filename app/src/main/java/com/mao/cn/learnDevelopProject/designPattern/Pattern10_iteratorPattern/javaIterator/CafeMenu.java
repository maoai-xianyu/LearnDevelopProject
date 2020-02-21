package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.javaIterator;

import com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.ooIteratorMode.MenuItem;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * @author zhangkun
 * @time 2020-02-21 15:32
 */
public class CafeMenu {
    private Hashtable<String, MenuItem> mHashtable = new Hashtable<>();

    public CafeMenu() {

        addItem("Moca Burget", "boiled egg", true, 1.99f);
        addItem("Soup Latte", "fried", true, 7.99f);
        addItem("hot cofe", "toast", false, 2.99f);

    }

    private void addItem(String name, String description, boolean vegetable, float price) {
        MenuItem menuItem = new MenuItem(name, description, vegetable, price);
        mHashtable.put(name, menuItem);
    }

    public Iterator getIterator() {
        return mHashtable.values().iterator();
    }
}
