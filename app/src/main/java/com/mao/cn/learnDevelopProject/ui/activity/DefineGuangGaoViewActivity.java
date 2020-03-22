// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/28/2017 11:40 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.DensityUtil;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DefineGuangGaoViewActivity extends BaseActivity {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.llPointGroup)
    LinearLayout llPointGroup;

    private ArrayList<ImageView> imageViews;
    private int[] imageIds = {
            R.drawable.image_art,
            R.drawable.image_dafault,
            R.drawable.image_datong_design,
            R.drawable.image_datong_logo,
            R.drawable.image_name_sign
    };

    private int prePosition = 0;

    private String[] imageNames = {
            "艺术",
            "默认",
            "钓鱼",
            "大同",
            "名字"
    };

    // 是否已经拖拽
    private boolean isDragging = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = vp.getCurrentItem() + 1;
            vp.setCurrentItem(item);
            // 延时发消息
            handler.sendEmptyMessageDelayed(0, 4000);
        }
    };

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_guang_gao_view;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("广告效果");
        tvHeaderTitle.setVisibility(View.VISIBLE);
        imageViews = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imageViews.add(imageView);

            // 添加指示点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            // 在代码中设置的都是像素--- 问题，在所有的手机上都是8个像素。
            // 8px ----》dp dip
            int width = DensityUtil.dip2px(this, 8);
            ToastUtils.show("width" + width);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                params.leftMargin = width;
                point.setEnabled(false);
            }
            point.setLayoutParams(params);
            llPointGroup.addView(point);
        }

        vp.setAdapter(new ViewPagerAdapter());

        // 设置中间位置
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size(); // 要保证ImageViews的整数倍
        vp.setCurrentItem(item);

        tvTitle.setText(imageNames[prePosition]);
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * 当页面滚动了的时候回调
             * @param i 当前页面的位置
             * @param v 滑动页面的百分比
             * @param i1 在屏幕上滑动的像素
             */
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            /**
             * 当某个页面被选中了的时候回调
             * @param i 被选中页面的位置
             */
            @Override
            public void onPageSelected(int i) {
                int realPosition = i % imageViews.size();
                tvTitle.setText(imageNames[realPosition]);
                llPointGroup.getChildAt(prePosition).setEnabled(false);
                llPointGroup.getChildAt(realPosition).setEnabled(true);
                prePosition = realPosition;
            }

            /**
             * 当页面滚动状态的变化的时候回调
             * 静止 -> 滑动
             * 滑动 -> 静止
             * 静止 -> 拖拽
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    // 拖拽
                    LogU.d("SCROLL_STATE_DRAGGING 拖拽 --------------------");
                    isDragging = true;
                    handler.removeCallbacksAndMessages(null);

                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    // 滑动
                    LogU.d("SCROLL_STATE_SETTLING 滑动 --------------------");
                } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                    // 空闲
                    LogU.d("SCROLL_STATE_IDLE 空闲 --------------------");
                    isDragging = false;
                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(0, 4000);
                }
            }
        });

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            //return imageViews.size();
            // 无限滑动
            return Integer.MAX_VALUE;
        }

        /**
         * x相当于getView
         *
         * @param container vp
         * @param position  当前页面的显示
         * @return
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            int realPosition = position % imageViews.size();
            ImageView imageView = imageViews.get(realPosition);
            container.addView(imageView);
            LogU.d("instantiateItem =" + position + "imageView == " + imageView);

            // 触摸事件
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            LogU.d("ACTION_DOWN = 手指按下");
                            handler.removeCallbacksAndMessages(null);
                            // 按下
                            break;
                        case MotionEvent.ACTION_MOVE:
                            // 手指在控件上移动
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            // 取消
                            LogU.d("ACTION_CANCEL = 取消");
                            //handler.removeCallbacksAndMessages(null);
                            //handler.sendEmptyMessageDelayed(0, 4000);
                            break;
                        case MotionEvent.ACTION_UP:
                            // 手指离开
                            LogU.d("ACTION_DOWN = 手指离开");
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(0, 4000);
                            break;
                    }
                    // 处理touch事件 false ，让点击事件消费
                    return false;
                }
            });

            // 点击事件
            imageView.setTag(position);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogU.d("点击事件");
                    int position = (int) v.getTag() % imageViews.size();
                    String name = imageNames[position];
                    onTip("name = " + name);
                }
            });
            return imageView;
        }

        /**
         * 比较view和object是否是同一个实例
         *
         * @param view
         * @param o    instantiateItem 返回的结果
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        /**
         * 释放资源
         *
         * @param container viewpager
         * @param position  要释放的位置
         * @param object    要释放的页面
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            LogU.d("instantiateItem =" + position + "object == " + object);
            container.removeView((View) object);
        }

    }
}
