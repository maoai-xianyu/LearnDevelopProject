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

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.wedget.interploator.SpringScaleInterpolator;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class OthersFragment extends BaseFragment implements IMainGuide {


    @BindView(R.id.iv_coin)
    ImageView ivCoin;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.tv_inter)
    TextView tvInter;
    @BindView(R.id.tv_rebound)
    TextView tvRebound;
    @BindView(R.id.tv_spring)
    TextView tvSpring;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.menu)
    Button btnMenu;
    @BindView(R.id.item1)
    Button btnItem1;
    @BindView(R.id.item2)
    Button btnItem2;
    @BindView(R.id.item3)
    Button btnItem3;
    @BindView(R.id.item4)
    Button btnItem4;
    @BindView(R.id.item5)
    Button btnItem5;

    private boolean isMenuOpen;

    public static OthersFragment getInstance() {
        return new OthersFragment();
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_others;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {


    }

    @Override
    public void setListener() {

        RxView.clicks(btnPlay).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            play();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(tvInter).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            playInter();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvRebound).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            playRebound();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvSpring).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            playSpring();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnMenu).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            playStartMenu();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    private void playStartMenu() {
        if (isMenuOpen) {
            doAnimateClose(btnItem1, 0, 5, 300);
            doAnimateClose(btnItem2, 1, 5, 300);
            doAnimateClose(btnItem3, 2, 5, 300);
            doAnimateClose(btnItem4, 3, 5, 300);
            doAnimateClose(btnItem5, 4, 5, 300);
            isMenuOpen = false;
        } else {
            doAnimateOpen(btnItem1, 0, 5, 300);
            doAnimateOpen(btnItem2, 1, 5, 300);
            doAnimateOpen(btnItem3, 2, 5, 300);
            doAnimateOpen(btnItem4, 3, 5, 300);
            doAnimateOpen(btnItem5, 4, 5, 300);
            isMenuOpen = true;
        }
    }

    /**
     * 打开菜单的动画
     * @param view 执行动画的view
     * @param index view在动画序列中的顺序,从0开始
     * @param total 动画序列的个数
     * @param radius 动画半径
     *
     *  Math.sin(x):x -- 为number类型的弧度，角度乘以0.017(2π/360)可以转变为弧度
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        //动画周期为500ms
        set.setDuration(500).start();

    }

    /**
     * 关闭菜单的动画
     * @param view 执行动画的view
     * @param index view在动画序列中的顺序
     * @param total 动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateClose(View view, int index, int total, int radius) {

        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));

        set.setDuration(500).start();


    }

    private void playInter() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(iv, "scaleX", 1.0f, 1.8f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv, "scaleY", 1.0f, 1.8f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new SpringScaleInterpolator(0.4f));
        set.playTogether(animatorX, animatorY);
        set.start();
    }

    private void playRebound() {
        SpringSystem springSystem = SpringSystem.create();
        Spring spring = springSystem.createSpring();
        spring.setCurrentValue(1.0f);
        //1.tension(拉力)、2.friction(摩擦力)。摩擦力越大，弹性效果越小。默认的tension值
        spring.setSpringConfig(new SpringConfig(50, 5));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                float currentValue = (float) spring.getCurrentValue();
                iv.setScaleX(currentValue);
                iv.setScaleY(currentValue);
            }
        });
        spring.setEndValue(1.8f);
    }

    /**
     * dampingRatio越大，摆动次数越少，当到1的时候完全不摆动,注意它体验出来的最明显的特征是摆动次数这个概念
     * <p>
     * stiffness值越小，弹簧越容易摆动，摆动的时间越长，反之摆动时间越短，注意它体验出来的最明显的特征是摆动时间这个概念
     */
    private void playSpring() {

        SpringAnimation animationX = new SpringAnimation(iv, DynamicAnimation.SCALE_X, 1.8f);
        SpringAnimation animationY = new SpringAnimation(iv, DynamicAnimation.SCALE_Y, 1.8f);
        SpringForce springX = animationX.getSpring();
        springX.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springX.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        animationX.setStartValue(1.0f);

        SpringForce springY = animationY.getSpring();
        springY.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springY.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        animationY.setStartValue(1.0f);

        animationX.start();
        animationY.start();
    }

    private void play() {

        AnimatorSet animatorSetStart = new AnimatorSet();
        AnimatorSet animatorSetBack = new AnimatorSet();

        ObjectAnimator animPriceStart = ObjectAnimator.ofFloat(tvPrice, "translationX", 0, 20f);
        ObjectAnimator animCoinStart = ObjectAnimator.ofFloat(ivCoin, "translationX", 0, -20f);

        ObjectAnimator animPriceBack = ObjectAnimator.ofFloat(tvPrice, "translationX", 20f, 0);
        ObjectAnimator animCoinBack = ObjectAnimator.ofFloat(ivCoin, "translationX", -20f, 0);

        ObjectAnimator tvPriceAlphaBack = ObjectAnimator.ofFloat(tvPrice, "alpha", 1, 0);
        ObjectAnimator ivCoinAlphaBack = ObjectAnimator.ofFloat(ivCoin, "alpha", 1, 0);

        animatorSetBack.setDuration(5000);
        animatorSetBack.playTogether(animPriceBack, animCoinBack, tvPriceAlphaBack, ivCoinAlphaBack);

        animatorSetStart.setDuration(5000);
        animatorSetStart.playTogether(animPriceStart, animCoinStart);
        animatorSetStart.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSetBack.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSetStart.start();


        animatorSetBack.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                LogU.i("animatorSetBack  结束");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

}
