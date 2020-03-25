package com.mao.cn.learnDevelopProject.widgets;

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
 * @time 2020-03-24 18:32
 */
public class QQStepView extends View {

    private int mOuterColor = Color.BLUE;
    private int mInnerColor = Color.RED;
    private int mBorderWidth = 20; // 代表的是 20px
    private int mStepTextSize = 15;
    private int mStepTextColor;

    private Paint mOutPaint, mInnerPaint, mTextPaint;


    // 总共的步数
    private int mStepMax = 0;
    // 当前的步数
    private int mCurrentStep = 0;

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor, mOuterColor);
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor);
        mBorderWidth = (int) array.getDimension(R.styleable.QQStepView_borderWidth, mBorderWidth);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, sp2px(mStepTextSize));
        mStepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor);
        array.recycle();
        // 1. 分析效果
        // 2. 确定自定义属性，编写 attrs.xml
        // 3. 在布局中使用
        // 4. 在自定义view中获取自定义属性
        // 5. onMeasure()测量
        // 6. onDraw() 画外圆弧，内圆弧，文字
        // 7. 其他


        mOutPaint = new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setStrokeWidth(mBorderWidth);
        mOutPaint.setColor(mOuterColor);
        mOutPaint.setStrokeCap(Paint.Cap.ROUND); // 圆弧
        mOutPaint.setStyle(Paint.Style.STROKE);// 画笔空心


        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND); // 圆弧
        mInnerPaint.setStyle(Paint.Style.STROKE);// 画笔空心

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 调用者在布局文件中可能是  wrap_content 宽度和高度不一致
        // 获取模式   AT_MOST  40dp
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        LogU.d(" width  " + width + " height " + height);
        LogU.d(" widthMode  " + widthMode + " heightMode " + heightMode);

        if (widthMode == MeasureSpec.AT_MOST) {
            width = px2dp(100);
        }
        // 宽度高度不一致 取最小值，确保是个正方形
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 6.1 画外圆弧  分析：圆弧闭合了  思考：边缘没显示完整， 描边有宽度 mBorderWidth 圆弧
        /*int center = getWidth() / 2;
        int radius = getWidth() / 2 - mBorderWidth/2;
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);*/
        RectF rectF = new RectF(mBorderWidth / 2,
                mBorderWidth / 2,
                getWidth() - mBorderWidth / 2,
                getWidth() - mBorderWidth / 2);
        canvas.drawArc(rectF, 135, 270, false, mOutPaint);

        // 6.2 画内圆弧  怎么画，肯定不能写死  百分百比，是用户设置
        if (mStepMax == 0) return;
        float sweepAngle = (float) mCurrentStep / mStepMax;
        LogU.d("sweepAngle " + sweepAngle);
        canvas.drawArc(rectF, 135, sweepAngle * 270, false, mInnerPaint);

        // 6.3 画文字
        String stepText = mCurrentStep + "";
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = Math.abs(fontMetrics.top - fontMetrics.bottom) / 2 - fontMetrics.bottom;
        int baseline = getHeight() / 2 + dy;

        Rect rect = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), rect);
        int dx = getWidth() / 2 - rect.width() / 2;
        canvas.drawText(stepText, dx, baseline, mTextPaint);

    }


    // 7. 其他 动起来
    public synchronized void setStepMax(int setMax) {
        this.mStepMax = setMax;
    }

    public synchronized void setCurrentStepMax(int currentStep) {
        this.mCurrentStep = currentStep;
        // 不断绘制 onDraw
        invalidate();
    }

}
