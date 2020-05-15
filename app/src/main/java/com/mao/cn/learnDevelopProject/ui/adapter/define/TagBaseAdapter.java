package com.mao.cn.learnDevelopProject.ui.adapter.define;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-03-27 21:55
 */
public abstract class TagBaseAdapter {

    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    private final ArrayList<View> mViews = new ArrayList<>();


    // 1. 有多少个条目
    public abstract int getCounts();

    // 2. getView 通过 position
    public abstract View getView(int position, ViewGroup parent);

    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.removeObserver(observer);
    }

    // 3. 观察者模式，通知更新
    public void notifyDataSetChanged() {
        mDataSetObservable.notifyObservers();
    }


    // 将子view布局添加到总的list里面
    public void addViewToList(ViewGroup parent) {
        mViews.clear();

        int counts = getCounts();

        if (counts == 0) {
            return;
        }
        for (int i = 0; i < counts; i++) {
            mViews.add(getView(i, parent));
        }
    }

    /**
     * 得到列表里面的所有子view
     *
     * @return view集合
     */
    public ArrayList<View> getViewList() {
        return mViews;
    }
}
