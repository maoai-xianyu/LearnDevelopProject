package com.mao.cn.learnDevelopProject.widgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.mao.cn.learnDevelopProject.R;

import java.util.Random;

/**
 * @author zhangkun
 * @time 2020-04-09 17:52
 */
public class LoveLayout extends RelativeLayout {


    private Context mContext;

    // 随机数
    private Random mRandom;
    private int[] mImageRes;

    // 控件的宽高
    private int mWidth;
    private int mHeight;

    private int mDrawableWidth;
    private int mDrawableHeight;

    private TimeInterpolator[] mTimeInterpolator;

    public LoveLayout(Context context) {
        this(context, null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.mRandom = new Random();
        mImageRes = new int[]{
                R.drawable.pl_red,
                R.drawable.pl_blue,
                R.drawable.pl_yellow,
        };

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.pl_red);
        mDrawableWidth = drawable.getIntrinsicWidth();
        mDrawableHeight = drawable.getIntrinsicHeight();

        mTimeInterpolator = new TimeInterpolator[]{
                new DecelerateInterpolator(),
                new AccelerateInterpolator(),
                new AccelerateDecelerateInterpolator(),
                new LinearInterpolator()

        };
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取控件的宽高
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    /**
     * 初始化心
     */
    public void addLove() {
        for (int i = 0; i < 10; i++) {
            ImageView loveImage = new ImageView(mContext);
            RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(ALIGN_PARENT_BOTTOM);
            params.addRule(CENTER_HORIZONTAL);
            loveImage.setLayoutParams(params);
            // 随机一个图片资源
            loveImage.setBackgroundResource(mImageRes[mRandom.nextInt(mImageRes.length - 1)]);

            addView(loveImage);

            // 添加效果，有放大和透明变化 (属性动画)
            AnimatorSet animator = getAnimator(loveImage);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    // 执行之后移除
                    removeView(loveImage);
                }
            });
            animator.start();
        }
    }


    public AnimatorSet getAnimator(ImageView iv) {
        // 路径动画
        AnimatorSet allAnimatorSet = new AnimatorSet();

        AnimatorSet animatorInnerSet = new AnimatorSet();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(iv, "alpha", 0.3f, 1.0f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(iv, "scaleX", 0.3f, 1.0f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(iv, "scaleY", 0.3f, 1.0f);

        animatorInnerSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        animatorInnerSet.setDuration(350);

        // 运动的路径
        allAnimatorSet.playSequentially(animatorInnerSet, getBezierAnimator(iv));


        return allAnimatorSet;

    }

    private Animator getBezierAnimator(ImageView iv) {
        // 起始点
        PointF point0 = new PointF(mWidth / 2 - mDrawableWidth / 2, mHeight - mDrawableHeight);
        // point1的y 值 大于 point2的y值
        // 控制点
        PointF point1 = getPointF(1);
        // 控制点
        PointF point2 = getPointF(2);
        // 结束点
        PointF point3 = new PointF(mRandom.nextInt(mWidth) - mDrawableWidth / 2, 0);  // point3 在屏幕的最上方 x 为控件宽度的随机数， y 为 0

        LoveTypeEvaluator loveTypeEvaluator = new LoveTypeEvaluator(point1, point2);

        //  ofFloat  第一个参数 loveTypeEvaluator  第二参数 p0  第三参数 p3
        ValueAnimator bezierAnimator = ObjectAnimator.ofObject(loveTypeEvaluator, point0, point3);
        bezierAnimator.setDuration(3000);
        bezierAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                iv.setX(pointF.x);
                iv.setY(pointF.y);
                // 透明度
                float animatedFraction = animation.getAnimatedFraction();
                iv.setAlpha(1f - animatedFraction + 0.2f);
            }
        });
        // 添加插值器
        bezierAnimator.setInterpolator(mTimeInterpolator[mRandom.nextInt(mTimeInterpolator.length - 1)]);
        return bezierAnimator;
    }

    private PointF getPointF(int index) {
        return new PointF(mRandom.nextInt(mWidth) - mDrawableWidth / 2,
                mRandom.nextInt(mHeight / 2) + (index - 1) * (mHeight / 2));
    }
}
