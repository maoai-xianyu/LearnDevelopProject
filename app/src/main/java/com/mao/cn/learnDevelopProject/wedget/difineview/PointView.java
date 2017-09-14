package com.mao.cn.learnDevelopProject.wedget.difineview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.mao.cn.learnDevelopProject.model.Point;
import com.mao.cn.learnDevelopProject.wedget.interploator.PointEvaluator;

/**
 * author:  zhangkun .
 * date:    on 2017/9/14.
 */

public class PointView extends View {

    private Point mCurPoint;

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurPoint != null) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(300, 300, mCurPoint.getRadius(), paint);
        }
    }

    public void doPointAnim(){
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),new Point(20),new Point(200));
        animator.addUpdateListener(animation -> {
            mCurPoint = (Point)animation.getAnimatedValue();
            invalidate();
        });
        animator.setDuration(2000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }
}
