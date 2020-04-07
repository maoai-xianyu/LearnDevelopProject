package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;

/**
 * @author zhangkun
 * @time 2020-03-25 16:29
 */
public class DefineShapeView extends View {

    private Paint mPaint;
    private int mCircleColor = Color.BLUE;
    private int mSquareColor = Color.YELLOW;
    private int mTriangleColor = Color.RED;

    private Shape currentShape = Shape.Circle;

    private Path mPath; // 画三角形

    public DefineShapeView(Context context) {
        this(context, null);
    }

    public DefineShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefineShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DefineShapeView);

        mCircleColor = array.getColor(R.styleable.DefineShapeView_circleBackground, mCircleColor);
        mSquareColor = array.getColor(R.styleable.DefineShapeView_squareBackground, mSquareColor);
        mTriangleColor = array.getColor(R.styleable.DefineShapeView_triangleBackground, mTriangleColor);
        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mCircleColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (currentShape) {
            case Circle:
                mPaint.setColor(mCircleColor);
                int radius = getWidth() / 2;
                canvas.drawCircle(radius, radius, radius, mPaint);
                break;
            case Square:
                mPaint.setColor(mSquareColor);
                Rect rect = new Rect(0, 0, getWidth(), getHeight());
                canvas.drawRect(rect, mPaint);
                break;
            case Triangle:
                mPaint.setColor(mTriangleColor);
                if (mPath == null) {
                    // 画路径
                    mPath = new Path();
                    int a = getWidth() / 2;
                    /*// 等腰三角形
                    mPath.moveTo(a, 0);// 此点为多边形的起点
                    mPath.lineTo(getWidth(), getHeight());
                    mPath.lineTo(0, getHeight());
                    mPath.close(); // 使这些点构成封闭的多边形*/


                    // 等边三角形
                   /* mPath.moveTo(a, 0);// 此点为多边形的起点
                    mPath.lineTo(getWidth(), (float) ((getWidth() / 2)*Math.sqrt(3)));
                    mPath.lineTo(0,(float) ((getWidth() / 2)*Math.sqrt(3)));
                    mPath.close(); // 使这些点构成封闭的多边形*/


                    mPath.moveTo(a, 0);// 此点为多边形的起点
                    mPath.lineTo(getWidth(), (float) (getWidth() * Math.sin(Math.PI / 3)));
                    mPath.lineTo(0, (float) (getWidth() * Math.sin(Math.PI / 3)));
                    mPath.close(); // 使这些点构成封闭的多边
                }


                canvas.drawPath(mPath, mPaint);
                break;
        }
    }

    public void changeShape() {
        switch (currentShape) {
            case Circle:
                currentShape = Shape.Square;
                break;
            case Square:
                currentShape = Shape.Triangle;
                break;
            case Triangle:
                currentShape = Shape.Circle;
                break;
        }
        invalidate();
    }

    public enum Shape {
        Circle, Square, Triangle
    }

    // 获取当前的形状
    public Shape getCurrentShape() {
        return currentShape;
    }

}
