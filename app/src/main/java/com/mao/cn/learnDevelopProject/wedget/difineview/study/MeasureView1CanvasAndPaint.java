package com.mao.cn.learnDevelopProject.wedget.difineview.study;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;

/**
 * author:  zhangkun .
 * date:    on 2017/9/28.
 * <p>
 * Canvas就是画布，Paint就是画笔
 * <p>
 * Canvas就是画布，Paint就是画笔
 * <p>
 * Canvas就是画布，Paint就是画笔
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
    private Paint paint_text = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint_2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Context context;


    public MeasureView1CanvasAndPaint(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    public MeasureView1CanvasAndPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    public MeasureView1CanvasAndPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initPaint();
    }

    private void initPaint() {
        paint.setColor(Color.parseColor("#FF4081"));
        paint_text.setColor(Color.parseColor("#f4c41f"));
        paint_text.setTextSize(45f);
        paint_text.setTextSkewX(-1f);
        paint_text.setFakeBoldText(true);
        paint_2.setColor(Color.parseColor("#E4AF0A"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left = getLeft();
        float right = getRight();
        float top = getTop();
        float bottom = getBottom();
        // 方形
        //canvas.drawRect(left,top,right,bottom,paint);

        // 画圆形
        float cx = getWidth();
        float cy = getHeight();
        float radius = Math.min(cx, cy) / 2;
        //canvas.drawCircle(cx / 2, cy / 2, radius, paint);

        // 扇形
        RectF rectF = new RectF(0F, 0F, 500F, 500F);
        //canvas.drawArc(rectF, 0, 60, true, paint);
        //canvas.drawArc(rectF, 60, 60, true, paint_2);

        // 绘制图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        float width = (getWidth() - bitmap.getWidth()) / 2;
        float height = (getHeight() - bitmap.getHeight()) / 2;
        //canvas.drawBitmap(bitmap, width, height, paint);

        // 绘制字
        canvas.drawText("hello world",100,100,paint_text);

        // 绘制路径
        Path path = new Path();
        // 起点
        path.moveTo(200,200);
        // 连接的点
        path.lineTo(300,50);
        path.lineTo(400,100);
        path.lineTo(100,400);
        canvas.drawPath(path,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measuredHeight(heightMeasureSpec));
    }

    /**
     * 测量宽
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
