package com.mao.cn.learnDevelopProject.widgets.dragview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.StatusBarUtil;

/**
 * @author zhangkun
 * @time 2020-04-09 09:56
 * 监听当前view的触摸事件
 */
public class DragBubbleTouchListener implements View.OnTouchListener, DragBubbleView.BubbleListener {

    // 原来需要拖动爆炸的View
    private View mStaticView;
    private DragBubbleView mDragBubbleView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private Context mContext;

    private FrameLayout mBombFrame;
    private ImageView mImageView;
    private BubbleDisappearListener bubbleDisappearListener;

    public DragBubbleTouchListener(View staticView, Context context, BubbleDisappearListener bubbleDisappearListener) {
        mContext = context;
        mStaticView = staticView;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDragBubbleView = new DragBubbleView(context);
        mLayoutParams = new WindowManager.LayoutParams();
        // 背景要透明
        mLayoutParams.format = PixelFormat.TRANSPARENT;

        mDragBubbleView.setBubbleListener(this);

        mBombFrame = new FrameLayout(context);
        mImageView = new ImageView(context);
        mImageView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mBombFrame.addView(mImageView);

        this.bubbleDisappearListener = bubbleDisappearListener;

    }

    // 有两个圆，一个是固定圆位置固定不动但是半径会变化（两个圆之间的距离越远半径就越小） 还有一个是拖拽圆半径是不变的位置是跟随我手指移动

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 要在WindowManage上面搞一个View,贝塞尔View
                mWindowManager.addView(mDragBubbleView, mLayoutParams);
                // getX() 获取相对父容器的位置，  getRawX() 获取屏幕的位置
                float downX = event.getRawX();
                float downY = event.getRawY();
                //mDragBubbleView.initPoint(downX, downY - StatusBarUtil.getStatusBarHeight(mContext));
                // 初始化贝塞尔View的点
                // 保证固定圆的中心在View的中心
                int[] location = new int[2];
                mStaticView.getLocationOnScreen(location);
                Bitmap bitmapByView = getBitmapByView(mStaticView);
                mDragBubbleView.initPoint(location[0] + bitmapByView.getWidth() / 2, (location[1] + bitmapByView.getHeight() / 2) - StatusBarUtil.getStatusBarHeight(mContext));
                // 给消息拖拽设置一个BitMap
                mDragBubbleView.setDragBitmap(bitmapByView);
                // 首先把自己隐藏
                mStaticView.setVisibility(View.INVISIBLE);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getRawX();
                float moveY = event.getRawY();
                mDragBubbleView.updateDragPoint(moveX, moveY - StatusBarUtil.getStatusBarHeight(mContext));
                break;
            case MotionEvent.ACTION_UP:
                mDragBubbleView.handleActionUp();
                break;
        }
        return true;
    }


    /**
     * 如何从一个view 获取一个bitmap
     *
     * @param view
     * @return
     */
    private Bitmap getBitmapByView(View view) {
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @Override
    public void backPosition() {
        // 把消息的view移除
        mWindowManager.removeView(mDragBubbleView);
        // 吧原来的view先死
        mStaticView.setVisibility(View.VISIBLE);

    }

    @Override
    public void dismissView(PointF pointF) {
        // 执行爆炸动画（帧动画 ）
        // 把消息的view移除
        mWindowManager.removeView(mDragBubbleView);
        // 原来的view 移除 消息
        mStaticView.setVisibility(View.GONE);
        // 要在 mWindowManager 添加一个爆炸动画
        mWindowManager.addView(mBombFrame, mLayoutParams);
        mImageView.setBackgroundResource(R.drawable.anim_bubble_pop);

        AnimationDrawable drawable = (AnimationDrawable) mImageView.getBackground();
        mImageView.setX(pointF.x - drawable.getIntrinsicWidth() / 2);
        mImageView.setY(pointF.y - drawable.getIntrinsicHeight() / 2);
        drawable.start();
        // 等它执行完之后移除这个爆炸动画
        mImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWindowManager.removeView(mBombFrame);
                // 通知外面改view消失
                if (bubbleDisappearListener != null) {
                    bubbleDisappearListener.dismiss(mStaticView);
                }
            }
        }, getAnimationDrawable(drawable));

    }

    private long getAnimationDrawable(AnimationDrawable drawable) {
        int numberOfFrames = drawable.getNumberOfFrames();
        long time = 0;
        for (int i = 0; i < numberOfFrames; i++) {
            time += drawable.getDuration(i);
        }
        return time;
    }


    public interface BubbleDisappearListener {
        void dismiss(View view);

    }
}
