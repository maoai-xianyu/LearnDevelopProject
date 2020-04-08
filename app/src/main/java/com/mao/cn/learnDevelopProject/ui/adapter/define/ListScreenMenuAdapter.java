package com.mao.cn.learnDevelopProject.ui.adapter.define;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;
import com.mao.cn.learnDevelopProject.widgets.ListDataScreenView;

/**
 * Created by zhangkun
 * on 2020/4/9.
 */

public class ListScreenMenuAdapter extends BaseMenuAdapter {
    private Context mContext;

    public ListScreenMenuAdapter(Context context) {
        this.mContext = context;
    }

    // 方式二：可以让Adapter去持有我们 ListDataScreenView 的对象
    private ListDataScreenView mListDataScreenView;

    public ListScreenMenuAdapter(Context context, ListDataScreenView listDataScreenView) {
        this.mContext = context;
        this.mListDataScreenView = listDataScreenView;
    }

    private String[] mItems = {"类型", "品牌", "价格", "更多"};

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public View getTabView(int position, ViewGroup parent) {
        TextView tabView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.ui_list_data_screen_tab, parent, false);
        tabView.setText(mItems[position]);
        tabView.setTextColor(Color.BLACK);
        return tabView;
    }

    @Override
    public View getMenuView(int position, ViewGroup parent) {
        // 真正开发过程中，不同的位置显示的布局不一样
        TextView menuView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.ui_list_data_screen_menu, parent, false);
        menuView.setText(mItems[position]);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("点击之后需要关闭menu");
                // 方案三：观察者模式
                notifyChangeCloseMenu();

                // mListDataScreenView.closeMenu();
                /*if (mClickCloseListener != null) {
                    mClickCloseListener.closeMenu();
                }*/
            }
        });

        return menuView;
    }

    @Override
    public void menuClose(View tabView) {
        TextView tabTv = (TextView) tabView;
        tabTv.setTextColor(Color.BLACK);
    }

    @Override
    public void menuOpen(View tabView) {
        TextView tabTv = (TextView) tabView;
        tabTv.setTextColor(Color.RED);
    }


    // 方式一：可以写一个监听通过Activity去掉用 ListDataScreenView 的关闭方法
    private ClickCloseListener mClickCloseListener;

    public void setClickCloseListener(ClickCloseListener clickCloseListener) {
        mClickCloseListener = clickCloseListener;
    }

    public interface ClickCloseListener {
        void closeMenu();
    }
}
