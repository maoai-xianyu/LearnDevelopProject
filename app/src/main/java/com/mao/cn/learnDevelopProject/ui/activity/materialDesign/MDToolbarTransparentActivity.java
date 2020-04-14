package com.mao.cn.learnDevelopProject.ui.activity.materialDesign;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.DefineNextScrollView;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class MDToolbarTransparentActivity extends BaseActivity implements DefineNextScrollView.DefineNextScrollChangeListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nes)
    DefineNextScrollView nes;


    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_md_toolbar_transparent;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        /*ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }*/


        nes.setListener(this);


    }

    @Override
    public void setListener() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public void onAlpha(float alpha) {

        LogU.d("  alpha "+alpha);

        toolbar.setAlpha(1- alpha);

    }
}
