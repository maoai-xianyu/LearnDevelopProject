package com.mao.cn.learnDevelopProject.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

import butterknife.BindView;

public class MoviesFoodFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static MoviesFoodFragment newInstance() {
        MoviesFoodFragment fragment = new MoviesFoodFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_movies_food;
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
