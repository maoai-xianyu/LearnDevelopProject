package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

public class DefineTagLayoutViewFragment extends BaseFragment {

    public static DefineTagLayoutViewFragment newInstance() {
        DefineTagLayoutViewFragment fragment = new DefineTagLayoutViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_tag_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
