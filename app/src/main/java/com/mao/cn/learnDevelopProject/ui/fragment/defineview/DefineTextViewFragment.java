package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

public class DefineTextViewFragment extends BaseFragment {


    public static DefineTextViewFragment newInstance() {
        DefineTextViewFragment fragment = new DefineTextViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_textview;
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
