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
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
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
import com.mao.cn.learnDevelopProject.wedget.difineview.DefineTextView;
import com.mao.cn.learnDevelopProject.wedget.interploator.CharEvalutor;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class PropertyValuesKeyFrameAnimFragment extends BaseFragment implements IMainGuide {


    @BindView(R.id.tv_play_int)
    TextView tvPlayInt;
    @BindView(R.id.tv_play_z)
    TextView tvPlayZ;
    @BindView(R.id.tv_play_x)
    TextView tvPlayX;
    @BindView(R.id.tv_play_y)
    TextView tvPlayY;
    @BindView(R.id.tv_trans_x)
    TextView tvTranX;
    @BindView(R.id.tv_trans_y)
    TextView tvTranY;
    @BindView(R.id.tv_scale_x)
    TextView tvScaleX;
    @BindView(R.id.tv_scale_y)
    TextView tvScaleY;

    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_object)
    TextView tvObject;

    @BindView(R.id.tv_inter)
    TextView tvInter;
    @BindView(R.id.tv_argb)
    TextView tvArgb;
    @BindView(R.id.tv_arg)
    TextView tvArg;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.tv_define)
    DefineTextView tvDefine;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;

    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.tv_pva)
    TextView tvPva;
    @BindView(R.id.tv_show_num)
    TextView tvShowNum;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_arrow)
    TextView tvArrow;
    @BindView(R.id.tv_cancel_set)
    TextView tvCancelSet;
    @BindView(R.id.tv_value_an)
    TextView tvValueAn;
    @BindView(R.id.tv_object_an)
    TextView tvObjectAn;
    @BindView(R.id.tv_set_an)
    TextView tvSetAn;

    private ObjectAnimator animator;

    private AnimatorSet animatorSet;

    private boolean rotate;

    public static PropertyValuesKeyFrameAnimFragment getInstance() {
        return new PropertyValuesKeyFrameAnimFragment();
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_property_keyframe;
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
            animator = propertyAnimator();
            animator.start();

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvPlayZ).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = propertyObjectAnimator();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvPlayX).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = keyFrameAnimator();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvPlayY).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = keyframeNoInter();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvTranX).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = keyframeInter();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvTranY).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = keyframeInterNoZero();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(tvScaleX).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = keyframeInterNoLast();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvScaleY).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = keyframeInterBetween();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvObject).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = keyframeObject();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvCancel).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator.cancel();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvArrow).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = ivArrowAnimatorPlay();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(iv).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            onTip("点击我了！");
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(tvPva).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animatorSet = playSquentiallyAnim();
            animatorSet.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvInter).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animatorSet = playTogetherAnim();
            animatorSet.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvArgb).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animatorSet = playBuilderAnim();
            animatorSet.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvArg).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animatorSet = playBuilderAnimLintener();
            animatorSet.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvNum).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animatorSet = playBuilderAnimSetProperty();
            animatorSet.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvCancelSet).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animatorSet.cancel();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvValueAn).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            valueAnimatorPlayxml();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvObjectAn).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            objectAnimatorPlayxml();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvSetAn).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            setAnimatorPlayxml();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
    }

    /**
     * ObjectAnimator.ofPropertyValuesHolder()
     * target：指需要执行动画的控件
     * values：是一个可变长参数，可以传进去多个PropertyValuesHolder实例，由于每个PropertyValuesHolder实例都会针对一个属性做动画，
     * 所以如果传进去多个PropertyValuesHolder实例，将会对控件的多个属性同时做动画操作
     *
     * @return
     */
    private ObjectAnimator propertyAnimator() {
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);
        PropertyValuesHolder colorHolder = PropertyValuesHolder.ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvShow, rotationHolder, colorHolder);
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateInterpolator());
        return animator;
    }

    /**
     * propertyName:ObjectAnimator动画操作的属性名;
     * evaluator:Evaluator实例，Evaluator是将当前动画进度计算出当前值的类，
     * 可以使用系统自带的IntEvaluator、FloatEvaluator也可以自定义
     * values：可变长参数，表示操作动画属性的值
     *
     * @return
     */
    private ObjectAnimator propertyObjectAnimator() {
        PropertyValuesHolder objectHolder = PropertyValuesHolder.ofObject("CharText", new CharEvalutor(), 'A', 'Z');
        PropertyValuesHolder colorHolder = PropertyValuesHolder.ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvDefine, objectHolder, colorHolder);
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateInterpolator());
        return animator;
    }

    /**
     * Keyframe
     * 关键帧这个概念是从动画里学来的
     * 一个关键帧必须包含两个原素，第一时间点，第二位置。即这个关键帧是表示的是某个物体在哪个时间点应该在哪个位置上。
     *
     * @return
     */
    private ObjectAnimator keyFrameAnimator() {

        /**
         * fraction：表示当前的显示进度，即从加速器中getInterpolation()函数的返回值；
         * value：表示当前应该在的位置
         */
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);

        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2,
                frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);

        /**
         * scaleX放大1.1倍
         */
        Keyframe scaleXframe0 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleXframe1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleXframe2 = Keyframe.ofFloat(0.2f, 1.1f);
        Keyframe scaleXframe3 = Keyframe.ofFloat(0.3f, 1.1f);
        Keyframe scaleXframe4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleXframe5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleXframe6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleXframe7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleXframe8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleXframe9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleXframe10 = Keyframe.ofFloat(1, 1);
        PropertyValuesHolder frameHolderX = PropertyValuesHolder.ofKeyframe("ScaleX", scaleXframe0, scaleXframe1, scaleXframe2, scaleXframe3, scaleXframe4, scaleXframe5, scaleXframe6, scaleXframe7, scaleXframe8, scaleXframe9, scaleXframe10);

        /**
         * scaleY放大1.1倍
         */
        Keyframe scaleYframe0 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleYframe1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleYframe2 = Keyframe.ofFloat(0.2f, 1.1f);
        Keyframe scaleYframe3 = Keyframe.ofFloat(0.3f, 1.1f);
        Keyframe scaleYframe4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleYframe5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleYframe6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleYframe7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleYframe8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleYframe9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleYframe10 = Keyframe.ofFloat(1, 1);
        PropertyValuesHolder frameHolderY = PropertyValuesHolder.ofKeyframe("ScaleY", scaleYframe0, scaleYframe1, scaleYframe2, scaleYframe3, scaleYframe4, scaleYframe5, scaleYframe6, scaleYframe7, scaleYframe8, scaleYframe9, scaleYframe10);


        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(ivPhone, valuesHolder, frameHolderX, frameHolderY);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * fraction和value这两个参数是必须有的，所以无论用哪种方式实例化Keyframe都必须保证这两个值必须被初始化。
     * 会使用默认的线性插值器（LinearInterpolator）
     *
     * @return
     */
    private ObjectAnimator keyframeNoInter() {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.5f, 100f);
        Keyframe frame2 = Keyframe.ofFloat(1);
        frame2.setValue(0f);
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(ivPhone, frameHolder);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 使用插值器
     *
     * @return
     */
    private ObjectAnimator keyframeInter() {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.5f, 100f);
        Keyframe frame2 = Keyframe.ofFloat(1);
        frame2.setValue(0f);
        frame2.setInterpolator(new BounceInterpolator());
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(ivPhone, frameHolder);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 去掉第0帧，将以第一帧为起始位置
     *
     * @return
     */
    private ObjectAnimator keyframeInterNoZero() {
        Keyframe frame1 = Keyframe.ofFloat(0.5f, 100f);
        Keyframe frame2 = Keyframe.ofFloat(1);
        frame2.setValue(0f);
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame1, frame2);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(ivPhone, frameHolder);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 去掉结束帧，将最后一帧为结束帧
     * <p>
     * 只保留一个中间帧，会崩
     *
     * @return
     */
    private ObjectAnimator keyframeInterNoLast() {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.5f, 100f);
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(ivPhone, frameHolder);
        animator.setDuration(3000);
        return animator;
    }


    /**
     * 保留两个中间帧
     *
     * @return
     */
    private ObjectAnimator keyframeInterBetween() {
        Keyframe frame0 = Keyframe.ofFloat(0.2f, 100f);
        Keyframe frame1 = Keyframe.ofFloat(0.5f, 70f);
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(ivPhone, frameHolder);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 如果去掉第0帧，将以第一个关键帧为起始位置
     * 如果去掉结束帧，将以最后一个关键帧为结束位置
     * 使用Keyframe来构建动画，至少要有两个或两个以上帧
     */


    /**
     * 练习
     * <p>
     * object 自定义属性，自定义算法
     *
     * @return
     */
    private ObjectAnimator keyframeObject() {
        Keyframe frame0 = Keyframe.ofObject(0, 'A');
        Keyframe frame1 = Keyframe.ofObject(0.1f, 'L');
        Keyframe frame2 = Keyframe.ofObject(1f, 'Z');
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("CharText", frame0, frame1, frame2);
        frameHolder.setEvaluator(new CharEvalutor());
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvDefine, frameHolder);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 练习
     */
    private ObjectAnimator ivArrowAnimatorPlay() {
        ObjectAnimator animator;
        if (rotate) {
            PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat("rotation", 180f, 360f);
            animator = ObjectAnimator.ofPropertyValuesHolder(ivArrow, valuesHolder);
            rotate = false;
        } else {
            PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat("rotation", 0, 180f);
            animator = ObjectAnimator.ofPropertyValuesHolder(ivArrow, valuesHolder);
            rotate = true;
        }
        animator.setDuration(1000);
        return animator;
    }

    /**
     * playSequentially的效果，即逐个播放动画，一个动画结束后，播放下一个动画
     * <p>
     * 通过上面两个例子，总结的时候到了：
     * 第一：playTogether和playSequentially在激活动画后，控件的动画情况与它们无关，他们只负责定时激活控件动画。
     * 第二：playSequentially只有上一个控件做完动画以后，才会激活下一个控件的动画，如果上一控件的动画是无限循环，那下一个控件就别再指望能做动画了。
     *
     * @return
     */
    private AnimatorSet playSquentiallyAnim() {
        ObjectAnimator animatoR = ObjectAnimator.ofFloat(iv, "rotation", 0, 20f, -20f, 20f, -20f, 0);
        ObjectAnimator animatorSX = ObjectAnimator.ofFloat(iv, "scaleX", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorSY = ObjectAnimator.ofFloat(iv, "scaleY", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofInt(tvShow, "BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatoR, animatorSX, animatorSY, animatorAlpha);
        animatorSet.setDuration(5000);
        return animatorSet;
    }


    /**
     * playTogether的效果，同时播放动画
     *
     * @return
     */
    private AnimatorSet playTogetherAnim() {
        ObjectAnimator animatoR = ObjectAnimator.ofFloat(ivPhone, "rotation", 0, 20f, -20f, 20f, -20f, 20f, -20f, 0);
        ObjectAnimator animatorSX = ObjectAnimator.ofFloat(ivPhone, "scaleX", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorSY = ObjectAnimator.ofFloat(ivPhone, "scaleY", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofInt(tvShow, "BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatoR, animatorSX, animatorSY, animatorAlpha);
        animatorSet.setDuration(2000);
        return animatorSet;
    }

    /**
     * AnimatorSet.Builder;
     *
     * @return
     */
    private AnimatorSet playBuilderAnim() {
        ObjectAnimator animatoR = ObjectAnimator.ofFloat(ivPhone, "rotation", 0, 20f, -20f, 20f, -20f, 20f, -20f, 0);
        ObjectAnimator animatorSX = ObjectAnimator.ofFloat(ivPhone, "scaleX", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorSY = ObjectAnimator.ofFloat(ivPhone, "scaleY", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofInt(tvShow, "BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatoR).with(animatorSX).with(animatorSY).before(animatorAlpha);
        animatorSet.setDuration(2000);
        return animatorSet;
    }


    /**
     * 监听
     * <p>
     * 所以我们来总结一下AnimatorSet的监听：
     * 1、AnimatorSet的监听函数也只是用来监听AnimatorSet的状态的，与其中的动画无关；
     * 2、AnimatorSet中没有设置循环的函数，所以AnimatorSet监听器中永远无法运行到onAnimationRepeat()中！
     *
     * @return
     */
    private AnimatorSet playBuilderAnimLintener() {
        ObjectAnimator animatoR = ObjectAnimator.ofFloat(ivPhone, "rotation", 0, 20f, -20f, 20f, -20f, 20f, -20f, 0);
        ObjectAnimator animatorSX = ObjectAnimator.ofFloat(ivPhone, "scaleX", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorSY = ObjectAnimator.ofFloat(ivPhone, "scaleY", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofInt(tvShow, "BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        animatorAlpha.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatoR).with(animatorSX).with(animatorSY).before(animatorAlpha);
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                onTip(" onAnimationStart  开始");

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                onTip(" onAnimationStart  结束");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                onTip(" onAnimationCancel  取消");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                onTip(" onAnimationRepeat  重复");
            }
        });
        return animatorSet;
    }


    /**
     * AnimatorSet 属性
     * AnimatorSet 设置了对应的属性，都以其为主
     * 唯一例外 setStartDelay
     * AnimatorSet真正激活延时 = AnimatorSet.startDelay+第一个动画.startDelay
     *
     * @return
     */
    private AnimatorSet playBuilderAnimSetProperty() {
        ObjectAnimator animatoR = ObjectAnimator.ofFloat(ivPhone, "rotation", 0, 20f, -20f, 20f, -20f, 20f, -20f, 0);
        animatoR.setStartDelay(2000);
        animatoR.setDuration(50000);
        animatoR.setInterpolator(new BounceInterpolator());
        ObjectAnimator animatorSX = ObjectAnimator.ofFloat(ivPhone, "scaleX", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorSY = ObjectAnimator.ofFloat(ivPhone, "scaleY", 1, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofInt(tvShow, "BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        animatorAlpha.setInterpolator(new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatoR).with(animatorSX).with(animatorSY).before(animatorAlpha);
        animatorSet.setDuration(2000);

        return animatorSet;
    }


    /**
     *  valueAnimator xml 动画 对应的是 animator   AnimatorInflater 读取
     */
    /**
     * android:duration:每次动画播放的时长
     * android:valueFrom:初始动化值；取值范围为float,int和color，如果取值为float对应的值样式应该为89.0，取值为Int时，对应的值样式为：89;当取值为color时，对应的值样式为 #333333;
     * android:valueTo：动画结束值；取值范围同样是float,int和color这三种类型的值；
     * android:startOffset：动画激活延时；对应代码中的startDelay(long delay)函数；
     * android:repeatCount：动画重复次数
     * android:repeatMode：动画重复模式，取值为repeat和reverse；repeat表示正序重播，reverse表示倒序重播
     * android:valueType：表示参数值类型，取值为intType和floatType；与android:valueFrom、android:valueTo相对应。如果这里的取值为intType，那么android:valueFrom、android:valueTo的值也就要对应的是int类型的数值。
     * 如果这里的数值是floatType，那么android:valueFrom、android:valueTo的值也要对应的设置为float类型的值。
     * 非常注意的是，如果android:valueFrom、android:valueTo的值设置为color类型的值，那么不需要设置这个参数；
     * android:interpolator:设置加速器；
     */
    private void valueAnimatorPlayxml() {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(activity, R.animator.value_animator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                tvShow.layout(animatedValue, animatedValue, tvShow.getWidth() + animatedValue, tvShow.getHeight() + animatedValue);
            }
        });
        valueAnimator.start();
    }


    /**
     * objectAnimator  动画 xml
     * - android:propertyName：对应属性名，即ObjectAnimator所需要操作的属性名。
     * 其它字段的意义与animator的意义与取值是一样的，下面再重新列举一下。
     * - android:duration:每次动画播放的时长
     * - android:valueFrom:初始动化值；取值范围为float,int和color；
     * - android:valueTo：动画结束值；取值范围同样是float,int和color这三种类型的值；
     * - android:startOffset：动画激活延时；对应代码中的startDelay(long delay)函数；
     * - android:repeatCount：动画重复次数
     * - android:repeatMode：动画重复模式，取值为repeat和reverse；repeat表示正序重播，reverse表示倒序重播
     * - android:valueType：表示参数值类型，取值为intType和floatType；与android:valueFrom、android:valueTo相对应。如果这里的取值为intType，那么android:valueFrom、android:valueTo的值也就要对应的是int类型的数值。如果这里的数值是floatType，那么android:valueFrom、android:valueTo的值也要对应的设置为float类型的值。非常注意的是，如果android:valueFrom、android:valueTo的值设置为color类型的值，那么不需要设置这个参数；
     * - android:interpolator:设置加速器；
     */
    private void objectAnimatorPlayxml() {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(activity, R.animator.object_animator);
        valueAnimator.setTarget(iv);
        valueAnimator.start();
    }


    private void setAnimatorPlayxml() {
        AnimatorSet valueAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.set_animator);
        valueAnimator.setTarget(ivPhone);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

}
