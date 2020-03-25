package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineCircleProgressFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineColorTrackTextViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineQQStepViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineRatingBarFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineTextViewFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class DefineViewDemoActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;


    @BindView(R.id.mDT1)
    TextView mDT1;
    @BindView(R.id.mDT2)
    TextView mDT2;
    @BindView(R.id.mDT3)
    TextView mDT3;
    @BindView(R.id.mDT4)
    TextView mDT4;
    @BindView(R.id.mDT5)
    TextView mDT5;
    @BindView(R.id.mDT6)
    TextView mDT6;
    @BindView(R.id.mDT7)
    TextView mDT7;


    @BindView(R.id.mDT30)
    TextView mDT30;
    @BindView(R.id.mDT31)
    TextView mDT31;
    @BindView(R.id.mDT32)
    TextView mDT32;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_view_demo;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setText("自定义view");


    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));

        RxView.clicks(mDT1).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineTextViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT2).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineQQStepViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT3).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineColorTrackTextViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT4).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineCircleProgressFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT5).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineRatingBarFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
