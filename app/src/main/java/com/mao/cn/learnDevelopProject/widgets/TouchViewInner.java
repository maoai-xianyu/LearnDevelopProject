package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-03-28 17:52
 * <p>
 * 用于学习View的 onTouch 事件
 */
public class TouchViewInner extends View {

    public TouchViewInner(Context context) {
        super(context);
    }

    public TouchViewInner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewInner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogU.d("------> TouchViewInner onTouchEvent " + event.getAction());
        //super.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogU.d("------> TouchViewInner dispatchTouchEvent " + event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
