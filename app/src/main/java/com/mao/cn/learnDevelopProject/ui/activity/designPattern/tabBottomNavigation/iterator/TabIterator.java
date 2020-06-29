package com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation.iterator;

import com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation.BottomTabItem;

/**
 * @author zhangkun
 * @time 2020-06-01 12:21
 * @Description
 */
public interface TabIterator {

    BottomTabItem next();
    boolean hasNext();
}
