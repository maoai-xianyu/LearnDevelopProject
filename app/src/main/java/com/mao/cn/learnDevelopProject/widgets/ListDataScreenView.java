package com.mao.cn.learnDevelopProject.widgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.ui.adapter.define.BaseMenuAdapter;
import com.mao.cn.learnDevelopProject.ui.adapter.define.ListScreenMenuObserver;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2020-04-07 14:50
 */
public class ListDataScreenView extends LinearLayout {

    private Context mContext;
    private LinearLayout mMenuTabView;
    private FrameLayout mMenuMiddleView;
    private View mShadowView;
    // 阴影的颜色
    private int mShadowColor = 0x88888888;

    private FrameLayout mMenuContainerView;

    private BaseMenuAdapter mAdapter;
    // 内容的高度
    private int mMenuContainerHeight;

    private int mCurrentPosition = -1;

    private final int ANIMATOR_DURATION = 350;

    // 动画是否在执行
    private boolean mAnimatorExecute = false;

    public ListDataScreenView(Context context) {
        this(context, null);
    }

    public ListDataScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListDataScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initLayout();
    }

    /**
     * 实例化布局
     */
    private void initLayout() {
        /**
         * 1. 先创建一个 xml 布局，在加载，findViewById
         * 2. 简单的效果可以用代码实现
         */
        setOrientation(VERTICAL);
        // 创建头部用来存放tab
        mMenuTabView = new LinearLayout(mContext);
        mMenuTabView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mMenuTabView);
        // 创建 FrameLayout 用来存放 阴影（View） + 菜单内容（FrameLayout）
        mMenuMiddleView = new FrameLayout(mContext);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                0);
        params.weight = 1;
        mMenuMiddleView.setLayoutParams(params);
        addView(mMenuMiddleView);
        // 创建阴影 可以不用设置 LayoutParams  默认是  MATCH_PARENT
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(mShadowColor);
        mShadowView.setAlpha(0);
        mShadowView.setVisibility(GONE);
        mShadowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
        mMenuMiddleView.addView(mShadowView);
        // 创建菜单，存放内容
        mMenuContainerView = new FrameLayout(mContext);
        mMenuContainerView.setBackgroundColor(Color.WHITE);
        mMenuContainerView.setClickable(true);
        mMenuMiddleView.addView(mMenuContainerView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogU.d("onMeasure  -->>>>");
        if (mMenuContainerHeight == 0) {
            // 内容的高度，是整个View的 75%
            int height = MeasureSpec.getSize(heightMeasureSpec);
            mMenuContainerHeight = (int) (height * 3f / 4);
            ViewGroup.LayoutParams layoutParams = mMenuContainerView.getLayoutParams();
            layoutParams.height = mMenuContainerHeight;
            mMenuContainerView.setLayoutParams(layoutParams);
            // 进来的时候阴影不显示，内容也不显示
            mMenuContainerView.setTranslationY(-mMenuContainerHeight);
        }
    }

    /**
     * 具体的观察者类对象
     */
    private class ListScreenMenuAdapterObserver extends ListScreenMenuObserver {
        @Override
        public void closeScreenMenu() {
            // 如果有注册就会有通知
            closeMenu();
        }
    }

    private ListScreenMenuAdapterObserver mListScreenMenuAdapterObserver;

    /**
     * 设置adapter
     *
     * @param baseMenuAdapter
     */
    public void setAdapter(BaseMenuAdapter baseMenuAdapter) {

        // 观察者 微信公众号用户
        if (baseMenuAdapter == null) {
            throw new NullPointerException("不能为空");
        }
        if (mAdapter != null && mListScreenMenuAdapterObserver != null) {
            // 取消监听
            mAdapter.unregisterDataSetObserver(mListScreenMenuAdapterObserver);
        }

        this.mAdapter = baseMenuAdapter;

        mListScreenMenuAdapterObserver = new ListScreenMenuAdapterObserver();
        // 注册观察者 具体的观察者实例对象 订阅
        this.mAdapter.registerDataSetObserver(mListScreenMenuAdapterObserver);

        // 获取有多少条数据
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            // 获取菜单的Tab
            View tabView = mAdapter.getTabView(i, mMenuTabView);
            mMenuTabView.addView(tabView);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) tabView.getLayoutParams();
            layoutParams.weight = 1;
            tabView.setLayoutParams(layoutParams);
            // 设置点击事件
            setTabClick(tabView, i);
            // 获取菜单的内容
            View menuView = mAdapter.getMenuView(i, mMenuContainerView);
            menuView.setVisibility(GONE);
            mMenuContainerView.addView(menuView);

        }
    }

    /**
     * 设置点击事件
     *
     * @param tabView
     * @param position
     */
    private void setTabClick(View tabView, int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPosition == -1) {
                    // 没有打开
                    openMenu(position, tabView);
                } else {
                    if (mCurrentPosition == position) {
                        // 打开
                        closeMenu();
                    } else {
                        // 切换显示
                        View currentMenu = mMenuContainerView.getChildAt(mCurrentPosition);
                        currentMenu.setVisibility(GONE);
                        mAdapter.menuClose(mMenuTabView.getChildAt(mCurrentPosition));
                        mCurrentPosition = position;
                        mAdapter.menuOpen(mMenuTabView.getChildAt(mCurrentPosition));
                        LogU.d("setTabClick  -->>>> mCurrentPosition");
                        currentMenu = mMenuContainerView.getChildAt(mCurrentPosition);
                        currentMenu.setVisibility(VISIBLE);
                    }

                }

            }
        });

    }


    private void openMenu(int position, View tabView) {
        if (mAnimatorExecute) return;
        // 打开
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", -mMenuContainerHeight, 0);
        translationAnimator.setDuration(ANIMATOR_DURATION);

        mShadowView.setVisibility(VISIBLE);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 0f, 1f);
        alphaAnimator.setDuration(ANIMATOR_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnimator, alphaAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorExecute = false;
                mCurrentPosition = position;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mAnimatorExecute = true;
                // 把当期的Tab 传到外面，用适配器
                mAdapter.menuOpen(tabView);
            }
        });

        animatorSet.start();

        // 获取当前位置，显示当前菜单

        View childAt = mMenuContainerView.getChildAt(position);
        childAt.setVisibility(VISIBLE);


    }

    public void closeMenu() {
        if (mAnimatorExecute) return;
        // 关闭
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", 0, -mMenuContainerHeight);
        translationAnimator.setDuration(ANIMATOR_DURATION);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 1f, 0f);
        alphaAnimator.setDuration(ANIMATOR_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnimator, alphaAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mShadowView.setVisibility(GONE);
                View childAt = mMenuContainerView.getChildAt(mCurrentPosition);
                childAt.setVisibility(GONE);
                mCurrentPosition = -1;
                mAnimatorExecute = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mAnimatorExecute = true;
                mAdapter.menuClose(mMenuTabView.getChildAt(mCurrentPosition));
            }

        });
        animatorSet.start();
    }


}
