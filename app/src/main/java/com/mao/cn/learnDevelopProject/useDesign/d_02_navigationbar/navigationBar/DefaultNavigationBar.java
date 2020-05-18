package com.mao.cn.learnDevelopProject.useDesign.d_02_navigationbar.navigationBar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;

/**
 * @author zhangkun
 * @time 2020-05-16 15:48
 * @Description 默认样式的导航栏
 */
public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder> {


    protected DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    @Override
    public void attachNavigationParams() {
        super.attachNavigationParams();

        TextView textLeftView = findViewById(R.id.tvHeaderTitle);
        textLeftView.setVisibility(getBuilder().mLeftVisible);

    }

    /**
     * 导航栏的Builer
     */
    public static class Builder extends AbsNavigationBar.Builder<DefaultNavigationBar.Builder> {

        private int mLeftVisible = View.VISIBLE;


        public Builder(Context context, ViewGroup viewGroup) {
            super(context, R.layout.ui_navigation_toolbar_default, viewGroup);
        }

        @Override
        public DefaultNavigationBar create() {
            return new DefaultNavigationBar(this);
        }


        public Builder setText(String text) {
            setText(R.id.tvHeaderTitle, text);
            return this;
        }


        public Builder setOnClickListener(View.OnClickListener clickListener) {
            setOnClickListener(R.id.tvHeaderTitle, clickListener);
            return this;
        }

        public Builder hideLeftText() {
            mLeftVisible = View.INVISIBLE;
            return this;
        }
    }
}
