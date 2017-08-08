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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerMainComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.model.MovieDetail;
import com.mao.cn.learnDevelopProject.modules.MainModule;
import com.mao.cn.learnDevelopProject.ui.adapter.MovieTopAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.ui.presenter.MainPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class MainActivity extends BaseActivity implements IMain {

    @Inject
    MainPresenter presenter;

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.btn_desc_okhttp)
    Button btnDescOkhttp;
    @BindView(R.id.btn_desc_rxjava)
    Button btnDescRxjava;
    @BindView(R.id.rvData)
    RecyclerView rvData;


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_main;
    }

    @Override
    public void initView() {

        presenter.getMovieTop(0, 10);

    }

    @Override
    public void setListener() {

        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            Logger.e(throwable.getMessage());
        });


        RxView.clicks(btnDescOkhttp).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(OkhttpShowContentActivity.class);
        }, throwable -> {
            Logger.e(throwable.getMessage());
        });


        RxView.clicks(btnDescRxjava).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(RxjavaShowContentActivity.class);

        }, throwable -> {
            Logger.e(throwable.getMessage());
        });
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Override
    public void showTopMovie(List<MovieDetail> movieDetails, String platformTitle) {
        if (!checkActivityState()) return;
        tvHeaderTitle.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(platformTitle);
        if (ListU.notEmpty(movieDetails)) {
            LinearLayoutManager linearLayoutCourse = new LinearLayoutManager(context);
            linearLayoutCourse.setOrientation(LinearLayoutManager.VERTICAL);
            rvData.setLayoutManager(linearLayoutCourse);

            MovieTopAdapter movieTopAdapter = new MovieTopAdapter(this);
            movieTopAdapter.addMovieList(movieDetails);
            rvData.setAdapter(movieTopAdapter);
        }

    }
}
