package com.mao.cn.learnDevelopProject.useDesign.d_02_navigationbar.navigationBar;

import android.content.Context;
import android.view.ViewGroup;

/**
 * @author zhangkun
 * @time 2020-05-16 15:48
 * @Description 直接使用的导航栏
 */
public class NavigationBar extends AbsNavigationBar<NavigationBar.Builder>{

    protected NavigationBar(Builder builder) {
        super(builder);
    }

    /**
     * 导航栏的Builer
     */
    public static class Builder extends AbsNavigationBar.Builder<NavigationBar.Builder>{

        public Builder(Context context, int layoutId, ViewGroup viewGroup) {
            super(context, layoutId, viewGroup);
        }

        @Override
        public NavigationBar create() {
            return new NavigationBar(this);
        }
    }
}
