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

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.wedget.fancyCoverFlow.FancyCoverFlow;
import com.mao.cn.learnDevelopProject.wedget.fancyCoverFlow.ImageAndTextAdapter;
import com.mao.cn.learnDevelopProject.wedget.fancyCoverFlow.PicAndTextInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class FancyCoverFlowActivity extends BaseActivity implements IMain {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.fcf)
    FancyCoverFlow fcf;
    private ImageAndTextAdapter adapter;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_fancy_cover_flow;
    }

    @Override
    public void initView() {
        tvHeaderTitle.setText("3D");
        tvHeaderTitle.setVisibility(View.VISIBLE);
        setUp();
    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        fcf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.setCurrentPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void setUp(){
        List<PicAndTextInfo> mPicAndTextInfos = new ArrayList<>();
        mPicAndTextInfos.add(new PicAndTextInfo(R.drawable.icon_login_menu_email, "账号登录"));
        mPicAndTextInfos.add(new PicAndTextInfo(R.drawable.icon_login_menu_wechat, "微信登录"));
        mPicAndTextInfos.add(new PicAndTextInfo(R.drawable.icon_login_menu_qq, "QQ登录"));

        adapter = new ImageAndTextAdapter(context, mPicAndTextInfos);

        fcf.setAdapter(adapter);
        fcf.setSelection(1);
        fcf.setUnselectedAlpha(1.0f);
        fcf.setUnselectedSaturation(0.0f);
        fcf.setUnselectedScale(1.0f);
        fcf.setSpacing(20);
        fcf.setMaxRotation(0);
        fcf.setScaleDownGravity(0.5f);
        fcf.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
    }


    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
