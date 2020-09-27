package com.mao.cn.learnDevelopProject.ui.activity.tabhost;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.fragment.MineMineFragment;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.fragment.MoiveVideoFragment;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.fragment.MovieListFragment;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.fragment.MovieMainFragment;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.fragment.MovieShowFragment;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class TabHostMaoyanActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;
    @BindView(android.R.id.tabhost)
    FragmentTabHost fragmentTabHost;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_tab_host_maoyan;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setText("lottie");

        // 初始化tabHost
        fragmentTabHost.setup(TabHostMaoyanActivity.this, getSupportFragmentManager(), R.id.fl_real_content);

        addView();
        // 设置默认tab
        fragmentTabHost.setCurrentTab(2);

    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }


    private static String[] TAB_TAG;
    private static String lastSelectedTag;
    public static final int TAB_MAIN = 0;

    private void addView() {
        TAB_TAG = getResources().getStringArray(R.array.movie_tab_tags);
        lastSelectedTag = TAB_TAG[TAB_MAIN];
        int[] tabLayouts = {R.layout.tabitem_main, R.layout.tabitem_movie, R.layout.tabitem_video,
                R.layout.tabitem_concert, R.layout.tabitem_mine};
        Class[] mFragmentClasses = new Class[]{MovieMainFragment.class, MovieListFragment.class,
                MoiveVideoFragment.class, MovieShowFragment.class, MineMineFragment.class};

        if (TAB_TAG.length != mFragmentClasses.length || TAB_TAG.length != tabLayouts.length) {
            throw new IllegalStateException("tab数与相关数据项不对应");
        }
        LayoutInflater inflater = LayoutInflater.from(this);
        TabWidget tabWidget = fragmentTabHost.getTabWidget();
        for (int i = 0; i < TAB_TAG.length; i++) {
            fragmentTabHost.addTab(fragmentTabHost.newTabSpec(TAB_TAG[i])
                            .setIndicator(inflater.inflate(tabLayouts[i], tabWidget, false)),
                    mFragmentClasses[i], null);
        }

        int tabWidgetChildCount = tabWidget.getChildCount();
        for (int i = 0; i < tabWidgetChildCount; i++) {
            View view = tabWidget.getChildAt(i);
            view.setTag(R.id.movie_tab_item_id, TAB_TAG[i]);
            view.setOnClickListener(new TabClickListener(fragmentTabHost));
        }
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
    }

    /**
     * 底部item的点击事件
     */
    static class TabClickListener implements View.OnClickListener {

        private WeakReference<FragmentTabHost> reference;
        private AnimatorSet animSet;

        public TabClickListener(FragmentTabHost fragmentTabHost) {
            this.reference = new WeakReference<>(fragmentTabHost);
        }

        @Override
        public void onClick(View v) {
            String tag = (String) v.getTag(R.id.movie_tab_item_id);
            FragmentTabHost fragmentTabHost = reference.get();
            if (!TextUtils.isEmpty(tag) && fragmentTabHost != null && ViewCompat
                    .isAttachedToWindow(fragmentTabHost)) {
                if (!fragmentTabHost.getCurrentTabTag().equals(tag)) {
                    fragmentTabHost.setCurrentTabByTag(tag);
                } else {

                }

                //如果是首页，对首页选中后的icon做动效
                cancelScaleAnimator(animSet);//排除重复动画
                if (TAB_TAG[TAB_MAIN].equals(tag) && !TAB_TAG[TAB_MAIN]
                        .equals(lastSelectedTag)) {
                    //增加一重判断，排除二次重复点击释放动效
                    View mainTabIv = v.findViewById(R.id.tab_iv_main);
                    animSet = scaleAnimator(mainTabIv);
                }

                lastSelectedTag = tag;
            }
        }

        /**
         * 对目标view做从0da01.3倍，再还原为1的动效
         */
        private AnimatorSet scaleAnimator(View targetView) {
            if (null != targetView) {
                ObjectAnimator animX = ObjectAnimator.ofFloat(targetView, "scaleX", 0.1f, 1.3f, 1f);
                ObjectAnimator animY = ObjectAnimator.ofFloat(targetView, "scaleY", 0.1f, 1.3f, 1f);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animX, animY);
                animatorSet.setDuration(400);
                if (animatorSet != null && !animatorSet.isRunning()) {
                    animatorSet.start();
                }
                return animatorSet;
            }
            return null;
        }

        /**
         * 清除目标动画
         */
        private void cancelScaleAnimator(Animator animator) {
            if (animator != null && animator.isRunning()) {
                animator.cancel();
            }
        }
    }

}
