package com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation.iterator.TabIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-06-01 09:43
 * @Description
 */
public class TabBottomNavigation extends LinearLayout {

    private List<BottomTabItem> mBottomTabItems;
    private int mCurrentIndex = -1;

    public TabBottomNavigation(Context context) {
        this(context, null);
    }

    public TabBottomNavigation(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabBottomNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        mBottomTabItems = new ArrayList<>();
    }

    public void addItemTabView(List<BottomTabItem> bottomTabItems) {
        mBottomTabItems.clear();
        mBottomTabItems.addAll(bottomTabItems);

        for (int i = 0; i < bottomTabItems.size(); i++) {
            BottomTabItem tabItem = bottomTabItems.get(i);
            View tabView = tabItem.getTabView();
            addView(tabView);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) tabView.getLayoutParams();
            layoutParams.weight = 1;
            layoutParams.gravity = Gravity.CENTER;
            tabView.setLayoutParams(layoutParams);
            setItemClickListener(tabView, i);
        }
        mBottomTabItems.get(0).setSelected(true);
        mCurrentIndex = 0;
    }



    public void addItemTabViewIt(TabIterator tabIterator) {
        mBottomTabItems.clear();
        // 当期位置
        int index = 0;

        while (tabIterator.hasNext()){
            BottomTabItem tabItem = tabIterator.next();
            View tabView = tabItem.getTabView();
            addView(tabView);
            mBottomTabItems.add(tabItem);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) tabView.getLayoutParams();
            layoutParams.weight = 1;
            layoutParams.gravity = Gravity.CENTER;
            tabView.setLayoutParams(layoutParams);
            setItemClickListener(tabView, index++);
        }
        mBottomTabItems.get(0).setSelected(true);
        mCurrentIndex = 0;
    }

    private void setItemClickListener(View tabView, int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != position) {
                    // 原来的非选中
                    mBottomTabItems.get(mCurrentIndex).setSelected(false);
                    mCurrentIndex = position;
                    // 设置当前的选中
                    mBottomTabItems.get(mCurrentIndex).setSelected(true);

                    // 点击的位置，接口回调回去
                }
            }
        });


    }

}
