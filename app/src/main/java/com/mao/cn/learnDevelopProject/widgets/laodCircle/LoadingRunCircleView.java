package com.mao.cn.learnDevelopProject.widgets.laodCircle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * @author zhangkun
 * @time 2020-04-08 11:41
 */
public class LoadingRunCircleView extends RelativeLayout {

    private LoadingCircleView mLeftCircleView;
    private LoadingCircleView mCenterCircleView;
    private LoadingCircleView mRightCircleView;
    private int mTranslationDistance = 20;
    private final long ANIMATION_DURATION = 350;
    private AnimatorSet mAnimatorOutSet;
    private AnimatorSet mAnimatorInnerSet;
    private boolean mIsStopAnimator = false;

    public LoadingRunCircleView(Context context) {
        this(context, null);
    }

    public LoadingRunCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingRunCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTranslationDistance = dip2px(mTranslationDistance);
        setBackgroundColor(Color.WHITE);
        mLeftCircleView = initCircleView(context);
        mLeftCircleView.changeColor(Color.BLUE);
        mCenterCircleView = initCircleView(context);
        mCenterCircleView.changeColor(Color.RED);
        mRightCircleView = initCircleView(context);
        mRightCircleView.changeColor(Color.YELLOW);
        addView(mLeftCircleView);
        addView(mRightCircleView);
        addView(mCenterCircleView);
        post(new Runnable() {
            @Override
            public void run() {
                executeOutAnimation();

            }
        });
    }

    private void executeOutAnimation() {
        if (mIsStopAnimator) return;

        // 左边
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(mLeftCircleView, "translationX", 0, -mTranslationDistance);

        // 右边
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(mRightCircleView, "translationX", 0, mTranslationDistance);
        mAnimatorOutSet = new AnimatorSet();
        mAnimatorOutSet.setDuration(ANIMATION_DURATION);
        mAnimatorOutSet.playTogether(leftAnimator, rightAnimator);
        mAnimatorOutSet.setInterpolator(new DecelerateInterpolator());
        mAnimatorOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                executeInnerAnimation();
            }
        });
        mAnimatorOutSet.start();


    }


    private void executeInnerAnimation() {
        if (mIsStopAnimator) return;
        // 左边
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(mLeftCircleView, "translationX", -mTranslationDistance, 0);

        // 右边
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(mRightCircleView, "translationX", mTranslationDistance, 0);
        mAnimatorInnerSet = new AnimatorSet();
        mAnimatorInnerSet.setDuration(ANIMATION_DURATION);
        mAnimatorInnerSet.playTogether(leftAnimator, rightAnimator);
        mAnimatorInnerSet.setInterpolator(new AccelerateInterpolator( ));
        mAnimatorInnerSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                // 切换颜色顺序  左边的给中间，中间的给右边，右边的给左边

                int leftCircleViewColor = mLeftCircleView.getColor();
                int rightCircleViewColor = mRightCircleView.getColor();
                int centerCircleViewColor = mCenterCircleView.getColor();

                mLeftCircleView.changeColor(rightCircleViewColor);
                mCenterCircleView.changeColor(leftCircleViewColor);
                mRightCircleView.changeColor(centerCircleViewColor);
                executeOutAnimation();
            }
        });
        mAnimatorInnerSet.start();


    }

    private LoadingCircleView initCircleView(Context context) {

        LoadingCircleView loadingCircleView = new LoadingCircleView(context);
        RelativeLayout.LayoutParams params = new LayoutParams(dip2px(10), dip2px(10));
        params.addRule(CENTER_IN_PARENT);
        loadingCircleView.setLayoutParams(params);
        return loadingCircleView;
    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, getResources().getDisplayMetrics());
    }

    // 在页面关闭的时候
    public void destoryAnimation(){
        super.setVisibility(View.INVISIBLE); // 不要摆放和计算，少走一些系统的源码,View的绘制流程
        // 清理动画
        mAnimatorOutSet.cancel();
        mAnimatorInnerSet.cancel();
        // 把 loadingView 从父布局中移除
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null){
            parent.removeView(this); // 从父布局移除
            removeAllViews(); // 移除自己所有的View
        }
        mIsStopAnimator = true;
    }

    public void startAnmiator(){
        mIsStopAnimator = false;
        post(new Runnable() {
            @Override
            public void run() {
                executeOutAnimation();

            }
        });
    }

    public void stopAnmiator(){
        mIsStopAnimator = true;
        mAnimatorOutSet.cancel();
        mAnimatorInnerSet.cancel();
    }

}
