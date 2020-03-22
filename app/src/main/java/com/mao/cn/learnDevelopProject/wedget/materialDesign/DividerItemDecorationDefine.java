package com.mao.cn.learnDevelopProject.wedget.materialDesign;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * @author zhangkun
 * @time 2020-02-26 23:10
 */
public class DividerItemDecorationDefine extends RecyclerView.ItemDecoration {

    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDiverder;
    // 获取系统的divider
    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };

    public DividerItemDecorationDefine(Context context, int orientation) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDiverder = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("哥们，不是水平也不是垂直");
        }
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        // 2. 调用此绘制方法 RecyclerView 会回调该绘制方法，需要自己去绘制条目的间隔线
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            // 垂直
            drawVertical(c, parent);
        } else {
            // 水平
            drawHorizontal(c, parent);
        }
    }


    private void drawVertical(Canvas c, RecyclerView parent) {
        // 垂直
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingEnd();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + mDiverder.getIntrinsicHeight();
            mDiverder.setBounds(left, top, right, bottom);
            mDiverder.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        // 水平
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            int right = left + mDiverder.getIntrinsicHeight();
            mDiverder.setBounds(left, top, right, bottom);
            mDiverder.draw(c);
        }

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        // 1. 调用此方法 (首先会去获取条目之间的间隙宽度---Rect矩形区域，左上右下)
        // 获得条目的偏移量
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            // 垂直
            outRect.set(0, 0, 0, mDiverder.getIntrinsicHeight());
        } else {
            // 水平
            outRect.set(0, 0, mDiverder.getIntrinsicWidth(), 0);
        }
    }
}
