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

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerLoadingComponent;
import com.mao.cn.learnDevelopProject.contants.KeyMaps;
import com.mao.cn.learnDevelopProject.modules.LoadingModule;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.ILoading;
import com.mao.cn.learnDevelopProject.ui.presenter.LoadingPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.FileU;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.PathU;
import com.mao.cn.learnDevelopProject.utils.tools.PreferenceU;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

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
public class LoadingActivity extends BaseActivity implements ILoading {


    @Inject
    LoadingPresenter presenter;
    @BindView(R.id.iv_loading_background)
    ImageView ivLoadingBackground;
    @BindView(R.id.tv_show)
    TextView tvShow;
    private Subscription subscribe;
    private CompositeSubscription compositeSubscription;

    @Override
    public void getArgs(Bundle bundle) {


    }

    @Override
    public int setView() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawable(null);
        //initStatus();
        return R.layout.aty_loading;
    }

    @Override
    public void initView() {
        compositeSubscription = new CompositeSubscription();
        subscribe = Observable.interval(2, TimeUnit.SECONDS).compose(timer()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {

                LogU.i("  aLong  " + aLong);
                startActivity(MainActivity.class, true);
            }
        });

        compositeSubscription.add(subscribe);

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

        Glide.with(this).load(R.drawable.bg_loading).animate(animator).into(ivLoadingBackground);
        startAnimat();

        initApp();
    }

    private void initStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decoderView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decoderView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    ViewPropertyAnimation.Animator animator = new ViewPropertyAnimation.Animator() {

        @Override
        public void animate(View view) {
            view.setAlpha(0f);
            ObjectAnimator objAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 1f);
            objAnimator.setDuration(2000);
            objAnimator.start();
        }
    };

    private void startAnimat() {

        int imgHeight = ivLoadingBackground.getHeight() / 5;
        int height = getWindowManager().getDefaultDisplay().getHeight();
        int curY = height / 2 + imgHeight / 2;
        int dy = (height - imgHeight) / 2;
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animatorTranslate = ObjectAnimator.ofFloat(ivLoadingBackground, "translationY", 0, dy);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(ivLoadingBackground, "ScaleX", 1f, 0.2f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(ivLoadingBackground, "ScaleY", 1f, 0.2f);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(ivLoadingBackground, "alpha", 1f, 0.5f);
        set.play(animatorTranslate)
                .with(animatorScaleX).with(animatorScaleY).with(animatorAlpha);
        set.setDuration(1200);
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

               /* ivLoadingBackground.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                        LoadingActivity.this.finish();
                    }
                }, 3000);*/
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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

    public void initApp() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        PreferenceU.getInstance(this).saveInt(KeyMaps.Screen.SCREEN_WIDTH, dm.widthPixels);
        PreferenceU.getInstance(this).saveInt(KeyMaps.Screen.SCREEN_HEIGHT, dm.heightPixels);
        FileU.ifNotExistCreateDir(PathU.getInstance().getAssetsFile() + "/youdao/localdict");
        /*String pathFile = PathU.getInstance().getAssetsFile() + "/youdao/localdict/localdict.datx";
        FileU.createFile(new File(pathFile));*/
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
