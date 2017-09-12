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
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class FrameFragment extends BaseFragment implements IMainGuide {


    @BindView(R.id.iv_coin)
    ImageView ivCoin;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.iv_icon_buy)
    ImageView ivIconBuy;
    @BindView(R.id.tv_show)
    TextView tvShow;

    public static FrameFragment getInstance() {
        return new FrameFragment();
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_frame;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {


    }

    @Override
    public void setListener() {

        RxView.clicks(btnPlay).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    tvShow.setText("金币和钱分开开始");
                    play();
                }, throwable -> LogU.e(throwable.getMessage()));

    }


    private void play() {
        if (!checkActivityState()) return;
        AnimatorSet animatorSetStart = new AnimatorSet();
        AnimatorSet animatorSetBack = new AnimatorSet();
        ObjectAnimator animPriceStart = ObjectAnimator.ofFloat(tvPrice, "translationX", 0, 20f);
        ObjectAnimator animCoinStart = ObjectAnimator.ofFloat(ivCoin, "translationX", 0, -20f);

        ObjectAnimator animPriceBack = ObjectAnimator.ofFloat(tvPrice, "translationX", 20f, 0);
        ObjectAnimator animCoinBack = ObjectAnimator.ofFloat(ivCoin, "translationX", -20f, 0);
        ObjectAnimator tvPriceAlphaBack = ObjectAnimator.ofFloat(tvPrice, "alpha", 1, 0);
        ObjectAnimator ivCoinAlphaBack = ObjectAnimator.ofFloat(ivCoin, "alpha", 1, 0);

        animatorSetBack.setDuration(2000);
        animatorSetBack.playTogether(animPriceBack, animCoinBack, tvPriceAlphaBack, ivCoinAlphaBack);

        animatorSetStart.setDuration(2000);
        animatorSetStart.playTogether(animPriceStart, animCoinStart);

        animatorSetStart.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tvShow.setText("金币和钱返回开始");
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
                tvShow.setText("金币和钱返回消失，帧动画金币炸开！");
                startImageAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void startImageAnimation() {
        ivIconBuy.setVisibility(View.VISIBLE);
        AnimationDrawable anim = (AnimationDrawable) ivIconBuy.getBackground();
        anim.start();
        int duration = 0;
        for (int i = 0; i < anim.getNumberOfFrames(); i++) {
            duration += anim.getDuration(i);
        }
        Observable.interval(duration, TimeUnit.MILLISECONDS).take(1).compose(timer())
                .subscribe(aLong -> tvShow.setText("帧动画金币炸开！结束"));
    }
}
