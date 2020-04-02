package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.StatusBarUtil;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.DefineScrollView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class MDStatusBarActivity extends BaseActivity {


    @BindView(R.id.rlHeader)
    RelativeLayout rlHeader;
    @BindView(R.id.ib_header_back)
    ImageButton ibBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.nsv)
    DefineScrollView nsv;
    @BindView(R.id.iv)
    ImageView iv;


    private int imageHeight;
    private int headerHeight;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_status_bar;
    }

    @Override
    public void initView() {
        // 设置状态栏
        //StatusBarUtil.setStatusBarColor(this, ContextCompat.getColor(this,R.color.colorPrimaryDarkNew));
        // 设置全屏
        StatusBarUtil.setStatusBarTranslucent(this);

        //QQ 效果:1. 不断监听ScrollView的滚动，判断当前滚动的位置和头部的ImageView比较计算背景的透明度
        // 2. 自定义Behavior

        tvHeaderTitle.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("QQ");

        rlHeader.getBackground().setAlpha(0);
        // 不断的监听滚动 判断当前滚动的位置和头部的ImageView比较计算背景的透明度

        iv.post(new Runnable() {
            @Override
            public void run() {
                imageHeight = iv.getMeasuredHeight();
            }
        });

        rlHeader.post(new Runnable() {
            @Override
            public void run() {
                headerHeight = rlHeader.getHeight();
            }
        });

    }

    @Override
    public void setListener() {
        RxView.clicks(ibBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));


        nsv.setListener(new DefineScrollView.DefineScrollChangeListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                // 获取图片的高度，根据当前滚动的位置，计算alpha的值
                if (imageHeight == 0 || headerHeight == 0) return;


                float alpha = (float) t / (imageHeight - headerHeight);
                if (alpha <= 0) {
                    alpha = 0;
                }
                if (alpha >= 1) {
                    alpha = 1;
                }

                rlHeader.getBackground().setAlpha((int) (alpha * 255));


            }
        });

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
