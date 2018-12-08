package com.mao.cn.learnDevelopProject.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * author : zhangkun .
 * date   : on 2018/12/8
 * 用于控件動畫的处理
 */
public class ViewTools {

    // 容器 View
    public static void hideView(View view) {
        hideView(view, 0);
    }

    public static void showView(View view) {
        showView(view, 0);
    }

    public static void hideView(View view, long time) {
        RotateAnimation ra = new RotateAnimation(0, 180, view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true); // 动画停留在播放完成的状态
        ra.setStartOffset(time);// 延时
        view.startAnimation(ra);
    }

    public static void showView(View view, long time) {
        RotateAnimation ra = new RotateAnimation(180, 360, view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true); // 动画停留在播放完成的状态
        ra.setStartOffset(time);// 延时
        view.startAnimation(ra);
    }

    // 容器 ViewGroup
    public static void hideViewGroup(ViewGroup view) {
        hideViewGroup(view, 0);
    }

    public static void showViewGroup(ViewGroup view) {
        showViewGroup(view, 0);
    }

    public static void hideViewGroup(ViewGroup view, long time) {
        RotateAnimation ra = new RotateAnimation(0, 180, view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true); // 动画停留在播放完成的状态
        ra.setStartOffset(time);// 延时
        view.startAnimation(ra);
        // 设置容器的子view不可点
        for (int i = 0; i < view.getChildCount(); i++) {
            View v = view.getChildAt(i);
            v.setEnabled(false);
        }
    }

    public static void showViewGroup(ViewGroup view, long time) {
        RotateAnimation ra = new RotateAnimation(180, 360, view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true); // 动画停留在播放完成的状态
        ra.setStartOffset(time);// 延时
        view.startAnimation(ra);
        // 设置容器的子view可点
        for (int i = 0; i < view.getChildCount(); i++) {
            View v = view.getChildAt(i);
            v.setEnabled(true);
        }
    }

    // 属性动画
    public static void hideViewGroupProperty(ViewGroup view) {
        hideViewGroupProperty(view, 0);
    }

    public static void showViewGroupProperty(ViewGroup view) {
        showViewGroupProperty(view, 0);
    }

    public static void hideViewGroupProperty(ViewGroup view, long time) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, 180);
        animator.setDuration(500);
        animator.setStartDelay(time);
        animator.start();

        // 设置圆心
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
    }

    public static void showViewGroupProperty(ViewGroup view, long time) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 180, 360);
        animator.setDuration(500);
        animator.setStartDelay(time);
        animator.start();

        // 设置圆心
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
    }

}
