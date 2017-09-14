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
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Build;
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

    private ObjectAnimator animator;

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

        RxView.clicks(iv).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            onTip("点击我了！");
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(tvInter).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvArgb).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvArg).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvNum).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator.start();
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
}
