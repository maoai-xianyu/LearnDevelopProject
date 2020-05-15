package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.LoadingView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class DefineLoadingViewFragment extends BaseFragment {

    @BindView(R.id.loadView)
    LoadingView loadView;

    @BindView(R.id.tv1)
    TextView tv1;


    @BindView(R.id.tv2)
    TextView tv2;


    public static DefineLoadingViewFragment newInstance() {
        DefineLoadingViewFragment fragment = new DefineLoadingViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_loading_view;
    }

    @Override
    public void initView() {
    }


    @Override
    public void setListener() {

        RxView.clicks(tv1).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            loadView.startAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(tv2).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            loadView.stopAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
