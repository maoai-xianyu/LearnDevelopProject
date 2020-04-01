package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.StatusBarUtil;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class MDStatusBarActivity extends BaseActivity {


    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_status_bar;
    }

    @Override
    public void initView() {
        // 设置状态栏
        //StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this,R.color.colorPrimaryDarkNew));
        // 设置全屏
        StatusBarUtil.setActivityTranslucent(this);

        //QQ 效果:1. 不断监听ScrollView的滚动，判断当前滚动的位置和头部的ImageView比较计算背景的透明度
        // 2. 自定义Behavior

    }

    @Override
    public void setListener() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
