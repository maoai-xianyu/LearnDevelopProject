package com.mao.cn.learnDevelopProject.widgets;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * @author zhangkun
 * @time 2020-04-09 20:56
 * 自定义路径属性动画
 */
public class LoveTypeEvaluator implements TypeEvaluator<PointF> {

    private PointF mPoint1;
    private PointF mPoint2;

    public LoveTypeEvaluator(PointF p1, PointF p2) {
        this.mPoint1 = p1;
        this.mPoint2 = p2;
    }

    //
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        // fraction -> t => 0-1  startValue 代表 p0  endValue 代表 p3
        // 开始套公式 4个点，还有两个点从构造函数来

        PointF pointF = new PointF();

        pointF.x = (float) (startValue.x * Math.pow((1 - fraction), 3)
                + 3 * mPoint1.x * fraction * Math.pow((1 - fraction), 2)
                + 3 * mPoint2.x * Math.pow(fraction, 2) * (1 - fraction)
                + endValue.x * Math.pow(fraction, 3));

        pointF.y = (float) (startValue.y * Math.pow((1 - fraction), 3)
                + 3 * mPoint1.y * fraction * Math.pow((1 - fraction), 2)
                + 3 * mPoint2.y * Math.pow(fraction, 2) * (1 - fraction)
                + endValue.y * Math.pow(fraction, 3));


        return pointF;
    }
}
