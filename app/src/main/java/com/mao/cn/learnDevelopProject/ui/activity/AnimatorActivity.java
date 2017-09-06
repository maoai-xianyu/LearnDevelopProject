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

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class AnimatorActivity extends BaseActivity implements IMain {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_coin)
    ImageView ivCoin;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.btn_play)
    Button btnPlay;

    private String[] perms = {Manifest.permission.CALL_PHONE};
    private final int PERMS_REQUEST_CODE = 200;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_animator;
    }

    @Override
    public void initView() {
        tvHeaderTitle.setText("动画");
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

        RxView.clicks(btnPlay).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            //play();
            callPhone();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


    }

    //拨打电话
    private void callPhone() {
        //检查拨打电话权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "15171476706"));
            startActivity(intent);
        }
    }


    @Override
    protected void setupComponent(AppComponent appComponent) {

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
