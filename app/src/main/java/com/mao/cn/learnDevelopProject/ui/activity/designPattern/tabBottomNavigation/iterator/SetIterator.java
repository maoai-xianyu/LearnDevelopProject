package com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation.iterator;

import com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation.BottomTabItem;

/**
 * @author zhangkun
 * @time 2020-06-01 12:25
 * @Description
 */
public class SetIterator<T extends BottomTabItem> implements TabIterator {


    @Override
    public BottomTabItem next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    public void addItem(T item) {
        // 可能还需要写一些代码
    }
}
