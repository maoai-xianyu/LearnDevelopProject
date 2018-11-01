package com.mao.cn.learnDevelopProject.ui.fragment;

import android.os.Bundle;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.ui.activity.FancyCoverFlowActivity;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class OthersHomeFragment extends BaseFragment {

    @BindView(R.id.btn_login)
    Button btnLogin;

    public static OthersHomeFragment newInstance() {
        OthersHomeFragment fragment = new OthersHomeFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_others_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {

        RxView.clicks(btnLogin).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(FancyCoverFlowActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }


}
