package com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation.iterator;

import com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation.BottomTabItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-06-01 12:25
 * @Description
 */
public class ListIterator<T extends BottomTabItem> implements TabIterator {

    private List<T> mTabItems;
    private int index = 0;

    public ListIterator() {

        mTabItems = new ArrayList<>();
    }

    public void addItem(T item) {
        mTabItems.add(item);
    }

    @Override
    public BottomTabItem next() {
        return mTabItems.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < mTabItems.size();
    }
}
