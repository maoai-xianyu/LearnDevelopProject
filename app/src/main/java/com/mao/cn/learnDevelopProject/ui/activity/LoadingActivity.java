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
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerLoadingComponent;
import com.mao.cn.learnDevelopProject.modules.LoadingModule;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.ILoading;
import com.mao.cn.learnDevelopProject.ui.presenter.LoadingPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

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
    private Subscription subscribe;

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

        subscribe = Observable.interval(5, TimeUnit.SECONDS).compose(timer()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {

                LogU.i("  aLong  " + aLong);
                startActivity(MainActivity.class, true);
            }
        });


        // timer  定时
        /*Observable.timer(5, TimeUnit.SECONDS).compose(timer()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                LogU.i("  aLong  " + aLong);
                startActivity(MainActivity.class, true);
            }
        });*/


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

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerLoadingComponent.builder()
                .appComponent(appComponent)
                .loadingModule(new LoadingModule(this))
                .build().inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null && !subscribe.isUnsubscribed()) {
            subscribe.unsubscribe();
        }

    }
}
