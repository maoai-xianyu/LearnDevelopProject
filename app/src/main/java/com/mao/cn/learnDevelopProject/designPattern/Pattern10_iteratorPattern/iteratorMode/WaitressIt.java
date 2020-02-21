package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.iteratorMode;

import com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.ooIteratorMode.MenuItem;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-02-21 14:28
 */
public class WaitressIt {
    private ArrayList<Iterator> mIterators = new ArrayList<>();

    public WaitressIt() {
    }

    public void addIterator(Iterator iterator) {
        mIterators.add(iterator);
    }

    public void printMenu() {
        Iterator iterator;
        MenuItem menuItem;
        for (int i = 0; i < mIterators.size(); i++) {
            iterator = mIterators.get(i);
            while (iterator.hasNext()) {
                menuItem = (MenuItem) iterator.next();
                System.out.println(menuItem.getName() + "***" + menuItem.getPrice() + "***" + menuItem.getDescription());
            }
        }

    }
}
