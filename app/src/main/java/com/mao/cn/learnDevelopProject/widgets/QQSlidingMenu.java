package com.mao.cn.learnDevelopProject.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.ScreenUtils;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-03-30 11:12
 */
public class QQSlidingMenu extends HorizontalScrollView {

    private Context mContext;

    // 菜单的宽度
    private int mMenuWidth;

    // 手势处理类
    private GestureDetector mGestureDetector;
    // 打开还是关闭
    private boolean mMenuIsOpen = false;
    private View mChildContent;
    private View mChildMenu;
    private ImageView mShadeView;

    // 是否拦截
    private boolean mIsIntercept = false;

    public QQSlidingMenu(Context context) {
        this(context, null);
    }

    public QQSlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQSlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQSlidingMenu);
        float rightMargin = array.getDimension(
                R.styleable.QQSlidingMenu_qqmenuRightMargin, ScreenUtils.dip2px(mContext, 50));

        mMenuWidth = (int) (ScreenUtils.getScreenWidth(mContext) - rightMargin);
        array.recycle();

        mGestureDetector = new GestureDetector(context, mSimpleOnGestureListener);
    }


    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // 快速滑动
            LogU.d("velocityX  " + velocityX);
            // Bug  判断左右还是上下   只有左右快速滑动才切换
            if(Math.abs(velocityY) > Math.abs(velocityX)){
                // 代表上下快速划  这个时候不做处理
                return super.onFling(e1, e2, velocityX, velocityX);
            }
            // 快速往左边滑动的时候 是一个 负数  快速往右边滑动的时候 是一个正数
            if (mMenuIsOpen) {
                // 条件  打开的时候往左  边快速滑动
                if (velocityX < 0) {
                    closeMenu();
                    return true;
                }
            } else {
                // 条件  关闭的时候往右边快速滑动切换
                if (velocityX > 0) {
                    openMenu();
                    return true;
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };


    // 1. 宽度不对，指定宽高， onFinishInflate 是最早执行的 onCreate方法执行
    @Override
    protected void onFinishInflate() {
        // 布局加载完毕会回调，也就是 xml 布局文件解析完毕
        super.onFinishInflate();
        // 指定宽高 1. 内容页的宽度是屏幕的宽度
        // 获取linearLayout
        ViewGroup container = (ViewGroup) getChildAt(0);
        int childCount = container.getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("必须两个布局");
        }
        mChildMenu = container.getChildAt(0);
        // 设置宽高 只能通过 LayoutParams
        ViewGroup.LayoutParams menuParams = mChildMenu.getLayoutParams();
        menuParams.width = mMenuWidth;
        // 7.0 以下的手机必须采用下面的方式
        mChildMenu.setLayoutParams(menuParams);
        // 2. 菜单也的宽度是 屏幕的宽度-右边自定义的宽度
        // 布局单独提出来
        mChildContent = container.getChildAt(1);
        container.removeView(mChildContent);
        // 然后再外面套一层阴影
        FrameLayout contentContainer = new FrameLayout(getContext());
        contentContainer.addView(mChildContent);
        mShadeView = new ImageView(getContext());
        mShadeView.setBackgroundColor(Color.parseColor("#99000000"));
        contentContainer.addView(mShadeView);
        // 最后在把容器放回原来的位置
        container.addView(contentContainer);

        ViewGroup.LayoutParams contentParams = contentContainer.getLayoutParams();
        contentParams.width = ScreenUtils.getScreenWidth(mContext);
        // 7.0 以下的手机必须采用下面的方式
        contentContainer.setLayoutParams(contentParams);
        // 2. 初始化进来之后是关闭的，也就是要显示 主页，不显示侧滑的页面
        // scrollTo(mMenuWidth,0);
    }

    /**
     * measure 之后执行
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // 2. 初始化进来之后是关闭的，也就是要显示 主页，不显示侧滑的页面
        if(changed){
            // 4.3 默认关闭的状态 要让其自己滚动一段距离  菜单的宽度
            scrollTo(mMenuWidth,0);
        }
    }


    // 3. 手指抬起之后 二选一

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 拦截不执行 onTouch
        if (mIsIntercept) {
            return true;
        }

        // 获取手指滑动的速率，当大于一定的值就认为是快速滑动，GestureDetector(系统提供好的类)
        if (mGestureDetector.onTouchEvent(ev)) {
            return true;
        }// 快速滑动触发了，下面的不要执行了

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            // 手指抬起, 需要获取当前滚动的距离
            LogU.d("SlidingMenu  " + getScrollX());
            int currentScrollX = getScrollX();
            if (currentScrollX > mMenuWidth / 2) {
                // 关闭
                closeMenu();
            } else {
                // 打开
                openMenu();
            }
            //不需要 调用  super.onTouchEvent(ev);
            return true;

        }
        return super.onTouchEvent(ev);
    }


    public void toggleMenu() {
        if(mMenuIsOpen){
            closeMenu();
        }else{
            openMenu();
        }
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        // 有动画
        smoothScrollTo(mMenuWidth, 0);
        mMenuIsOpen = false;
    }

    /**
     * 打开菜单
     */
    private void openMenu() {
        smoothScrollTo(0, 0);
        mMenuIsOpen = true;
    }


    // 4. 处理右边的缩放 和 左边的缩放 和 透明度，需要不断的获取当前的滚动的位置
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        LogU.d("l " + l); // 变化是 mMenuWidth -> 0
        // 算一个t度值
        float scale = 1f * l / mMenuWidth; // scale 变化是 1 - 0
        // 右边的所犯 最小是 0.6f，最大的是 1f
        mShadeView.setAlpha(1 - scale);
        mChildMenu.setTranslationX(0.8f * l);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mIsIntercept = false;
        // 处理事件拦截 和 ViewGroup 的事件分发的源码实践
        // 当前菜单打开的时候，手指触摸右边内容部分需要关闭菜单，还需要拦截事件（打开情况下点击内容不会响应点击事件）
        if (mMenuIsOpen) {

            float currentX = ev.getX();
            if (currentX > mMenuWidth) {
                // 关闭
                closeMenu();
                // 子view 不需要响应任何事件（点击和触摸），拦截子view的事件
                mIsIntercept = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
