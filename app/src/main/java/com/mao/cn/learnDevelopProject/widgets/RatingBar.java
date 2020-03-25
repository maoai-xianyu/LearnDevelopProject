package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-03-25 19:00
 */
public class RatingBar extends View {

    private Bitmap mStarNormalBitmap;
    private Bitmap mStarFocusBitmap;
    private int mGradeNumber = 5;
    private int mCurrentGrade = 0;


    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);

        int starNormalId = array.getResourceId(R.styleable.RatingBar_starNormal, 0);
        if (starNormalId == 0) {
            throw new RuntimeException("请设置属性 starNormal");
        }
        mStarNormalBitmap = BitmapFactory.decodeResource(getResources(), starNormalId);
        int focusNormalId = array.getResourceId(R.styleable.RatingBar_starFocus, 0);
        if (focusNormalId == 0) {
            throw new RuntimeException("请设置属性 focusNormal");
        }
        mStarFocusBitmap = BitmapFactory.decodeResource(getResources(), focusNormalId);
        mGradeNumber = array.getInt(R.styleable.RatingBar_gradeNumber, mGradeNumber);
        array.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 计算高度 一张图片的高度   还需要实现 padding  + 加上间隔

        int height = mStarFocusBitmap.getHeight();
        int width = mStarFocusBitmap.getWidth() * mGradeNumber; // + 加上间隔
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mGradeNumber; i++) {
            int x = i * mStarFocusBitmap.getWidth();
            // 结合第二个步骤 触摸的是  mCurrentGrade 是不断变化的
            if (mCurrentGrade > i) {
                canvas.drawBitmap(mStarFocusBitmap, x, 0, null);
            } else {
                canvas.drawBitmap(mStarNormalBitmap, x, 0, null);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 移动
        // 按下
        // 抬起
        // 处理逻辑都是一样的，判断手指的位置，根据当前位置 计算出分数，在去刷新显示
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 按下
            case MotionEvent.ACTION_MOVE: // 移动
                //case MotionEvent.ACTION_UP: // 抬起
                float moveX = event.getX(); // event.getX() 获取相对于当前控件的位置  event.getRawX()  获取屏幕的x位置
                LogU.d("RatingBar  ---- " + moveX);
                int currentGrade = (int) (moveX / mStarFocusBitmap.getWidth() + 1);

                // 范围
                if (currentGrade < 0) {
                    currentGrade = 0;
                }

                if (currentGrade > mGradeNumber) {
                    currentGrade = mGradeNumber;
                }

                // 分数相同的情况下不要绘制了 , 尽量减少onDraw()的调用
                if (currentGrade == mCurrentGrade) {
                    return true;
                }

                // 刷新显示
                mCurrentGrade = currentGrade;
                invalidate(); // onDraw  尽量减少 onDraw() 的调用，目前是不断调用的
                break;
        }

        return true; // onTouch 需要看源码  false 不消费  第一次 DOWN false  DOWN 以后的事件是进不来的。

    }
}
