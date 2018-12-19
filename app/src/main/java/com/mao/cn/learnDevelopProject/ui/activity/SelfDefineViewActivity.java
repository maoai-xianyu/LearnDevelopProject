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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.di.component.DaggerDefineViewComponent;
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
public class SelfDefineViewActivity extends BaseActivity {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_define_old)
    TextView tvDefineOld;
    @BindView(R.id.tv_define_youku)
    TextView tvDefineYouKu;
    @BindView(R.id.tv_define_guang_gao)
    TextView tvDefineGuangGao;
    @BindView(R.id.tv_define_down_list)
    TextView tvDefineDownList;
    @BindView(R.id.tv_define_on_off)
    TextView tvDefineOnOff;
    @BindView(R.id.tv_define_property)
    TextView tvDefineProperty;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_self_define_view;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("自定义View篇章");
        tvHeaderTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvDefineOld).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DefineViewActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(tvDefineYouKu).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DefineYoukuViewActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvDefineGuangGao).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DefineGuangGaoViewActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvDefineDownList).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DefineDownListViewActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvDefineOnOff).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DefineViewOnOrOffActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvDefineProperty).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DefineViewPropertyActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
    }
}
