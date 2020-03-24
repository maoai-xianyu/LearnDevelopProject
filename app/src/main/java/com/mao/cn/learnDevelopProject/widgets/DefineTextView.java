package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-03-24 09:34
 */
public class DefineTextView extends View {

    private String mText;
    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;
    private Paint mPaint;


    // 构造函数会在代码里面new的时候调用
    // DefineTextView tv = new DefineTextView(this)

    public DefineTextView(Context context) {
        this(context, null);
    }

    // 在布局layout中使用

    /**
     * <com.mao.cn.learnDevelopProject.widgets.DefineTextView
     * android:layout_width="wrap_content"
     * android:layout_height="wrap_content"/>
     * ===========
     *
     * @param context
     * @param attrs
     */
    public DefineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 在布局layout中使用(调用)，但是会有style
    public DefineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DefineTextView);
        mText = array.getString(R.styleable.DefineTextView_defineText);
        mTextColor = array.getColor(R.styleable.DefineTextView_defineTextColor, mTextColor);
        // 15 15px 15sp
        mTextSize = array.getDimensionPixelSize(R.styleable.DefineTextView_defineTextSize, sp2px(mTextSize));
        // 回收
        array.recycle();

        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 大小
        mPaint.setTextSize(mTextSize);
        // 颜色
        mPaint.setColor(mTextColor);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp,
                getResources().getDisplayMetrics());
    }

    /**
     * 自定义view的测量方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 布局的宽高都是由这个方法决定的
        // 指定控件的宽高，需要测量
        // 获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 1. 确定的值，这个时候不需要计算，给多少就是多少

        // 2. 给的是wrap_context,需要计算 计算宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            // 计算的宽度 与字体的长度和大小有关，用画笔测量
            Rect rect = new Rect();
            // 获取文本的Rect
            mPaint.getTextBounds(mText, 0, mText.length(), rect);
            width = rect.width() + getPaddingStart() + getPaddingLeft();
        }

        // 3. 计算高度
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            // 计算的宽度 与字体的长度和大小有关，用画笔测量
            Rect rect = new Rect();
            // 获取文本的Rect
            mPaint.getTextBounds(mText, 0, mText.length(), rect);
            height = rect.height() + getPaddingTop() + getPaddingBottom();
        }
        // 设置控件的宽高
        setMeasuredDimension(width, height);

    }

    /**
     * 绘图
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画文字 text  x  y  paint
        // x 就是文字开始的位置 0
        // y 基线 baseline 的位置是：  getHeight() / 2 + dy
        // 而  dy 是 getHeight() / 2 到 baseline 的距离
        // dy 是  (FontMetricsInt.bottom - FontMetricsInt.top)/2 - FontMetricsInt.bottom
        // top 是一个负值  bottom 是一个正值 top 和 bottom 代表 baseline 到文字顶部和底部的距离

        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        LogU.d("font top" + fontMetricsInt.top);
        LogU.d("font bottom" + fontMetricsInt.bottom);
        int dy = Math.abs(fontMetricsInt.top - fontMetricsInt.bottom) / 2 - fontMetricsInt.bottom;

        int baseline = getHeight() / 2 + dy;

        int x = getPaddingStart();
        canvas.drawText(mText, x, baseline, mPaint);
    }


    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param scale   （DisplayMetrics类中属性density）
     * @return
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param scale    （DisplayMetrics类中属性density）
     * @return
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
