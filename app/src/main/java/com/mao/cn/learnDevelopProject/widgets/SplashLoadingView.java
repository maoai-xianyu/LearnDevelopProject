package com.mao.cn.learnDevelopProject.widgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;

/**
 * @author zhangkun
 * @time 2020-04-12 22:56
 */
public class SplashLoadingView extends View {

    // 旋转动画执行的时间
    private final long ROTATION_ANIMATION_TIME = 1400;
    // 当前大圆旋转的角度（弧度）
    private float mCurrentRotationAngle = 0F;
    // 用于显示圆的颜色
    private int[] mCircleColors;
    // 大圆里面包含很多小圆的半径 - 整宽度的 1/4
    private float mRotationRadius;
    // 每个小圆的半径 - 大圆半径的 1/8
    private float mCircleRadius;
    // 绘制所有效果的画笔
    private Paint mPaint;
    // 中心点
    private int mCenterX, mCenterY;
    // 整体的颜色背景
    private int mSplashColor = Color.WHITE;
    // 初始化一次
    private boolean mInitParams = false;

    private LoadingState mLoadingState;

    // 当前大圆的半径
    private float mCurrentRotationRadius = mRotationRadius;
    // 空心圆初始半径
    private float mHoleRadius = 0F;
    // 屏幕对角线的一半
    private float mDiagonalDist;


    public SplashLoadingView(Context context) {
        this(context, null);
    }

    public SplashLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCircleColors = context.getResources().getIntArray(R.array.splash_circle_colors);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!mInitParams) {
            int measuredWidth = getMeasuredWidth();
            mCenterX = measuredWidth / 2;
            mCenterY = getMeasuredHeight() / 2;
            mRotationRadius = measuredWidth / 4;
            mCircleRadius = mRotationRadius / 8;
            mDiagonalDist = (float) Math.sqrt(mCenterX * mCenterX + mCenterY * mCenterY);
            mInitParams = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mLoadingState == null) {
            mLoadingState = new RotationState();


        } else {

        }

        mLoadingState.draw(canvas);


    }


    public void disappear() {
        // 开始聚合动画
        if (mLoadingState instanceof RotationState) {
            RotationState rotationState = (RotationState) mLoadingState;
            rotationState.cancel();
        }
        mLoadingState = new MergeState();

    }

    public abstract class LoadingState {
        public abstract void draw(Canvas canvas);
    }


    /**
     * 旋转
     */
    public class RotationState extends LoadingState {
        private ValueAnimator mRotation;

        public RotationState() {
            // 属性动画
            mRotation = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));

            mRotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // 获取角度，绘制圆
                    mCurrentRotationAngle = (float) animation.getAnimatedValue();
                    // 不断绘制
                    invalidate();
                }
            });

            mRotation.setRepeatCount(-1);
            mRotation.setDuration(ROTATION_ANIMATION_TIME);
            // 线性变换
            mRotation.setInterpolator(new LinearInterpolator());
            mRotation.start();

        }

        @Override
        public void draw(Canvas canvas) {


            // 画一个背景 白色
            canvas.drawColor(mSplashColor);

            // 画六个圆  每份角度
            double percentAngle = Math.PI * 2 / mCircleColors.length;

            for (int i = 0; i < mCircleColors.length; i++) {
                mPaint.setColor(mCircleColors[i]);
                double currentAngle = percentAngle * i + mCurrentRotationAngle;
                int cx = (int) (mCenterX + Math.cos(currentAngle) * mRotationRadius);
                int cy = (int) (mCenterY + Math.sin(currentAngle) * mRotationRadius);
                canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
            }
        }

        public void cancel() {
            mRotation.cancel();
        }
    }


    /**
     * 聚合
     */
    public class MergeState extends LoadingState {


        private ValueAnimator mAnimator;

        public MergeState() {

            // 属性动画
            mAnimator = ValueAnimator.ofFloat(mRotationRadius, 0);
            mAnimator.setDuration(ROTATION_ANIMATION_TIME / 2);
            mAnimator.setInterpolator(new AnticipateInterpolator(3f));
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotationRadius = (float) animation.getAnimatedValue();
                    invalidate();

                }
            });
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoadingState = new ExpendState();
                }
            });
            mAnimator.start();

        }

        @Override
        public void draw(Canvas canvas) {
            // 写聚合的动画
            // 画六个圆  每份角度
            double percentAngle = Math.PI * 2 / mCircleColors.length;

            for (int i = 0; i < mCircleColors.length; i++) {
                mPaint.setColor(mCircleColors[i]);
                double currentAngle = percentAngle * i + mCurrentRotationAngle;
                int cx = (int) (mCenterX + Math.cos(currentAngle) * mCurrentRotationRadius);
                int cy = (int) (mCenterY + Math.sin(currentAngle) * mCurrentRotationRadius);
                canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
            }

        }
    }

    /**
     * 展开
     */
    public class ExpendState extends LoadingState {


        private ValueAnimator mAnimator;

        public ExpendState() {

            // 属性动画
            mAnimator = ValueAnimator.ofFloat(0, mDiagonalDist);
            mAnimator.setDuration(ROTATION_ANIMATION_TIME / 2);
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mHoleRadius = (float) animation.getAnimatedValue();
                    invalidate();

                }
            });
            mAnimator.start();

        }

        @Override
        public void draw(Canvas canvas) {
            float stokeWidth = mDiagonalDist - mHoleRadius;
            mPaint.setStrokeWidth(stokeWidth);
            mPaint.setColor(mSplashColor);
            mPaint.setStyle(Paint.Style.STROKE);
            float radius = stokeWidth / 2 + mHoleRadius;
            canvas.drawCircle(mCenterX, mCenterY, radius, mPaint);

        }
    }
}
