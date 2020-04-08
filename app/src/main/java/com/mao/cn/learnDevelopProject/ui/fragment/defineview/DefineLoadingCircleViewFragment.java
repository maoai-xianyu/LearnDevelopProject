package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.widgets.laodCircle.LoadingRunCircleView;

import butterknife.BindView;

public class DefineLoadingCircleViewFragment extends BaseFragment {

    @BindView(R.id.loadingRunCircleView)
    LoadingRunCircleView loadView;


    public static DefineLoadingCircleViewFragment newInstance() {
        DefineLoadingCircleViewFragment fragment = new DefineLoadingCircleViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_loading_circle_view;
    }

    @Override
    public void initView() {

        // 获取数据 之后 不显示 loadView
        loadView.setVisibility(View.GONE);
    }


    @Override
    public void setListener() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
