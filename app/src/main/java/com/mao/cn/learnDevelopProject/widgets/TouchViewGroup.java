package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-03-28 17:52
 * <p>
 * 用于学习View的 onTouch 事件
 */
public class TouchViewGroup extends LinearLayout {

    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 事件分发
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogU.d("TouchViewGroup dispatchTouchEvent " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 事件拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogU.d("TouchViewGroup onInterceptTouchEvent " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 事件触摸
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogU.d("TouchViewGroup onTouchEvent " + event.getAction());
        return super.onTouchEvent(event);
    }


}
