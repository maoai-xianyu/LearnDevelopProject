package com.mao.cn.learnDevelopProject.widgets.parallaxAnimation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.ParallaxTag;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.ParallaxViewFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-04-10 17:36
 */
public class ParallaxViewPager extends ViewPager {

    private List<Fragment> mBaseFragments;

    public ParallaxViewPager(@NonNull Context context) {
        this(context, null);
    }

    public ParallaxViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBaseFragments = new ArrayList<>();
    }

    /**
     * 设置布局
     *
     * @param layoutIds
     */
    public void attach(FragmentManager fm, int[] layoutIds) {
        mBaseFragments.clear();
        // 实例化fragment
        for (int i = 0; i < layoutIds.length; i++) {
            ParallaxViewFragment parallaxViewFragment = ParallaxViewFragment.newInstance(layoutIds[i]);
            mBaseFragments.add(parallaxViewFragment);
        }
        // 设置viewpager adapter

        setAdapter(new ParallaxPagerAdapter(fm));
        // 监听滑动滚动改变位移
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 滚动 position 当前位置  positionOffset  0 -1  positionOffsetPixels 0- 屏幕的宽度 px
                LogU.d("position ->" + position + " positionOffset " + positionOffset + " positionOffsetPixels " + positionOffsetPixels);
                // 获取左 out  右 in

                ParallaxViewFragment outFragment = (ParallaxViewFragment) mBaseFragments.get(position);
                List<View> parallaxViews = outFragment.getParallaxViews();
                for (View parallaxView : parallaxViews) {
                    ParallaxTag tag = (ParallaxTag) parallaxView.getTag(R.id.parallax_tag);
                    // 算法
                    parallaxView.setTranslationX((-positionOffsetPixels) * tag.translationXOut);
                    parallaxView.setTranslationY((-positionOffsetPixels) * tag.translationYOut);
                }


                try {
                    ParallaxViewFragment inFragment = (ParallaxViewFragment) mBaseFragments.get(position + 1);
                    parallaxViews = inFragment.getParallaxViews();
                    for (View parallaxView : parallaxViews) {
                        ParallaxTag tag = (ParallaxTag) parallaxView.getTag(R.id.parallax_tag);
                        parallaxView.setTranslationX((getMeasuredWidth() - positionOffsetPixels) * tag.translationXIn);
                        parallaxView.setTranslationY((getMeasuredWidth() - positionOffsetPixels) * tag.translationYIn);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onPageSelected(int position) {
                // 选择切换完毕

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private class ParallaxPagerAdapter extends FragmentStatePagerAdapter {


        public ParallaxPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mBaseFragments.get(position);
        }

        @Override
        public int getCount() {
            return mBaseFragments.size();
        }
    }
}
