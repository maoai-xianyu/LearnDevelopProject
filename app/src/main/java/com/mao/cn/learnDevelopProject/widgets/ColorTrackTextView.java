package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;

/**
 * @author zhangkun
 * @time 2020-03-25 10:38
 */
public class ColorTrackTextView extends TextView {


    // 1. 实现一个文字两种颜色 - 绘制不变色字体的画笔
    private Paint mOriginPaint;
    // 2. 实现一个文字两种颜色 - 绘制变色字体的画笔
    private Paint mChangePaint;
    // 3. 实现一个文字两种颜色 - 当前的进度
    private float mCurrentProgress = 0f;
    // 4. 实现不同朝向:
    private Direction mDirection = Direction.LEFT_TO_RIGHT;

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        int originColor = array.getColor(R.styleable.ColorTrackTextView_originColor, getTextColors().getDefaultColor());
        int changeColor = array.getColor(R.styleable.ColorTrackTextView_changeColor, getTextColors().getDefaultColor());
        array.recycle();
        mOriginPaint = getPaintByColor(originColor);
        mChangePaint = getPaintByColor(changeColor);
    }

    private Paint getPaintByColor(int color) {

        Paint paint = new Paint();
        // 抗锯齿
        paint.setAntiAlias(true);
        // 设置颜色
        paint.setColor(color);
        // 防抖动
        paint.setDither(true);
        // 设置字体大小
        paint.setTextSize(getTextSize());
        return paint;

    }

    // 一个文字两种颜色
    // 利用 clipRect 的 API 可以裁剪  左边用一个画笔去画，右边用另一个画笔画，不断改变中间值
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas); 不用系统的
        canvas.save();
        // canvas.clipRect() 裁剪区域
        // 根据进度把把中间值算出来
        int middle = (int) (mCurrentProgress * getWidth());
        if (mDirection == Direction.LEFT_TO_RIGHT) {
            //左边是红色，右边是黑色
            // 绘制变色的
            drawText(canvas, mChangePaint, 0, middle);
            drawText(canvas, mOriginPaint, middle, getWidth());
        } else {
            // 右边是红色，左边是黑色
            drawText(canvas, mChangePaint, getWidth() - middle, getWidth());
            // 绘制变色的
            drawText(canvas, mOriginPaint, 0, getWidth() - middle);
        }

    }

    /**
     * 绘制text
     *
     * @param canvas
     * @param paint
     * @param start
     * @param end
     */
    private void drawText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        // 绘制不变色的
        Rect originRect = new Rect(start, 0, end, getHeight());
        canvas.clipRect(originRect);

        String text = getText().toString();
        Rect bounds = new Rect();
        // 获取字体的宽度
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = getWidth() / 2 - bounds.width() / 2;
        // 获取文字基线
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseline = getHeight() / 2 + dy;
        canvas.drawText(text, x, baseline, paint);
        canvas.restore();
    }


    public void setChangeColor(int changeColor) {
        this.mChangePaint.setColor(changeColor);
    }

    public void setOriginColor(int originColor) {
        this.mOriginPaint.setColor(originColor);
    }


    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
        invalidate();
    }
}
