// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:36 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerLoadingComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.modules.LoadingModule;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.ILoading;
import com.mao.cn.learnDevelopProject.ui.presenter.LoadingPresenter;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class LoadingActivity extends BaseActivity implements ILoading {


    @Inject
    LoadingPresenter presenter;
    @BindView(R.id.iv_loading_background)
    SimpleDraweeView ivLoadingBackground;
    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    public void getArgs(Bundle bundle) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

    }

    @Override
    public int setView() {
        return R.layout.aty_loading;
    }

    @Override
    public void initView() {

        Logger.e("  SS  "+tvShow);

//        tvShow = (TextView) findViewById(R.id.tv_show);

        Logger.e("  SS  "+tvShow);

        //当应用是使用系统安装器安装并且运行的时候，category中是没有任何信息的，这个时候会导致按home键后，点击图标重启app。
        /*if (!isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }*/

    }
    @Override
    public void setListener() {

        // 个人设置
        RxView.clicks(tvShow).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    startActivity(MainActivity.class, true);
                }, throwable -> {
                    Logger.e(throwable.toString());
                });

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerLoadingComponent.builder()
                .appComponent(appComponent)
                .loadingModule(new LoadingModule(this))
                .build().inject(this);
    }
}
