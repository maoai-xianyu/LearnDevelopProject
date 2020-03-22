// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/28/2017 11:40 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.di.component.DaggerDefineViewComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.modules.DefineViewModule;
import com.mao.cn.learnDevelopProject.ui.adapter.CommonPagerTabAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IDefineView;
import com.mao.cn.learnDevelopProject.ui.fragment.DefineView1Fragment;
import com.mao.cn.learnDevelopProject.ui.fragment.DefineView2Fragment;
import com.mao.cn.learnDevelopProject.ui.fragment.DefineView3Fragment;
import com.mao.cn.learnDevelopProject.ui.presenter.DefineViewPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DefineViewActivity extends BaseActivity implements IDefineView {

    @Inject
    DefineViewPresenter presenter;

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_define_view)
    ViewPager vpDefineView;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_view;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("自定义View篇章");
        tvHeaderTitle.setVisibility(View.VISIBLE);
        initData();
    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerDefineViewComponent.builder().appComponent(appComponent).defineViewModule(new DefineViewModule(this)).build().inject(this);
    }

    private void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(DefineView1Fragment.getInstance());
        fragmentList.add(DefineView2Fragment.getInstance());
        fragmentList.add(DefineView3Fragment.getInstance());

        List<String> titles = new ArrayList<>();
        titles.add("View1");
        titles.add("View2Canvas");
        titles.add("View3");

        CommonPagerTabAdapter tabAdapter = new CommonPagerTabAdapter(getSupportFragmentManager());
        tabAdapter.reloadData(fragmentList, titles);
        vpDefineView.setAdapter(tabAdapter);
        vpDefineView.setOffscreenPageLimit(fragmentList.size());
        tab.setupWithViewPager(vpDefineView);

    }
}
