package com.mao.cn.learnDevelopProject.wedget.interploator;

import android.animation.TypeEvaluator;

import com.mao.cn.learnDevelopProject.model.Point;

/**
 * author:  zhangkun .
 * date:    on 2017/9/14.
 */

public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int start = startValue.getRadius();
        int end = endValue.getRadius();
        int curValue = (int) (start + fraction * (end - start));
        return new Point(curValue);
    }
}
