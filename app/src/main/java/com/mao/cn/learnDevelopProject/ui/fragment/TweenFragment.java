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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
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
public class TweenFragment extends BaseFragment implements IMainGuide {


    @BindView(R.id.btn_scale_1)
    TextView btnScale1;
    @BindView(R.id.btn_scale_2)
    TextView btnScale2;
    @BindView(R.id.btn_play_3)
    TextView btnPlay3;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.btn_alpha)
    TextView btnAlpha;
    @BindView(R.id.btn_rotate)
    TextView btnRotate;
    @BindView(R.id.btn_rotate_arrow)
    TextView btnRotateArrow;
    @BindView(R.id.btn_trans_x)
    TextView btnTransX;
    @BindView(R.id.btn_trans_y)
    TextView btnTransY;
    @BindView(R.id.btn_rotate_inter)
    TextView btnRotateInter;
    @BindView(R.id.btn_animator_set)
    TextView btnAnimatorSet;

    private boolean rotate;

    public static TweenFragment getInstance() {
        return new TweenFragment();
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_tween;
    }

    @Override
    public void initView() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {


    }

    @Override
    public void setListener() {

        RxView.clicks(btnScale1).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            scaleAnimattion(R.anim.scaleanim1);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(btnScale2).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            scaleAnimattion(R.anim.scaleanim2);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(btnPlay3).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            scaleAnimattion(R.anim.scaleanim3);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnAlpha).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            alphaAnimattion(R.anim.alphaanim);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnRotate).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            rotateAnimattion(R.anim.rorateanim);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnRotateArrow).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            if (rotate) {
                rotateAnimattionArrow(R.anim.rorateanimback);
                rotate = false;
            } else {
                rotate = true;
                rotateAnimattionArrow(R.anim.rorateanim);
            }

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(btnTransX).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            transAnimattion(R.anim.transanimx);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnTransY).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            transAnimattion(R.anim.transanimy);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnAnimatorSet).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            setAnimattion(R.anim.setanim);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnRotateInter).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            rotateAnimattionInterpolator(R.anim.rorateanim_interpolator);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    /**
     * 动画公有属性
     * android:duration        动画持续时间，以毫秒为单位
     * android:fillAfter          如果设置为true，控件动画结束时，将保持动画最后时的状态
     * android:fillBefore       如果设置为true,控件动画结束时，还原到开始动画前的状态
     * android:fillEnabled    与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
     * android:repeatCount 重复次数
     * android:repeatMode	重复类型，有reverse和restart两个值，reverse表示倒序回放，restart表示重新放一遍，必须与repeatCount一起使用才能看到效果。因为这里的意义是重复的类型，即回放时的动作。
     * android:interpolator  设定插值器，其实就是指定的动作效果，比如弹跳效果等
     */

    /**
     * android:fromXScale    起始的X方向上相对自身的缩放比例，浮点值，比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
     * android:toXScale        结尾的X方向上相对自身的缩放比例，浮点值；
     * android:fromYScale    起始的Y方向上相对自身的缩放比例，浮点值，
     * android:toYScale        结尾的Y方向上相对自身的缩放比例，浮点值；
     * android:pivotX            缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，
     * 当为数值时，表示在当前View的左上角，即原点处加上50px，做为起始缩放点；
     * 如果是50%，表示在当前控件的左上角加上自己宽度的50%做为起始点；
     * 如果是50%p，那么就是表示在当前的左上角加上父控件宽度的50%做为起始点x轴坐标。（具体意义，后面会举例演示）
     * android:pivotY           缩放起点Y轴坐标，取值及意义跟android:pivotX一样。
     */
    private void scaleAnimattion(int resId) {
        Animation animation = AnimationUtils.loadAnimation(activity, resId);
        iv.startAnimation(animation);
    }


    /**
     * android:fromAlpha   动画开始的透明度，从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
     * android:toAlpha       动画结束时的透明度，也是从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
     *
     * @param resId
     */
    private void alphaAnimattion(int resId) {
        Animation animation = AnimationUtils.loadAnimation(activity, resId);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                onTip("动画开始");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onTip("动画结束");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                onTip("动画重复");
            }
        });
        iv.startAnimation(animation);
    }

    /**
     * android:fromDegrees     开始旋转的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
     * android:toDegrees         结束时旋转到的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
     * android:pivotX               缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述
     * android:pivotY               缩放起点Y轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p
     *
     * @param resId
     */
    private void rotateAnimattion(int resId) {
        Animation animation = AnimationUtils.loadAnimation(activity, resId);
        iv.startAnimation(animation);
    }

    private void rotateAnimattionArrow(int resId) {
        Animation animation = AnimationUtils.loadAnimation(activity, resId);
        ivArrow.startAnimation(animation);
    }


    /**
     * android:fromXDelta     起始点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述，这里就不再重讲
     * android:fromYDelta    起始点Y轴从标，可以是数值、百分数、百分数p 三种样式；
     * android:toXDelta         结束点X轴坐标
     * android:toYDelta        结束点Y轴坐标
     *
     * @param resId
     */
    private void transAnimattion(int resId) {
        Animation animation = AnimationUtils.loadAnimation(activity, resId);
        iv.startAnimation(animation);
    }

    private void setAnimattion(int resId) {
        Animation animation = AnimationUtils.loadAnimation(activity, resId);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                onTip("动画开始");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onTip("动画结束");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                onTip("动画重复");
            }
        });
        iv.startAnimation(animation);
    }


    /**
     *
     * Interpolator插值器
     * AccelerateDecelerateInterpolator   在动画开始与介绍的地方速率改变比较慢，在中间的时候加速
     * AccelerateInterpolator                     在动画开始的地方速率改变比较慢，然后开始加速
     * AnticipateInterpolator                      开始的时候向后然后向前甩
     * AnticipateOvershootInterpolator     开始的时候向后然后向前甩一定值后返回最后的值
     * BounceInterpolator                          动画结束的时候弹起
     * CycleInterpolator                             动画循环播放特定的次数，速率改变沿着正弦曲线
     * DecelerateInterpolator                    在动画开始的地方快然后慢
     * LinearInterpolator                            以常量速率改变
     * OvershootInterpolator                      向前甩一定值后再回到原来位置
     */
    private void rotateAnimattionInterpolator(int resId) {
        Animation animation = AnimationUtils.loadAnimation(activity, resId);
        iv.startAnimation(animation);
    }

}
