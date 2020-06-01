package com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation.iterator.ListIterator;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;


/**
 * 单例设计模式，activity 管理
 */
public class TabBottomActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;

    TabBottomNavigation tabBottom;

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_tab_view;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setVisibility(View.VISIBLE);
        mDTextView.setText("迭代器设计模式");

        tabBottom = findViewById(R.id.tabBottom);


        ListIterator<MainBottomTabItem> listIterator = new ListIterator<>();
        listIterator.addItem(new MainBottomTabItem.Builder(this).text("首页")
                .resIcon(R.drawable.item_tab_selected)
                .create());
        listIterator.addItem(new MainBottomTabItem.Builder(this).text("发现")
                .resIcon(R.drawable.item_tab_selected)
                .create());
        listIterator.addItem(new MainBottomTabItem.Builder(this).text("我的")
                .resIcon(R.drawable.item_tab_selected)
                .create());


        /*List<BottomTabItem> bottomTabItemList = new ArrayList<>();

        bottomTabItemList.add(new MainBottomTabItem.Builder(this).text("首页")
                .resIcon(R.drawable.item_tab_selected)
                .create());
        bottomTabItemList.add(new MainBottomTabItem.Builder(this).text("发现")
                .resIcon(R.drawable.item_tab_selected)
                .create());
        bottomTabItemList.add(new MainBottomTabItem.Builder(this).text("我的")
                .resIcon(R.drawable.item_tab_selected)
                .create());

        tabBottom.addItemTabView(bottomTabItemList);*/

        tabBottom.addItemTabViewIt(listIterator);
    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));


    }

}
