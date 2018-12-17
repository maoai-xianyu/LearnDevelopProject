package com.mao.cn.learnDevelopProject.wedget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;

/**
 * author : zhangkun .
 * date   : on 2018/12/17
 * 一个视图从创建到显示过程中的主要方法
 * 1. 构造方法实例化类
 * 2. 测量measure  -> onMeasure(int,int) 如果当前view是一个viewGroup，还有义务测量孩子  孩子有建议权（高和宽）
 * 3. 指定位置 layout --> onLayout()  指定控件的位置，一般view不用写着方法，ViewGroup的时候才需要，一般view不需要重写该方法
 * 4. 绘制石头--draw() --> onDraw（canvas） 根据上面两个方法参数，进入绘制
 */
public class MyToggleButton extends View implements View.OnClickListener {

    private Bitmap backgroundBitmap;
    private Bitmap slidingBitmap;
    /**
     * 距离左边最大值
     */
    private int slideLeftMax;
    private Paint paint;
    private boolean isOpen = false;
    private int slideLeft;


    /**
     * 继承view,这个方法一定要实现
     * 如果我们在布局文件使用该类，将会用这个构造方法实例该类，如果没有就崩溃
     *
     * @param context
     * @param attrs
     */
    public MyToggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true); //设置抗锯齿
        initView();
    }

    private void initView() {
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_tog_on_off);
        slidingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_tog_close);
        slideLeftMax = backgroundBitmap.getWidth() - slidingBitmap.getWidth();
        this.setOnClickListener(this);
    }


    /**
     * 视图测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(backgroundBitmap.getWidth(), backgroundBitmap.getHeight());
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
        canvas.drawBitmap(slidingBitmap, slideLeft, 0, paint);
    }

    @Override
    public void onClick(View v) {
        isOpen = !isOpen;
        if (isOpen){
            slideLeft = slideLeftMax;
        }else {
            slideLeft = 0;
        }
        invalidate(); // 会导致onDraw()执行
    }
}
