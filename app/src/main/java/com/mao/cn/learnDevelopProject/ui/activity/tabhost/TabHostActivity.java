package com.mao.cn.learnDevelopProject.ui.activity.tabhost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class TabHostActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;
    @BindView(R.id.tab)
    MyFragmentTabHost fragmentTabHost;


    private String[] tabs = new String[]{"首页", "电影", "视频", "演出", "我的"};
    private Class[] mFragmentClasses = new Class[]{MovieMainFragment.class, MovieListFragment.class,
            MoiveVideoFragment.class, MovieShowFragment.class, MineMineFragment.class};
    private int[] selectorImg = new int[]{R.drawable.tab_main_icon, R.drawable.tab_movie_icon,
            R.drawable.tab_video_icon, R.drawable.tab_concert_icon, R.drawable.tab_mine_icon};

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_tab_host;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setText("lottie");

        // 初始化tabHost
        fragmentTabHost.setup(TabHostActivity.this, getSupportFragmentManager(), R.id.fragment_content);

        for (int i = 0; i < 5; i++) {
            fragmentTabHost.addTab(fragmentTabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i)), mFragmentClasses[i], null);
        }

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

    /**
     * tab的view对象
     *
     * @param index 索引
     * @return view对象
     */
    private View getTabView(int index) {
        View inflate = LayoutInflater.from(TabHostActivity.this).inflate(R.layout.item_tab, null);
        ImageView tabImage = inflate.findViewById(R.id.tab_image);
        TextView tabTitle = inflate.findViewById(R.id.tab_title);
        tabImage.setImageResource(selectorImg[index]); // 通过selector来控制图片的改变
        tabTitle.setText(tabs[index]);// 通过selector来控制文字颜色的改变
        return inflate;
    }

}
