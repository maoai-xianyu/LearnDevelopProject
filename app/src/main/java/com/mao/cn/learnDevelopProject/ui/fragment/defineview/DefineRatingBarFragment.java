package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.widgets.RatingBar;

import butterknife.BindView;

public class DefineRatingBarFragment extends BaseFragment {


    @BindView(R.id.ratingBar)
    RatingBar mQQStepView;

    public static DefineRatingBarFragment newInstance() {
        DefineRatingBarFragment fragment = new DefineRatingBarFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_rating_bar;
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
