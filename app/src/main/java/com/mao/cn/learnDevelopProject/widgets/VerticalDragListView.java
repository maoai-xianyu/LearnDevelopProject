package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ListViewCompat;
import androidx.customview.widget.ViewDragHelper;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-03-31 08:47
 */
public class VerticalDragListView extends FrameLayout {

    // 系统提供的好的工具
    private ViewDragHelper mViewDragHelper;
    private View mListView;
    // 后面菜单的高度
    private int mMenuHeight;

    private float mDownY;


    private boolean mMenuIsOpen = false;

    public VerticalDragListView(@NonNull Context context) {
        this(context, null);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mViewDragHelper = ViewDragHelper.create(this, mCallback);
    }


    // 1. 拖动子View

    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            // 指定该子VIew是否可以拖动，就是 child 可以拖动
            // 只能是列表可以拖动
            return mListView == child;
        }


        /*@Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            // 水平拖动移动的位置
            return left;
        }*/

        // 2.2 只能垂直拖动
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            // 垂直拖动移动的位置
            // 2.3 垂直拖动的范围只能是后面菜单 View 的高度
            if (top < 0) {
                top = 0;
            }

            if (top >= mMenuHeight) {
                top = mMenuHeight;
            }
            return top;
        }


        // 手指松开
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            LogU.d("yvel " + yvel);
            LogU.d("mListView.getTop() " + mListView.getTop());
            if (releasedChild == mListView) {
                if (mListView.getTop() > mMenuHeight / 2) {
                    // 滚动到菜单的高度 打开
                    mViewDragHelper.settleCapturedViewAt(0, mMenuHeight);
                    mMenuIsOpen = true;
                } else {
                    // 滚动到0的位置  关闭
                    mViewDragHelper.settleCapturedViewAt(0, 0);
                    mMenuIsOpen = false;
                }
                invalidate();

            }
        }
    };


    // 错误 because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.
    // VerticalDragListView.onInterceptTouchEvent().DOWN -> ListView.OnTouchEvent()
    // ->VerticalDragListView.onInterceptTouchEvent().MOVE -> VerticalDragListView.OnTouchEvent().MOVE
    // 解决：需要拦截 VerticalDragListView.onInterceptTouchEvent().DOWN
    // listview可以滑动，但是菜单没有效果了，请求父类不要拦截我的事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 菜单打开 要全部拦截
        if (mMenuIsOpen) {
            return true;
        }
        // 向下滑动拦截，不要给ListView做处理
        // 父 view 拦截子view, 但是子view可以调用 requestDisallowInterceptTouchEvent 请求父类不要拦截
        // 改变的是 mGroupFlags 的值
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                // mViewDragHelper VerticalDragListView 的 DOWN 事件
                mViewDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                if (moveY - mDownY > 0 && !canChildScrollUp()) {
                    // 向下滑动 && 滚动到了顶部，拦截不让ListList做处理
                    return true;
                }

                break;


        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * SwipeRefreshLayout  安卓7.0 - 24
     * 判断view是否滚动到最顶部
     * @return
     */
    /*public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mListView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mListView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mListView, -1) || mListView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mListView, -1);
        }
    }*/

    /**
     * SwipeRefreshLayout 判断顶部的方法
     * 判断view是否滚动到最顶部
     * @return
     */
    public boolean canChildScrollUp() {
        if (mListView instanceof ListView) {
            return ListViewCompat.canScrollList((ListView) mListView, -1);
        }
        return mListView.canScrollVertically(-1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("只能包含2个子布局");
        }
        mListView = getChildAt(1);
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogU.d("调用多次");
        View childBg = getChildAt(0);
        mMenuHeight = childBg.getMeasuredHeight();

    }*/

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogU.d("只调用一次");
        if (changed) {
            View childBg = getChildAt(0);
            mMenuHeight = childBg.getMeasuredHeight();
        }
    }

    /**
     * 响应滚动
     */
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

}
