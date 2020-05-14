package com.mao.cn.learnDevelopProject.widgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;

/**
 * @author zhangkun
 * @time 2020-04-07 10:15
 */
public class LoadingFixView extends LinearLayout {

    private DefineShapeView dfView;
    private View vShape;
    private int mTranslationDistance;
    private final int ANIMATOR_DURATION = 350;

    private AnimatorSet mFullAnimatorSet, mUpAnimatorSet;

    public LoadingFixView(Context context) {
        this(context, null);
    }

    public LoadingFixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingFixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTranslationDistance = dip2px(80);
        initLayout();
        initAnimator();
    }

    private void initAnimator() {

        // 下落动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(dfView, "translationY", 0, mTranslationDistance);
        // 中间的阴影缩小
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(vShape, "scaleX", 1f, 3f);
        mFullAnimatorSet = new AnimatorSet();
        mFullAnimatorSet.setInterpolator(new AccelerateInterpolator());
        mFullAnimatorSet.setDuration(ANIMATOR_DURATION);
        mFullAnimatorSet.playTogether(objectAnimator, scaleAnimator);
        // 是一种思想
        mFullAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 改变形状
                dfView.changeShape();
                // 下落完之后上抛
                upAnimator();
            }
        });


        objectAnimator = ObjectAnimator.ofFloat(dfView, "translationY", mTranslationDistance, 0);
        // 中间的阴影缩小
        scaleAnimator = ObjectAnimator.ofFloat(vShape, "scaleX", 3f, 1f);
        mUpAnimatorSet = new AnimatorSet();
        mUpAnimatorSet.setInterpolator(new DecelerateInterpolator());
        mUpAnimatorSet.setDuration(ANIMATOR_DURATION);;
        mUpAnimatorSet.playTogether(objectAnimator, scaleAnimator);

        // 是一种思想
        mUpAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 上抛完之后下落
                fallAnimator();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                // 开始旋转
                startRotationAnimator();

            }
        });


    }

    private void initLayout() {
        // 初始化布局
        /*View view = View.inflate(getContext(), R.layout.loading_view, null);
        // 添加布局
        addView(view);*/
        View view = inflate(getContext(), R.layout.loading_view, this);

        dfView = view.findViewById(R.id.dfView);
        vShape = view.findViewById(R.id.v);

        // onResume 方法中执行
        post(this::fallAnimator);

    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip,
                getResources().getDisplayMetrics());
    }

    private void fallAnimator() {
        mFullAnimatorSet.start();
    }

    private void upAnimator() {
        mUpAnimatorSet.start();
    }

    private void startRotationAnimator() {

        ObjectAnimator objectAnimator;
        switch (dfView.getCurrentShape()) {
            case Square:
                //180
                objectAnimator = ObjectAnimator.ofFloat(dfView, "rotation", 0, 180);
                break;
            case Triangle:
                objectAnimator = ObjectAnimator.ofFloat(dfView, "rotation", 0, -120);
                break;
            default:
                objectAnimator = ObjectAnimator.ofFloat(dfView, "rotation", 0, 270);
                break;

        }
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.setDuration(ANIMATOR_DURATION);
        objectAnimator.start();

    }

    public void stopAnimation() {
        super.setVisibility(View.INVISIBLE); // 不要摆放和计算，少走一些系统的源码,View的绘制流程
        // 清理动画
        dfView.clearAnimation();
        vShape.clearAnimation();
    }


    public void destoryAnimation() {
        super.setVisibility(View.INVISIBLE); // 不要摆放和计算，少走一些系统的源码,View的绘制流程
        // 清理动画
        dfView.clearAnimation();
        vShape.clearAnimation();
        // 把 loadingView 从父布局中移除
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this); // 从父布局移除
            removeAllViews(); // 移除自己所有的View
        }
        mUpAnimatorSet.cancel();
        mFullAnimatorSet.cancel();
    }

    public void startAnimation() {
        super.setVisibility(View.VISIBLE);
        post(this::fallAnimator);
    }
}
