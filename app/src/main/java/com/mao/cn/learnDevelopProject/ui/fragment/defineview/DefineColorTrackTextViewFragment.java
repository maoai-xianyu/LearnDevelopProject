package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.ColorTrackTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;

public class DefineColorTrackTextViewFragment extends BaseFragment {


    @BindView(R.id.btnLeft)
    Button btnLeft;

    @BindView(R.id.btnRight)
    Button btnRight;
    @BindView(R.id.colorTrackTextView)
    ColorTrackTextView colorTrackTextView;


    @BindView(R.id.llV)
    LinearLayout mIndicatorContainer;
    @BindView(R.id.vP)
    ViewPager mViewPager;

    private String[] items = {"直播", "推荐", "视频", "图片", "段子", "精华"};
    // 变成通用的
    private List<ColorTrackTextView> mIndicators;


    public static DefineColorTrackTextViewFragment newInstance() {
        DefineColorTrackTextViewFragment fragment = new DefineColorTrackTextViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_color_track_text_view;
    }

    @Override
    public void initView() {
        mIndicators = new ArrayList<>();
        initIndicator();
        initViewPager();
    }


    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public Fragment getItem(int position) {
                return DefineColorTrackTextViewItemPagerFragment.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogU.e("position -> " + position + "  positionOffset -> " + positionOffset);
                // position 代表当前的位置
                // positionOffset 代表滚动的 0 - 1 百分比

                // 1.左边  位置 position
                ColorTrackTextView left = mIndicators.get(position);
                left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                left.setCurrentProgress(1 - positionOffset);
                try {
                    ColorTrackTextView right = mIndicators.get(position + 1);
                    right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                    right.setCurrentProgress(positionOffset);
                } catch (Exception e) {

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化可变色的指示器
     */
    private void initIndicator() {
        for (int i = 0; i < items.length; i++) {
            // 动态添加颜色跟踪的TextView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(context);
            // 设置颜色
            colorTrackTextView.setTextSize(20);
            colorTrackTextView.setChangeColor(Color.RED);
            colorTrackTextView.setText(items[i]);
            colorTrackTextView.setLayoutParams(params);
            // 把新的加入LinearLayout容器
            mIndicatorContainer.addView(colorTrackTextView);
            // 加入集合
            mIndicators.add(colorTrackTextView);
        }
    }

    @Override
    public void setListener() {

        RxView.clicks(btnLeft).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                colorTrackTextView.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                changeDirection(false);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogU.e(throwable.getMessage());
            }
        });

        RxView.clicks(btnRight).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                colorTrackTextView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                changeDirection(true);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogU.e(throwable.getMessage());
            }
        });

    }


    private void changeDirection(boolean changeRight) {

        ValueAnimator animator = ObjectAnimator.ofFloat(0, 1);

        animator.setDuration(3000);
        animator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();

            colorTrackTextView.setCurrentProgress(animatedValue);

        });
        animator.start();
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
