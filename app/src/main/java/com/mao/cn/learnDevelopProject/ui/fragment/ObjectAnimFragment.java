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

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Build;
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
import com.mao.cn.learnDevelopProject.wedget.difineview.PointViewObjectAnin;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class ObjectAnimFragment extends BaseFragment implements IMainGuide {


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
    @BindView(R.id.pva)
    PointViewObjectAnin pva;

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

    public static ObjectAnimFragment getInstance() {
        return new ObjectAnimFragment();
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_object_anim;
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
            animator = alphaAnimator();
            animator.start();

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvPlayZ).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = rotateAnimatorZ();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvPlayX).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = rotateAnimatorX();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvPlayY).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = rotateAnimatorY();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvTranX).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = transAnimatorX();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvTranY).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = transAnimatorY();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(tvScaleX).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = scaleAnimatorX();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(tvScaleY).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = scaleAnimatorY();
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

        RxView.clicks(tvPva).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = pvaAnimator();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(tvInter).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = argIntAnimator();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvArgb).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = argAnimator();
            animator.start();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvArg).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            animator = argIntAnimator();
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
     * //1、透明度：alpha
     * public void setAlpha(float alpha)
     *
     * //2、旋转度数：rotation、rotationX、rotationY
     * public void setRotation(float rotation)
     * public void setRotationX(float rotationX)
     * public void setRotationY(float rotationY)

     * //3、平移：translationX、translationY
     * public void setTranslationX(float translationX)
     * public void setTranslationY(float translationY)

     //缩放：scaleX、scaleY
     * public void setScaleX(float scaleX)
     * public void setScaleY(float scaleY)
     *
     * 1、要使用ObjectAnimator来构造对画，要操作的控件中，必须存在对应的属性的set方法
     * 2、setter 方法的命名必须以骆驼拼写法命名，即set后每个单词首字母大写，其余字母小写，
     * 即类似于setPropertyName所对应的属性为propertyName
     */

    /**
     * 第一个参数用于指定这个动画要操作的是哪个控件
     * 第二个参数用于指定这个动画要操作这个控件的哪个属性
     * 第三个参数是可变长参数，这个就跟ValueAnimator中的可变长参数的意义一样了，就是指这个属性值是从哪变到哪。
     * 像我们上面的代码中指定的就是将textview的alpha属性从0变到1再变到0；
     *
     * @return
     */
    private ObjectAnimator alphaAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvShow, "alpha", 1, 0, 1);
        animator.setDuration(2000);
        animator.setInterpolator(new BounceInterpolator());
        return animator;
    }

    /**
     * TextView控件有rotation这个属性吗？没有，不光TextView没有，连它的父类View中也没有这个属性。
     * z轴
     * <p>
     * setRotationX(float rotationX)：表示围绕X轴旋转，rotationX表示旋转度数
     * setRotationY(rotationY):表示围绕Y轴旋转，rotationY表示旋转度数
     * setRotation(float rotation):表示围绕Z旋转,rotation表示旋转度数
     *
     * @return
     */
    private ObjectAnimator rotateAnimatorZ() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvShow, "rotation", 0, 180, 0);
        animator.setDuration(2000);
        return animator;
    }

    private ObjectAnimator rotateAnimatorX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvShow, "rotationX", 0, 180, 0);
        animator.setDuration(2000);
        return animator;
    }

    private ObjectAnimator rotateAnimatorY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvShow, "rotationY", 0, 180, 0);
        animator.setDuration(2000);
        return animator;
    }

    /**
     * setTranslationX(float translationX) :表示在X轴上的平移距离,以当前控件为原点，向右为正方向，参数translationX表示移动的距离。
     * setTranslationY(float translationY) :表示在Y轴上的平移距离，以当前控件为原点，向下为正方向，参数translationY表示移动的距离。
     */
    private ObjectAnimator transAnimatorX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvShow, "translationX", 0, 180, -180, 0);
        animator.setDuration(2000);
        return animator;
    }

    private ObjectAnimator transAnimatorY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvShow, "translationY", 0, 180, -180, 0);
        animator.setDuration(2000);
        return animator;
    }


    /**
     * setScaleX(float scaleX):在X轴上缩放，scaleX表示缩放倍数
     * setScaleY(float scaleY):在Y轴上缩放，scaleY表示缩放倍数
     *
     * @return
     */
    private ObjectAnimator scaleAnimatorX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvShow, "scaleX", 0, 3, 1);
        animator.setDuration(2000);
        return animator;
    }

    private ObjectAnimator scaleAnimatorY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvShow, "scaleY", 0, 3, 1);
        animator.setDuration(2000);
        return animator;
    }

    /**
     * 自定义
     *
     * @return
     */
    private ObjectAnimator pvaAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofInt(pva, "pointRadius", 0, 300, 100);
        animator.setDuration(2000);
        return animator;
    }


    private ObjectAnimator argAnimator() {
        ObjectAnimator animator;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator = ObjectAnimator.ofArgb(tvShow, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        } else {
            animator = ObjectAnimator.ofInt(tvShow, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
            animator.setEvaluator(new ArgbEvaluator());
        }
        animator.setDuration(2000);
        return animator;
    }

    private ObjectAnimator argIntAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofInt(tvShow, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setDuration(2000);
        return animator;
    }

    /**
     * 练习
     */
    private ObjectAnimator ivArrowAnimatorPlay() {
        ObjectAnimator animator;
        if (rotate) {
            animator = ObjectAnimator.ofFloat(ivArrow, "rotation", 180f, 360f);
            rotate = false;
        } else {
            animator = ObjectAnimator.ofFloat(ivArrow, "rotation", 0, 180f);
            rotate = true;
        }
        animator.setDuration(1000);
        return animator;
    }
}
