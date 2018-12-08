// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/28/2017 11:40 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.ViewTools;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DefineYoukuViewActivity extends BaseActivity {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;

    @BindView(R.id.tvBujian)
    TextView tvBujian;
    @BindView(R.id.tvShuXing)
    TextView tvShuXing;
    @BindView(R.id.tvReset)
    TextView tvReset;
    @BindView(R.id.ivImage)
    ImageView ivImage;

    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.iv_memu)
    ImageView ivMemu;

    @BindView(R.id.rl_first)
    RelativeLayout rlFirst;
    @BindView(R.id.rl_second)
    RelativeLayout rlSecond;
    @BindView(R.id.rl_three)
    RelativeLayout rlThree;


    /**
     * 第三个圆环
     */
    private boolean isShowRlThree = true;

    /**
     * 第二个圆环
     */
    private boolean isShowRlSecond = true;

    /**
     * 第一个圆环
     */
    private boolean isShowRlFirst = true;


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_youku_view;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("优酷");
        tvHeaderTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvBujian).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            tweenAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvShuXing).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            proPertyAnimaton();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvReset).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            ivImage.clearAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(ivImage).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            onTip("点击图片");
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });



        RxView.clicks(ivHome).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            // 如果三级菜单和二级菜单都是显示，就因此
            if (isShowRlSecond) {
                //隐藏二
                isShowRlSecond = false;
                ViewTools.hideViewGroupProperty(rlSecond);
                if (isShowRlThree) {
                    isShowRlThree = false;

                    ViewTools.hideViewGroupProperty(rlThree, 200);
                }
            } else {
                isShowRlSecond = true;
                ViewTools.showViewGroupProperty(rlSecond);
            }
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(ivMemu).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            if (isShowRlThree) {
                isShowRlThree = false;
                ViewTools.hideViewGroupProperty(rlThree);
            } else {
                isShowRlThree = true;
                ViewTools.showViewGroupProperty(rlThree);
            }
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(rlFirst).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            onTip("点击rlFirst");
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(rlSecond).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            onTip("点击rlSecond");
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(rlThree).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            onTip("点击rlThree");
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
    }

    private void tweenAnimation() {
        TranslateAnimation animation = new TranslateAnimation(0, ivImage.getWidth(), 0, ivImage.getHeight());
        animation.setDuration(2000);
        animation.setFillAfter(true);
        ivImage.startAnimation(animation);
    }

    private void proPertyAnimaton() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivImage, "translationX", 0, ivImage.getWidth());
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivImage, "translationY", 0, ivImage.getHeight());
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setDuration(2000);
        animationSet.play(animatorX).with(animatorY);
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.start();
    }

    private void proPertyAnimatonOther() {
        ivImage.animate()
                .translationXBy(ivImage.getWidth())
                .translationYBy(ivImage.getHeight())
                .setDuration(2000)
                .setInterpolator(new BounceInterpolator())
                .start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {

            // 如果 一  二 三 是显示的就全部隐藏
            if (isShowRlFirst) {
                isShowRlFirst = false;
                ViewTools.hideViewGroupProperty(rlFirst);
                if (isShowRlSecond) {
                    isShowRlSecond = false;
                    ViewTools.hideViewGroupProperty(rlSecond, 200);
                    if (isShowRlThree) {
                        isShowRlThree = false;
                        ViewTools.hideViewGroupProperty(rlThree, 400);
                    }
                }
            } else {
                isShowRlFirst = true;
                ViewTools.showViewGroupProperty(rlFirst);
                isShowRlSecond = true;
                ViewTools.showViewGroupProperty(rlSecond, 200);
            }
            // 如果 一 二 菜单隐藏 就是显示
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
