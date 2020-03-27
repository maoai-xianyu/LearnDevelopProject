package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-03-27 15:37
 */
public class TagLayout extends ViewGroup {


    private List<List<View>> mChildViews = new ArrayList<>();
    private List<Integer> mHeights = new ArrayList<>();

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogU.d(" 父 onMeasure 调用 ");
        // 拿到用户设置的大小和测量模式
        // 获取父布局宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        if (modeWidth == MeasureSpec.AT_MOST) {
            throw new RuntimeException("流式布局不允许设置layout_width为wrap_content");
        }

        int height = getPaddingTop() + getPaddingBottom();

        // 行宽
        int lineWidth = 0;
        // 行高  // 高度需要计算
        int lineHeight = 0;
        int childCount = getChildCount();

        mChildViews.clear();
        mHeights.clear();

        LogU.d("父宽度  width " + widthSize);
        List<View> childViews = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            // for循环测量子View
            View childView = getChildAt(i);
            // 下面的方法执行后，就可以获取到子View的宽和高，因为会调用子View的onMeasure
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            // margit 值  ViewGroup.LayoutParams 没有
            // 为什么 LinearLayout 有呢？LinearLayout 有自己的 LayoutParams extends ViewGroup.MarginLayoutParams 同时 复写重要方法 generateLayoutParams(AttributeSet attrs) 方法

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = lp.getMarginStart() + childView.getMeasuredWidth() + lp.getMarginEnd();
            int childHeight = lp.topMargin + childView.getMeasuredHeight() + lp.bottomMargin;
            LogU.d("childHeight " + childHeight);
            // 什么时候需要换行


            // 一行放不下的时候，需要进行换行，同时需要考虑
            if ((lineWidth + childWidth) > (widthSize - getPaddingStart() - getPaddingEnd())) {
                // 需要换行，需要累加高度
                height += lineHeight;
                lineWidth = childWidth;

                mChildViews.add(childViews);
                mHeights.add(lineHeight);

                childViews = new ArrayList<>();
                childViews.add(childView);
            } else {
                lineWidth += childWidth;
                // 一行放下
                lineHeight = Math.max(childHeight, lineHeight);
                childViews.add(childView);
            }

            if (i == childCount - 1) {
                height += lineHeight;
                mHeights.add(lineHeight);
                mChildViews.add(childViews);
            }
            LogU.d("lineHeight " + lineHeight);
        }
        // 根据子View计算和指定自己的布局
        setMeasuredDimension(widthSize, height);

        // 如果高度是wrap_content或者在ScrollView里面,就设置高度为计算的height值
        setMeasuredDimension(widthSize, modeHeight == MeasureSpec.AT_MOST || modeHeight == MeasureSpec.UNSPECIFIED ? height : heightSize);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 摆放子view
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogU.d(" 父 onLayout 调用 ");
        int left = getPaddingStart();
        int top = getPaddingTop();
        LogU.d(" top  " + top);
        // 行数
        int lineNumber = mChildViews.size();
        for (int i = 0; i < lineNumber; i++) {
            List<View> views = mChildViews.get(i);
            int lineHeight = mHeights.get(i);

            for (int j = 0; j < views.size(); j++) {
                View childView = views.get(j);
                // 处理不显示子布局
                if (childView.getVisibility() == GONE) {
                    continue;
                }
                // 获取当前View
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                int leftC = left + lp.leftMargin;
                int topC = top;
                int rightC = leftC + childView.getMeasuredWidth();
                int bottomC = topC + childView.getMeasuredHeight();
                LogU.d(" 布局子View  leftC " + leftC +" topC "+ topC+"  rightC "+rightC+"  bottomC "+bottomC);
                childView.layout(leftC, topC, rightC, bottomC);
                left += childView.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            }
            // top 增加
            left = getPaddingStart();
            top += lineHeight;
            LogU.d(" 换行  top " + top);
        }
    }
}
