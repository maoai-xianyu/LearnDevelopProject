package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.LoveLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class DefineClickLoveFragment extends BaseFragment {

    @BindView(R.id.tvClick)
    TextView tvClick;
    @BindView(R.id.love)
    LoveLayout love;


    public static DefineClickLoveFragment newInstance() {
        DefineClickLoveFragment fragment = new DefineClickLoveFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_click_love;
    }

    @Override
    public void initView() {


    }


    @Override
    public void setListener() {
        RxView.clicks(tvClick).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(
                aVoid -> {
                    love.addLove();
                }, throwable -> LogU.e(throwable.getMessage())
        );

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
