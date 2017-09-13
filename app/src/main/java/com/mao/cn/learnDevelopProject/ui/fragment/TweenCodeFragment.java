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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class TweenCodeFragment extends BaseFragment implements IMainGuide {


    @BindView(R.id.tv_alpha)
    TextView tvAlpha;
    @BindView(R.id.tv_rotate)
    TextView tvRotate;
    @BindView(R.id.tv_rotate_arrow)
    TextView tvRotateArrow;
    @BindView(R.id.tv_scale)
    TextView tvScale;
    @BindView(R.id.tv_trans)
    TextView tvTrans;
    @BindView(R.id.tv_animator_set)
    TextView tvAnimatorSet;
    @BindView(R.id.tv_interpolator)
    TextView tvInterpolator;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    private boolean rotate;

    public static TweenCodeFragment getInstance() {
        return new TweenCodeFragment();
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_tween_code;
    }

    @Override
    public void initView() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {


    }

    @Override
    public void setListener() {

        RxView.clicks(tvScale).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            scaleAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvAlpha).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            alphaAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvRotate).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            rotateAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvRotateArrow).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            rotateAnimationBack();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(tvTrans).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            transAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvAnimatorSet).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            setAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvInterpolator).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            interAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


    }


    /**
     * ScaleAnimation(Context context, AttributeSet attrs)  从XML文件加载动画，基本用不到
     * ScaleAnimation(float fromX, float toX, float fromY, float toY)
     * ScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY)
     * ScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
     * 在标签属性android:pivotX中有三种取值，数，百分数，百分数p；
     * 体现在构造函数中，就是最后一个构造函数的pivotXType,
     * 它的取值有三个，Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT；
     */
    private void scaleAnimation() {
        ScaleAnimation animation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new BounceInterpolator());
        animation.setDuration(2000);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(1);
        animation.setFillAfter(true);
        iv.startAnimation(animation);
    }

    /**
     * AlphaAnimation(Context context, AttributeSet attrs)  同样，从本地XML加载动画，基本不用
     * AlphaAnimation(float fromAlpha, float toAlpha)
     */
    private void alphaAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.1f);
        animation.setInterpolator(new CycleInterpolator(2f));
        animation.setDuration(2000);
        animation.setFillAfter(true);
        iv.startAnimation(animation);
    }

    /**
     * RotateAnimation(Context context, AttributeSet attrs)　　从本地XML文档加载动画，同样，基本不用
     * RotateAnimation(float fromDegrees, float toDegrees)
     * RotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY)
     * RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
     * 关键问题同样是pivotXType和pivotYType的选择，
     * 同样有三个取值：Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT；
     */
    private void rotateAnimation() {

        RotateAnimation animation = new RotateAnimation(0f, 180f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        iv.startAnimation(animation);
    }

    private void rotateAnimationBack() {
        if (rotate) {
            rotate = false;
            RotateAnimation animation = new RotateAnimation(180f, 360f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(2000);
            animation.setFillAfter(true);
            ivArrow.startAnimation(animation);
        } else {
            rotate = true;
            RotateAnimation animation = new RotateAnimation(0f, 180f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(2000);
            animation.setFillAfter(true);
            ivArrow.startAnimation(animation);
        }
    }

    /**
     * TranslateAnimation(Context context, AttributeSet attrs)  同样，基本不用
     * TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
     * TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)
     * 由于fromXDelta、fromYDelta、toXDelta、toYDelta这三个属性都具有三种状态，
     * 所以在构造函数中，最理想的状态就是第三个构造函数，能够指定每个值的类型，
     * 第二个构造函数：TranslateAnimation (float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)使用是绝对数值。
     * 只有最后一个构造函数可以指定百分数和相对父控件的百分数。
     */
    private void transAnimation() {
        TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 80, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 80);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        iv.startAnimation(animation);
    }

    private void setAnimation() {
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.1f);
        ScaleAnimation scaleAnim = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotateAnim = new RotateAnimation(0, 720,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet setAnim = new AnimationSet(true);
        setAnim.addAnimation(alphaAnim);
        setAnim.addAnimation(scaleAnim);
        setAnim.addAnimation(rotateAnim);
        setAnim.setDuration(3000);
        setAnim.setFillAfter(true);
        iv.startAnimation(setAnim);
    }

    private void interAnimation() {
        RotateAnimation interpolateScaleAnim = new RotateAnimation(0,360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        interpolateScaleAnim.setInterpolator(new BounceInterpolator());
        interpolateScaleAnim.setDuration(3000);
        iv.startAnimation(interpolateScaleAnim);
    }
}
