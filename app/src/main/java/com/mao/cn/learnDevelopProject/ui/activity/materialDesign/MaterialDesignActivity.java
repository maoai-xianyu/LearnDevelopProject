package com.mao.cn.learnDevelopProject.ui.activity.materialDesign;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.AddHeaderFooterFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.GirlImageFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.ItemDecorationFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.PullFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.drag.AnimatorRYVFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.drag.TextInputLayoutFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class MaterialDesignActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;

    @BindView(R.id.mT1)
    TextView mT1;
    @BindView(R.id.mT2)
    TextView mT2;
    @BindView(R.id.mT3)
    TextView mT3;
    @BindView(R.id.mT4)
    TextView mT4;
    @BindView(R.id.mT5)
    TextView mT5;
    @BindView(R.id.mT6)
    TextView mT6;
    @BindView(R.id.mT7)
    TextView mT7;
    @BindView(R.id.mT8)
    TextView mT8;
    @BindView(R.id.mT9)
    TextView mT9;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_material_design;
    }

    @Override
    public void initView() {


    }

    @Override
    public void setListener() {
        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    finish();
                }, throwable -> LogU.e(throwable.toString()));


        RxView.clicks(mT1).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, PullFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mT2).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.rlContent, ItemDecorationFragment.newInstance())
                            .commitAllowingStateLoss();
                }, throwable -> LogU.e(throwable.toString()));

        RxView.clicks(mT3).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.rlContent, GirlImageFragment.newInstance())
                            .commitAllowingStateLoss();
                }, throwable -> LogU.e(throwable.toString()));

        RxView.clicks(mT4).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.rlContent, AddHeaderFooterFragment.newInstance())
                            .commitAllowingStateLoss();
                }, throwable -> LogU.e(throwable.toString()));



        RxView.clicks(mT8).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.rlContent, AnimatorRYVFragment.newInstance())
                            .commitAllowingStateLoss();
                }, throwable -> LogU.e(throwable.toString()));

        RxView.clicks(mT9).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.rlContent, TextInputLayoutFragment.newInstance())
                            .commitAllowingStateLoss();
                }, throwable -> LogU.e(throwable.toString()));

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
