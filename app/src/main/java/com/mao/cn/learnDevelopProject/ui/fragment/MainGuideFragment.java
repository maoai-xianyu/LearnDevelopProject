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
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerMainGuideComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.event.BusAction;
import com.mao.cn.learnDevelopProject.event.RefreshMsgEvent;
import com.mao.cn.learnDevelopProject.modules.MainGuideModule;
import com.mao.cn.learnDevelopProject.ui.activity.AnimatorActivity;
import com.mao.cn.learnDevelopProject.ui.activity.DefineViewActivity;
import com.mao.cn.learnDevelopProject.ui.activity.NetWorkRequestActivity;
import com.mao.cn.learnDevelopProject.ui.activity.RetrofitShowContentActivity;
import com.mao.cn.learnDevelopProject.ui.activity.RxJavaLearnActivity;
import com.mao.cn.learnDevelopProject.ui.activity.StringSpannerClickActivity;
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
            startActivity(DefineViewActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnSpString).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(StringSpannerClickActivity.class);
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
    }
}
