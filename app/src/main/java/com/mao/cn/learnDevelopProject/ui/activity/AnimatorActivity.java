// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:17 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.ui.adapter.CommonPagerTabAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.ui.fragment.FrameFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.OthersFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.TweenFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.ValueFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class AnimatorActivity extends BaseActivity implements IMain {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_animator)
    ViewPager vpAnimator;


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_animator;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("动画");
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

    //拨打电话
    private void callPhone() {
        //检查拨打电话权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "15171476706"));
            startActivity(intent);
        }
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }


    private void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(FrameFragment.getInstance());
        fragmentList.add(TweenFragment.getInstance());
        fragmentList.add(ValueFragment.getInstance());
        fragmentList.add(OthersFragment.getInstance());

        List<String> titles = new ArrayList<>();
        titles.add("逐帧动画");
        titles.add("Tween动画");
        titles.add("value动画");
        titles.add("others动画");

        CommonPagerTabAdapter tabAdapter = new CommonPagerTabAdapter(getSupportFragmentManager());
        tabAdapter.reloadData(fragmentList, titles);
        vpAnimator.setAdapter(tabAdapter);
        vpAnimator.setOffscreenPageLimit(fragmentList.size());
        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setupWithViewPager(vpAnimator);

    }

}
