package com.mao.cn.learnDevelopProject.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-03-25 15:27
 */
public class CircleProgressBar extends View {


    private Paint mOutPaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;

    private int mOutPaintColor = Color.BLUE;
    private int mInnerPaintColor = Color.RED;
    private int mTextColor = Color.BLACK;
    private int mTextSize = 15;

    private int mCircleBorderWidth;

    private int mCurrentProgress;
    private int maxProgress;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);

        mOutPaintColor = array.getColor(R.styleable.CircleProgressBar_circleOuterColor, mOutPaintColor);
        mInnerPaintColor = array.getColor(R.styleable.CircleProgressBar_circleInnerColor, mInnerPaintColor);
        mTextColor = array.getColor(R.styleable.CircleProgressBar_circleTextColor, mTextColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.CircleProgressBar_circleTextSize, sp2px(mTextSize));
        mCircleBorderWidth = (int) array.getDimension(R.styleable.QQStepView_borderWidth, mCircleBorderWidth);

        array.recycle();


        mOutPaint = new Paint();
        mOutPaint.setColor(mOutPaintColor);
        mOutPaint.setAntiAlias(true);
        mOutPaint.setStrokeWidth(mCircleBorderWidth);
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);
        mOutPaint.setStyle(Paint.Style.STROKE);

        mInnerPaint = new Paint();
        mInnerPaint.setColor(mInnerPaintColor);
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(mCircleBorderWidth);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setStyle(Paint.Style.STROKE);


        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp,
                getResources().getDisplayMetrics());
    }

    private int px2dp(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                sp,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        LogU.d(" width  " + width + " height " + height);
        LogU.d(" widthMode  " + widthMode + " heightMode " + heightMode);

        if (widthMode == MeasureSpec.AT_MOST) {
            width = px2dp(100);
        }
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画外面的圆弧
        @SuppressLint("DrawAllocation") RectF rectF = new RectF(mCircleBorderWidth / 2,
                mCircleBorderWidth / 2,
                getWidth() - mCircleBorderWidth / 2,
                getWidth() - mCircleBorderWidth / 2
        );
        canvas.drawArc(rectF, 0, 360, false, mOutPaint);

        // 画进度内湖
        if (maxProgress == 0) return;
        float sweep = (float) mCurrentProgress / maxProgress;
        canvas.drawArc(rectF, 0, sweep * 360, false, mInnerPaint);

        // 画文字
        String text = mCurrentProgress + "%";

        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        // 获取文字的位置
        int start = getWidth() / 2 - bounds.width() / 2;

        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int baseline = getHeight() / 2 + ((fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom);
        canvas.drawText(text, start, baseline, mTextPaint);

    }

    public void setMaxPresent(int max) {
        this.maxProgress = max;
    }

    public void setCurrentPresent(int currentProgress) {
        this.mCurrentProgress = currentProgress;
        invalidate();
    }
}
