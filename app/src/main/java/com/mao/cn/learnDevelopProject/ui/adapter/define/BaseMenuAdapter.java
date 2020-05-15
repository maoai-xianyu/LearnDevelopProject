package com.mao.cn.learnDevelopProject.ui.adapter.define;

import android.view.View;
import android.view.ViewGroup;

import com.mao.cn.learnDevelopProject.utils.tools.ListU;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-03-27 21:55
 * 筛选菜单的 Adapter
 */

public abstract class BaseMenuAdapter {

    // 微信公众号(别观察者) 注册订阅用户就放入集合  放入观察者
    private List<ListScreenMenuObserver> mListScreenMenuObservers = new ArrayList<>();


    public void registerDataSetObserver(ListScreenMenuObserver observer) {
        mListScreenMenuObservers.add(observer);
    }

    public void unregisterDataSetObserver(ListScreenMenuObserver observer) {
        mListScreenMenuObservers.remove(observer);
    }


    // 通知观察者，有变化
    public void notifyChangeCloseMenu() {
        if (ListU.notEmpty(mListScreenMenuObservers)) {
            for (ListScreenMenuObserver observer : mListScreenMenuObservers) {
                observer.closeScreenMenu();
            }
        }

    }


    // 获取总共有多少条
    public abstract int getCount();

    // 获取当前的TabView
    public abstract View getTabView(int position, ViewGroup parent);

    // 获取当前的菜单内容
    public abstract View getMenuView(int position, ViewGroup parent);

    /**
     * 菜单打开
     *
     * @param tabView
     */
    public void menuOpen(View tabView) {

    }

    /**
     * 菜单关闭
     *
     * @param tabView
     */
    public void menuClose(View tabView) {

    }
}
