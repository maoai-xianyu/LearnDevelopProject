package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-03-26 10:00
 */
public class LetterSideBar extends View {

    private Paint mPaint;
    private Paint mLightPaint;
    private int mDefaultColor = Color.BLACK;
    private int mLightColor = Color.BLUE;
    private int mTextSize = 15;

    // 定义26个字母
    public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    // 当期触摸的位置
    private String mCurrentTouchLetter;
    private boolean mCurrentIsTouch;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);

        mDefaultColor = array.getColor(R.styleable.LetterSideBar_letterDefaultColor, mDefaultColor);
        mLightColor = array.getColor(R.styleable.LetterSideBar_letterLightColor, mLightColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.LetterSideBar_letterDefaultTextSize, sp2px(mTextSize));

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize); // 设置的是像素
        mPaint.setColor(mDefaultColor);

        mLightPaint = new Paint();
        mLightPaint.setAntiAlias(true);
        mLightPaint.setTextSize(mTextSize); // 设置的是像素
        mLightPaint.setColor(mLightColor);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp,
                getResources().getDisplayMetrics());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 计算指定的宽度 = 左右的padding + 字母的宽度(取决于你的画笔)
        String textTest = "A";
        Rect bounds = new Rect();
        mPaint.getTextBounds("A", 0, textTest.length(), bounds);
        int textWidthTest = bounds.width();

        int textWidth = (int) mPaint.measureText(textTest);
        LogU.d(" textWidthTest 测试 " + textWidthTest + " textWidth " + textWidth);
        int width = getPaddingEnd() + getPaddingStart() + textWidth;

        // 高度可以直接获取
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / letters.length;
        for (int i = 0; i < letters.length; i++) {
            // 知道每个字符的中心位置  1. 字母的高度一半   2. 字母高度一半+前面字符的高度
            int letterCenterY = i * itemHeight + itemHeight / 2;
            // 基线 基于中心位置
            /*Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
            int dy = (fontMetricsInt.bottom - fontMetricsInt.top)/2 - fontMetricsInt.bottom;*/
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseLine = letterCenterY + dy;
            // x绘制在文字的最中间   = 宽度/2 - 文字的宽度/2
            int textWidth = (int) mPaint.measureText(letters[i]);
            int x = getWidth() / 2 - textWidth / 2;

            // 当前字母 高亮 用两个画笔 改变颜色
            if (letters[i].equals(mCurrentTouchLetter)) {
                canvas.drawText(letters[i], x, baseLine, mLightPaint);
            } else {
                canvas.drawText(letters[i], x, baseLine, mPaint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // 计算出当前触摸字母  获取当前的位置
                float currentMoveY = event.getY();
                // 根据高度算位置 currentMoveY/字母的高度，通过位置获取字母
                int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / letters.length;
                int currentPosition = (int) (currentMoveY / itemHeight);
                if (currentPosition < 0) {
                    currentPosition = 0;
                }
                if (currentPosition > letters.length - 1) {
                    currentPosition = letters.length - 1;
                }
                if (letters[currentPosition].equals(mCurrentTouchLetter)) {
                    return true;
                }
                mCurrentIsTouch = true;
                if (mTouchListener != null) {
                    mTouchListener.onTouch(mCurrentTouchLetter, mCurrentIsTouch);
                }
                // 记录当期的位置
                mCurrentTouchLetter = letters[currentPosition];
                break;
            case MotionEvent.ACTION_UP:
                // 处理回调监听
                mCurrentIsTouch = false;
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mTouchListener != null) {
                            mTouchListener.onTouch(mCurrentTouchLetter, mCurrentIsTouch);
                        }
                    }
                }, 500);

                break;
        }
        // 重新绘制
        invalidate();
        return true;
    }

    private SideBarTouchListener mTouchListener;

    public void setOnSideBarTouchListener(SideBarTouchListener touchListener) {
        this.mTouchListener = touchListener;
    }

    public interface SideBarTouchListener {
        void onTouch(String letter, boolean isTouch);
    }

}
