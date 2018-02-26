package com.mao.cn.learnDevelopProject.wedget.difineview.study;

import android.content.Context;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;

/**
 * author:  zhangkun .
 * date:    on 2018/2/26.
 */

public class DefineView extends View {


    private Context context;

    public DefineView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measuredHeight(heightMeasureSpec));
    }

    /**
     * 测量宽
     *
     * @param widthMeasureSpec
     */
    private int measureWidth(int widthMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = context.getResources().getDimensionPixelOffset(R.dimen.d200);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 测量高
     *
     * @param heightMeasureSpec
     */
    private int measuredHeight(int heightMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = context.getResources().getDimensionPixelOffset(R.dimen.d200);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
