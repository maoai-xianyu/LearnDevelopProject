package com.mao.cn.learnDevelopProject.wedget.difineview.study;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author:  zhangkun .
 * date:    on 2017/9/28.
 */

/**
 * Canvas就是画布，Paint就是画笔
 */

/**
 * Canvas
 * 1、保存像素的Bitmap
 * 2、管理绘制请求的Canvas
 * 3、绘画的原始基本元素，例如矩形，线，文字，Bitmap
 * 4、拥有颜色和风格信息的画笔
 *
 */
public class MeasureView1CanvasAndPaint extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public MeasureView1CanvasAndPaint(Context context) {
        super(context);
        initPaint();
    }

    public MeasureView1CanvasAndPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MeasureView1CanvasAndPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint.setColor(Color.parseColor("#FF4081"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left = getLeft();
        float right = getRight();
        float top = getTop();
        float bottom = getBottom();
        canvas.drawRect(left,top,right,bottom,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measuredHeight(heightMeasureSpec));
    }

    /**
     * 测量宽
     * @param widthMeasureSpec
     */
    private int measureWidth(int widthMeasureSpec) {
        int result ;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST){
                result = Math.min(result,specSize);
            }
        }
        return result;
    }

    /**
     * 测量高
     * @param heightMeasureSpec
     */
    private int measuredHeight(int heightMeasureSpec) {
        int result ;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = 200;
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result,specSize);
            }
        }
        return  result;
    }
}
