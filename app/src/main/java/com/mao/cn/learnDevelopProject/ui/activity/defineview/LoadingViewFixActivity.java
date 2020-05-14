package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.LoadingFixView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class LoadingViewFixActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton ibBack;
    @BindView(R.id.loadViewFix)
    LoadingFixView loadViewFix;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_loading_view_fix;
    }

    @Override
    public void initView() {
        ibBack.setVisibility(View.VISIBLE);


    }

    @Override
    public void setListener() {

        RxView.clicks(ibBack).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                finish();

            }
        }, throwable -> LogU.e(throwable.getMessage()));


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadViewFix.destoryAnimation();
    }
}
