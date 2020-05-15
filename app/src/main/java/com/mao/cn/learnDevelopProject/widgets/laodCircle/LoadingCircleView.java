package com.mao.cn.learnDevelopProject.widgets.laodCircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhangkun
 * @time 2020-04-08 11:34
 */
public class LoadingCircleView extends View {

    private Paint mPaint;
    private int color;


    public LoadingCircleView(Context context) {
        this(context, null);
    }

    public LoadingCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 如果是原，那么宽高是一样的正方形
        int rx = getWidth() / 2;
        int ry = getHeight() / 2;
        canvas.drawCircle(rx, ry, getWidth() / 2, mPaint);
    }

    public void changeColor(int color) {
        this.color = color;
        mPaint.setColor(color);
        invalidate();
    }

    // 获取颜色
    public int getColor() {
        return color;
    }
}
