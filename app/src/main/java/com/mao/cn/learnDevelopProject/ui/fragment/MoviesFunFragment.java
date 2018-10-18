package com.mao.cn.learnDevelopProject.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

import butterknife.BindView;

public class MoviesFunFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static MoviesFunFragment newInstance() {
        MoviesFunFragment fragment = new MoviesFunFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_movies_fun;
    }

    @Override
    public void initView() {

    }

    public void setText(String text){
        tvTitle.setText(text);
    }

    @Override
    public void setListener() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
