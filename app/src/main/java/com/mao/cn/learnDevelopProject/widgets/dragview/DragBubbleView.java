package com.mao.cn.learnDevelopProject.widgets.dragview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-04-08 17:43
 * <p>
 * QQ 气泡 ，贝塞尔曲线
 */
public class DragBubbleView extends View {

    // 两个圆的圆心
    private PointF mFixationPoint;
    private PointF mDragPoint;

    // 拖拽圆的半径
    private int mDragRadius = 10;
    private Paint mPaint;

    // 固定圆的初始半径
    private int mFixationRadiusMax = 7;
    private int mFixationRadiusMin = 3;
    private int mFixationRadius;
    private Bitmap mDragBitmap;

    public DragBubbleView(Context context) {
        this(context, null);
    }

    public DragBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragRadius = dip2px(mDragRadius);
        mFixationRadiusMax = dip2px(mFixationRadiusMax);
        mFixationRadiusMin = dip2px(mFixationRadiusMin);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
    }

    // 更新当前拖拽的位置
    public void updateDragPoint(float moveX, float moveY) {
        mDragPoint.x = moveX;
        mDragPoint.y = moveY;
        invalidate();
    }

    // 初始化位置
    public void initPoint(float x, float y) {
        mFixationPoint = new PointF(x, y);
        mDragPoint = new PointF(x, y);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mDragPoint == null || mFixationPoint == null) return;
        // 绘制两个圆
        // 绘制拖拽圆  还有一个是拖拽圆半径是不变的位置是跟随我手指移动
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint);
        // 绘制固定圆 有一个初始化大小，而且它的半径是随着两个圆的距离变得而变小，小到一定的程度不见了，就不绘制了
        // 计算两个点的距离
        double distance = getDistance(mDragPoint, mFixationPoint);
        LogU.d("distance  " + distance);
        mFixationRadius = (int) (mFixationRadiusMax - distance / 35);

        Path beisaierPath = getBeiSaierPath();

        if (beisaierPath != null) {
            // 小到一定的程度 就不画了
            canvas.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint);
            // 画贝塞尔曲线
            canvas.drawPath(beisaierPath, mPaint);
        }

        // 画图片，位置也是手指一动的位置，中心位置才是手指拖动的位置
        if (mDragBitmap != null && !mDragBitmap.isRecycled()) {
            canvas.drawBitmap(mDragBitmap, mDragPoint.x - mDragBitmap.getWidth() / 2, mDragPoint.y - mDragBitmap.getHeight() / 2, null);

        }

    }

    /**
     * 获取 贝塞尔的路径
     *
     * @return
     */
    private Path getBeiSaierPath() {
        double distance = getDistance(mDragPoint, mFixationPoint);
        LogU.d(" distance " + distance);
        mFixationRadius = (int) (mFixationRadiusMax - distance / 35);
        LogU.d(" mFixationRadius " + mFixationRadius);

        if (mFixationRadius < mFixationRadiusMin) {
            // 超过一定距离，贝塞尔和固定圆都不要画了
            return null;
        }
        Path beisaierPath = new Path();

        // 求角a
        // 求斜率

        float dy = mDragPoint.y - mFixationPoint.y;
        float dx = mDragPoint.x - mFixationPoint.x;
        float tanA = dy / dx;
        double arcTanA = Math.atan(tanA);

        // p0
        float p0x = (float) (mFixationPoint.x + mFixationRadius * Math.sin(arcTanA));
        float p0y = (float) (mFixationPoint.y - mFixationRadius * Math.cos(arcTanA));
        // p1
        float p1x = (float) (mDragPoint.x + mDragRadius * Math.sin(arcTanA));
        float p1y = (float) (mDragPoint.y - mDragRadius * Math.cos(arcTanA));

        // p2
        float p2x = (float) (mDragPoint.x - mDragRadius * Math.sin(arcTanA));
        float p2y = (float) (mDragPoint.y + mDragRadius * Math.cos(arcTanA));

        // p3
        float p3x = (float) (mFixationPoint.x - mFixationRadius * Math.sin(arcTanA));
        float p3y = (float) (mFixationPoint.y + mFixationRadius * Math.cos(arcTanA));
        // 拼接 贝塞尔的曲线路径
        beisaierPath.moveTo(p0x, p0y);
        // 两个点  第一个点(控制点,两个圆心的中心点)
        PointF controlPointF = getControlPoint();
        // 画了第一条
        beisaierPath.quadTo(controlPointF.x, controlPointF.y, p1x, p1y);
        // 画第二条
        beisaierPath.lineTo(p2x, p2y);
        beisaierPath.quadTo(controlPointF.x, controlPointF.y, p3x, p3y);
        beisaierPath.close();


        return beisaierPath;
    }

    private PointF getControlPoint() {
        // 设置 两个圆心连线的重点
        //return new PointF((mDragPoint.x + mFixationPoint.x) / 2, (mDragPoint.y + mFixationPoint.y) / 2);
        return new PointF(((mDragPoint.x - mFixationPoint.x) / 2 + mFixationPoint.x), (mDragPoint.y - mFixationPoint.y) / 2 + mFixationPoint.y);

    }

    private double getDistance(PointF dragPoint, PointF fixationPoint) {
        return Math.sqrt((dragPoint.x - fixationPoint.x) * (dragPoint.x - fixationPoint.x)
                + (dragPoint.y - fixationPoint.y) * (dragPoint.y - fixationPoint.y));

    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip,
                getResources().getDisplayMetrics());
    }

    /**
     * 绑定可以拖拽
     *
     * @param view
     * @param bubbleDisappearListener
     */
    public static void attach(View view, DragBubbleTouchListener.BubbleDisappearListener bubbleDisappearListener) {
        if (view == null) {
            throw new NullPointerException("view 不能为空");
        }
        view.setOnTouchListener(new DragBubbleTouchListener(view, view.getContext(), bubbleDisappearListener));
    }

    public void setDragBitmap(Bitmap bitmapByView) {
        this.mDragBitmap = bitmapByView;
    }

    /**
     * 处理手指松开
     */
    public void handleActionUp() {
        LogU.d("mFixationRadius " + mFixationRadius + " mFixationRadiusMin " + mFixationRadiusMin);
        if (mFixationRadius > mFixationRadiusMin) {
            // 回弹 ValueAnimator 值变化的动画  0 到  1
            ValueAnimator objectAnimator = ObjectAnimator.ofFloat(1.0f);
            objectAnimator.setDuration(350);
            PointF start = new PointF(mDragPoint.x, mDragPoint.y);
            PointF end = new PointF(mFixationPoint.x, mFixationPoint.y);
            objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float percent = animation.getAnimatedFraction(); // 0-1
                    PointF pointByPercent = BubbleUtils.getPointByPercent(start, end, percent);
                    // 更新当前的拖拽点
                    updateDragPoint(pointByPercent.x, pointByPercent.y);

                }
            });
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    // 还要通知 TouchListener 移除当前view
                    if (mBubbleListener != null) {
                        mBubbleListener.backPosition();
                    }

                }
            });


            // 回弹
            objectAnimator.setInterpolator(new OvershootInterpolator(3f));
            objectAnimator.start();


        } else {
            // 爆炸
            if (mBubbleListener != null) {
                mBubbleListener.dismissView(mDragPoint);
            }

        }
    }


    private BubbleListener mBubbleListener;

    public void setBubbleListener(BubbleListener bubbleListener) {
        mBubbleListener = bubbleListener;
    }

    public interface BubbleListener {
        // 还原
        public void backPosition();

        // 爆炸
        public void dismissView(PointF pointF);
    }


}
