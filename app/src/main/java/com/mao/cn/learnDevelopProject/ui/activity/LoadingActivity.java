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

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.BarHide;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.di.component.DaggerLoadingComponent;
import com.mao.cn.learnDevelopProject.di.modules.LoadingModule;
import com.mao.cn.learnDevelopProject.ui.commons.UIActivity;
import com.mao.cn.learnDevelopProject.ui.features.ILoading;
import com.mao.cn.learnDevelopProject.ui.presenter.LoadingPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class LoadingActivity extends UIActivity implements ILoading {


    @Inject
    LoadingPresenter presenter;
    @BindView(R.id.iv_loading_background)
    ImageView ivLoadingBackground;
    @BindView(R.id.tv_show)
    TextView tvShow;
    private CompositeSubscription compositeSubscription;

    @Override
    public void getArgs(Bundle bundle) {


    }

    @Override
    public int setView() {
        return R.layout.aty_loading;
    }

    @Override
    public void initView() {
        Glide.with(this).load(R.drawable.bg_loading).into(ivLoadingBackground);
        compositeSubscription = new CompositeSubscription();
        Subscription subscribe = Observable.interval(2, TimeUnit.SECONDS).compose(timer()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {

                LogU.i("  aLong  " + aLong);
                startActivity(MainActivity.class, true);
            }
        });

        compositeSubscription.add(subscribe);

        getStatusBarConfig()
                .fullScreen(true)//有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)//隐藏状态栏
                .transparentNavigationBar()//透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .init();
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
        /*if (subscribe != null && !subscribe.isUnsubscribed()) {
            subscribe.unsubscribe();
        }*/
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
            compositeSubscription.clear();
        }
    }
}
