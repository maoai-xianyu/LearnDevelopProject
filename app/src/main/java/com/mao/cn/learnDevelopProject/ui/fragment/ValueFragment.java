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
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.wedget.interploator.DefineEvalutor;
import com.mao.cn.learnDevelopProject.wedget.interploator.DefineSelfInterpolator;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class ValueFragment extends BaseFragment implements IMainGuide {


    @BindView(R.id.tv_play_int)
    TextView tvPlayInt;
    @BindView(R.id.tv_play_of)
    TextView tvPlayOf;
    @BindView(R.id.tv_play)
    TextView tvPlay;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_inter)
    TextView tvInter;
    @BindView(R.id.tv_evalutor)
    TextView tvEvalutor;
    @BindView(R.id.tv_argb)
    TextView tvArgb;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_show)
    TextView tvShow;

    private ValueAnimator animator;

    public static ValueFragment getInstance() {
        return new ValueFragment();
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_value;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {


    }

    @Override
    public void setListener() {
        RxView.clicks(tvPlayInt).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            ofIntAnimator();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvPlayOf).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            ofFloatAnimator();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvPlay).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = ofIntAnimatorPlay();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvCancel).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator.cancel();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(iv).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            onTip("点击我了！");
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvInter).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = interAnimatorPlay();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvEvalutor).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = evaluatorAnimatorPlay();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvArgb).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = argevaluatorAnimatorPlay();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
    }

    /**
     * ValueAnimator对指定值区间做动画运算，
     * 我们通过对运算过程做监听来自己操作控件。
     */
    private void ofIntAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 400);
        animator.setDuration(2000);
        animator.addUpdateListener(animation -> {
            int curValue = (int) animation.getAnimatedValue();
            LogU.d("curValue:  " + curValue);
            iv.layout(curValue, curValue, curValue + iv.getWidth(), curValue + iv.getHeight());
        });
        animator.start();
    }

    /**
     * 常用方法
     * 他们的参数类型都是可变参数长参数，所以我们可以传入任何数量的值；传进去的值列表，就表示动画时的变化范围；
     * ofInt与ofFloat
     */
    private void ofFloatAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 400f, 50f, 300f);
        animator.setDuration(2000);
        animator.addUpdateListener(animation -> {
            Float curFloat = (Float) animation.getAnimatedValue();
            LogU.d("curValue:  " + curFloat);
            int curValue = curFloat.intValue();
            iv.layout(curValue, curValue, curValue + iv.getWidth(), curValue + iv.getHeight());
        });
        animator.start();
    }

    /**
     * 总而言之，通过getAnimatedValue()值类型与初始设置动画时的值类型相同
     * setRepeatCount(int value)用于设置动画循环次数,设置为0表示不循环，
     * 设置为ValueAnimation.INFINITE表示无限循环。
     * cancel()用于取消动画
     * setRepeatMode(int value)用于设置循环模式，取值为ValueAnimation.RESTART时,表示正序重新开始，
     * 当取值为ValueAnimation.REVERSE表示倒序重新开始。
     */
    private ValueAnimator ofIntAnimatorPlay() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 400);
        animator.addUpdateListener(animation -> {
            int curValue = (int) animation.getAnimatedValue();
            iv.layout(curValue, curValue, curValue + iv.getWidth(), curValue + iv.getHeight());
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                LogU.i(" onAnimationStart ");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LogU.i(" onAnimationEnd ");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                LogU.i(" onAnimationCancel ");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                LogU.i(" onAnimationRepeat ");

            }
        });
        animator.setDuration(2000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        //animator.setStartDelay(2000); 设置延迟时间
        //ValueAnimator clone = animator.clone();  复制一个动画，和原有的动画一模一样
        animator.start();
        return animator;
    }

    // 插值器

    /**
     * TimeInterpolator  float getInterpolation(float input);
     * input参数是一个float类型，它取值范围是0到1，表示当前动画的进度，取0时表示动画刚开始，取1时表示动画结束，取0.5时表示动画中间的位置，其它类推。
     * <p>
     * <p>
     * input参数与任何我们设定的值没关系，只与时间有关，随着时间的增长，
     * 动画的进度也自然的增加，input参数就代表了当前动画的进度。
     * int curValue = (int) animation.getAnimatedValue();而返回值则表示动画的当前数值进度
     *
     * @return
     */
    private ValueAnimator interAnimatorPlay() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 400);
        animator.addUpdateListener(animation -> {
            int curValue = (int) animation.getAnimatedValue();
            iv.layout(iv.getLeft(), curValue, iv.getRight(), curValue + iv.getHeight());
        });
        animator.setDuration(2000);
        // 弹跳BounceInterpolator()
        // 线性
        animator.setInterpolator(new DefineSelfInterpolator());
        return animator;
    }

    /**
     * Evaluator其实就是一个转换器，他能把小数进度转换成对应的数值位置
     * 对于Evaluator而言，ofInt()的默认Evaluator当然是IntEvaluator;而FloatEvalutar默认的则是FloatEvalutor;
     */
    private ValueAnimator evaluatorAnimatorPlay() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 400);
        animator.addUpdateListener(animation -> {
            int curValue = (int) animation.getAnimatedValue();
            iv.layout(iv.getLeft(), curValue, iv.getRight(), curValue + iv.getHeight());
        });
        animator.setDuration(2000);
        animator.setEvaluator(new DefineEvalutor());
        animator.setInterpolator(new BounceInterpolator());
        return animator;
    }


    /**
     * ArgbEvaluator 用于颜色渐变
     *
     * @return
     */
    private ValueAnimator argevaluatorAnimatorPlay() {
        ValueAnimator animator = ValueAnimator.ofInt(0xffffff00, 0xff0000ff);
        animator.addUpdateListener(animation -> {
            int curValue = (int) animation.getAnimatedValue();
            tvShow.setBackgroundColor(curValue);
        });
        animator.setDuration(5000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setInterpolator(new BounceInterpolator());
        return animator;
    }

}
