// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/11/2017 11:53 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.fragment;

import android.os.Bundle;
import android.widget.Button;

import com.hwangjr.rxbus.RxBus;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.di.component.DaggerMainGuideComponent;
import com.mao.cn.learnDevelopProject.di.modules.MainGuideModule;
import com.mao.cn.learnDevelopProject.event.BusAction;
import com.mao.cn.learnDevelopProject.event.RefreshMsgEvent;
import com.mao.cn.learnDevelopProject.ui.activity.AnimatorActivity;
import com.mao.cn.learnDevelopProject.ui.activity.LottieActivity;
import com.mao.cn.learnDevelopProject.ui.activity.NetWorkRequestActivity;
import com.mao.cn.learnDevelopProject.ui.activity.RetrofitShowContentActivity;
import com.mao.cn.learnDevelopProject.ui.activity.RxJavaLearnActivity;
import com.mao.cn.learnDevelopProject.ui.activity.SelfDefineViewActivity;
import com.mao.cn.learnDevelopProject.ui.activity.StringSpannerClickActivity;
import com.mao.cn.learnDevelopProject.ui.activity.aidlTest.AidlDemoActivity;
import com.mao.cn.learnDevelopProject.ui.activity.androidxf.ActivityResultApi;
import com.mao.cn.learnDevelopProject.ui.activity.androidxf.AndroidxDiffUtilActivity;
import com.mao.cn.learnDevelopProject.ui.activity.androidxf.AndroidxFragmentVpActivity;
import com.mao.cn.learnDevelopProject.ui.activity.annotation.AnnotationActivity;
import com.mao.cn.learnDevelopProject.ui.activity.annotation.DefineButterKnifeActivity;
import com.mao.cn.learnDevelopProject.ui.activity.aspectJ.AspectJDemoActivity;
import com.mao.cn.learnDevelopProject.ui.activity.defineview.DefineViewDemoActivity;
import com.mao.cn.learnDevelopProject.ui.activity.designPattern.DesignPatternDemoActivity;
import com.mao.cn.learnDevelopProject.ui.activity.handler.HandlerDemoActivity;
import com.mao.cn.learnDevelopProject.ui.activity.materialDesign.MaterialDesignActivity;
import com.mao.cn.learnDevelopProject.ui.activity.memory.MemoryActivity;
import com.mao.cn.learnDevelopProject.ui.activity.memory.MemoryNewActivity;
import com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo.RetrofitDemoActivity;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.TabHostActivity;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.TabHostMaoyanActivity;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.ui.presenter.MainGuidePresenter;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class MainGuideFragment extends BaseFragment implements IMainGuide {

    @Inject
    MainGuidePresenter presenter;

    @BindView(R.id.btn_desc_net)
    Button btnDescNet;
    @BindView(R.id.btn_desc_rxjava)
    Button btnDescRxjava;
    @BindView(R.id.btn_desc_image)
    Button btnDescImage;
    @BindView(R.id.btn_animator)
    Button btnAnimator;

    @BindView(R.id.btn_define_view)
    Button btnDefineView;
    @BindView(R.id.btn_sp_string)
    Button btnSpString;
    @BindView(R.id.btn_refresh_books)
    Button btnRefreshBooks;
    @BindView(R.id.btn_refresh_games)
    Button btnRefreshGames;
    @BindView(R.id.btn_retrofit)
    Button btnRetrofit;
    @BindView(R.id.btn_m)
    Button btnM;
    @BindView(R.id.btn_memory)
    Button btnMemory;
    @BindView(R.id.btn_life)
    Button btnLife;
    @BindView(R.id.btn_aidl)
    Button btnAidl;
    @BindView(R.id.btn_handler)
    Button btnHandler;
    @BindView(R.id.btn_aspectj)
    Button btnAspectj;
    @BindView(R.id.btn_annotation)
    Button btnAnnotation;
    @BindView(R.id.btn_butterknife)
    Button btnButterknife;
    @BindView(R.id.btn_define)
    Button btnDefineViewMore;
    @BindView(R.id.btnDesignPattern)
    Button btnDesignPattern;
    @BindView(R.id.btnLottie)
    Button btnLottie;
    @BindView(R.id.btnFragment)
    Button btnFragment;
    @BindView(R.id.btnFragmentM)
    Button btnFragmentM;
    @BindView(R.id.btnAndroidX)
    Button btnAndroidX;
    @BindView(R.id.btnAndroidResult)
    Button btnAndroidResult;
    @BindView(R.id.btnAndroidUtils)
    Button btnAndroidUtils;

    public static MainGuideFragment newInstance() {
        MainGuideFragment mainGuideFragment = new MainGuideFragment();
        return mainGuideFragment;
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_main_guide;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainGuideComponent.builder()
                .appComponent(appComponent)
                .mainGuideModule(new MainGuideModule(this))
                .build().inject(this);

    }

    @Override
    public void setListener() {

        RxView.clicks(btnDescNet).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(NetWorkRequestActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnDescRxjava).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(RxJavaLearnActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnDescImage).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(RetrofitShowContentActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnAnimator).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(AnimatorActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });



        RxView.clicks(btnDefineView).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(SelfDefineViewActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnSpString).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(StringSpannerClickActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnRetrofit).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(RetrofitDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnM).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(MaterialDesignActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnMemory).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(MemoryNewActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnLife).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(MemoryActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnRefreshBooks).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            RefreshMsgEvent event = new RefreshMsgEvent();
            event.setNumMsg("10");
            event.setShow(true);
            RxBus.get().post(event);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnRefreshGames).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            RefreshMsgEvent event = new RefreshMsgEvent();
            event.setShow(false);
            RxBus.get().post(event);

            RxBus.get().post(BusAction.BUS_ACTION_CHANGE, "40");
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnAidl).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(AidlDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnHandler).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(HandlerDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnAspectj).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(AspectJDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnAnnotation).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(AnnotationActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(btnButterknife).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DefineButterKnifeActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnDefineViewMore).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DefineViewDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnDesignPattern).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DesignPatternDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnLottie).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(LottieActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnFragment).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(TabHostActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnFragmentM).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(TabHostMaoyanActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnAndroidX).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(AndroidxFragmentVpActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnAndroidResult).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(ActivityResultApi.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(btnAndroidUtils).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(AndroidxDiffUtilActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
    }
}
