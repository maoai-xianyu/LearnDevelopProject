package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhangkun
 * @time 2020-03-24 09:34
 */
public class MyTextView extends View {


    // 构造函数会在代码里面new的时候调用
    // DefineTextView tv = new DefineTextView(this)

    public MyTextView(Context context) {
        super(context);
    }

    // 在布局layout中使用

    /**
     * <com.mao.cn.learnDevelopProject.widgets.DefineTextView
     * android:layout_width="wrap_content"
     * android:layout_height="wrap_content"/>
     *
     * @param context
     * @param attrs
     */
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // 在布局layout中使用(调用)，但是会有style
    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    }

    /**
     * 绘图
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 处理更用户交互，手指触摸
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                // 手指抬起
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动
                break;
            case MotionEvent.ACTION_DOWN:
                // 手指抬起
                break;
        }
        return super.onTouchEvent(event);
    }
}
