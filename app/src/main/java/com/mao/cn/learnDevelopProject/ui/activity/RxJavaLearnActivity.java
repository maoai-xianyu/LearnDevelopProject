// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 18:35 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerRxJavaLearnComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.modules.RxJavaLearnModule;
import com.mao.cn.learnDevelopProject.ui.adapter.RxJavaLearnAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IRxJavaLearn;
import com.mao.cn.learnDevelopProject.ui.presenter.RxJavaLearnPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.ResourceU;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class RxJavaLearnActivity extends BaseActivity implements IRxJavaLearn {

    @Inject
    RxJavaLearnPresenter presenter;

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.rvData)
    RecyclerView rvData;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.sv_image)
    SimpleDraweeView svImage;

    private RxJavaLearnAdapter adapter;
    private List<String> strings;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_rx_java_learn;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(getString(R.string.header_rxjava));

        strings = new ArrayList<>();

        strings.add("rxjava_start");
        /*strings.add("rxjava_Observer");
        strings.add("rxjava_Observer");*/

        LinearLayoutManager linearLayoutCourse = new LinearLayoutManager(context);
        linearLayoutCourse.setOrientation(LinearLayoutManager.VERTICAL);
        rvData.setLayoutManager(linearLayoutCourse);
        adapter = new RxJavaLearnAdapter(this);
        adapter.addStringList(strings);
        rvData.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        adapter.addListener(str -> {
            switch (str) {
                case "rxjava_start":
                    rxjava_startFun();
                    break;
                default:
                    break;
            }
        });
    }

    private void rxjava_startFun() {

        String[] list = ResourceU.getAssetsFileNames("images_cover");

        if (list != null) {
            for (String aList : list) {
                LogU.i("测试名：  " + aList);
            }
        }

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerRxJavaLearnComponent.builder()
                .appComponent(appComponent)
                .rxJavaLearnModule(new RxJavaLearnModule(this))
                .build().inject(this);
    }
}
