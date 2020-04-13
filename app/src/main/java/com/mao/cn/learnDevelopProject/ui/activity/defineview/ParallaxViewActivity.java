package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;
import android.os.Handler;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.widgets.SplashLoadingView;
import com.mao.cn.learnDevelopProject.widgets.parallaxAnimation.ParallaxViewPager;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class ParallaxViewActivity extends BaseActivity {


    @BindView(R.id.parallax)
    ParallaxViewPager mParallaxViewPager;
    @BindView(R.id.splashLoadingView)
    SplashLoadingView splashLoadingView;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_parallax_view;
    }

    @Override
    public void initView() {


        // 给 ParallaxViewPager 设置adapter, 封装 自定义view


        // 给一个方法  给一个布局数组
        mParallaxViewPager.attach(getSupportFragmentManager(),
                new int[]{R.layout.fragment_page_first, R.layout.fragment_page_second, R.layout.fragment_page_third});


        // 1. 可以监听ViewPager的滚动到了最后一页显示一个跳转按钮，其他页面就隐藏
        // 2. setLayout 的时候可以传 Fragment 数组，最后一个 Fragment 有个按钮对象



        // 模拟后台获取数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashLoadingView.disappear();

            }
        },3000);

    }

    @Override
    public void setListener() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
