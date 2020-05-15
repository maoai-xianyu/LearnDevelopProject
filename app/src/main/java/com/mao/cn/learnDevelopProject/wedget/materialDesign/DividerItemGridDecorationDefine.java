package com.mao.cn.learnDevelopProject.wedget.materialDesign;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author zhangkun
 * @time 2020-02-26 23:10
 */
public class DividerItemGridDecorationDefine extends RecyclerView.ItemDecoration {

    private Drawable mDiverder;
    // 获取系统的divider
    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };

    public DividerItemGridDecorationDefine(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDiverder = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        // 2. 调用此绘制方法 RecyclerView 会回调该绘制方法，需要自己去绘制条目的间隔线
        // 垂直
        drawVertical(c, parent);
        // 水平
        drawHorizontal(c, parent);

    }


    private void drawVertical(Canvas c, RecyclerView parent) {
        // 垂直 垂直的矩形
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            //  第一个child为主child的右边是一个矩形
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + mDiverder.getIntrinsicWidth();
            int top = child.getTop() - layoutParams.topMargin;
            int bottom = top + layoutParams.bottomMargin;
            mDiverder.setBounds(left, top, right, bottom);
            mDiverder.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        // 水平
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            //  第一个child为主child的右边是一个矩形
            int left = child.getLeft() - layoutParams.leftMargin;
            int right = child.getRight() + layoutParams.rightMargin;
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDiverder.getIntrinsicHeight();
            mDiverder.setBounds(left, top, right, bottom);
            mDiverder.draw(c);
        }

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        // 1. 调用此方法 (首先会去获取条目之间的间隙宽度---Rect矩形区域，左上右下)
        // 4个方向获得条目的偏移量
        // 垂直
        int right = mDiverder.getIntrinsicWidth();
        int bottom = mDiverder.getIntrinsicHeight();

        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        if (isLastColumn(position, parent)) {// 是否是最后一列
            right = 0;
        }
        if (isLastRow(position, parent)) {// 是否是最后一行
            bottom = 0;
        }
        outRect.set(0, 0, right, bottom);
    }


    /**
     * 是否是最后一列
     */
    public boolean isLastColumn(int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        if ((itemPosition + 1) % spanCount == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否是最后一行
     */
    public boolean isLastRow(int itemPosition, RecyclerView parent) {

        int spanCount = getSpanCount(parent);

        int childCount = parent.getAdapter().getItemCount();

        int rowNumber = childCount % spanCount == 0 ? childCount / spanCount : (childCount / spanCount) + 1;

        if (itemPosition > ((rowNumber - 1) * spanCount - 1)) {
            return true;
        }

        return false;
    }

    /**
     * 获取一行有多少列
     */
    public int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            // 获取一行的spanCount
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            return spanCount;
        }
        return 1;
    }
}
