package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineCircleProgressFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineColorTrackTextViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineDragBubbleViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineLetterSideBarFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineListDataScreenViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineLoadingCircleViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineLoadingViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineQQStepViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineRatingBarFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineTagLayoutViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineTextViewFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.defineview.DefineTouchViewAndViewGroupFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class DefineViewDemoActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;


    @BindView(R.id.mDT1)
    TextView mDT1;
    @BindView(R.id.mDT2)
    TextView mDT2;
    @BindView(R.id.mDT3)
    TextView mDT3;
    @BindView(R.id.mDT4)
    TextView mDT4;
    @BindView(R.id.mDT5)
    TextView mDT5;
    @BindView(R.id.mDT6)
    TextView mDT6;
    @BindView(R.id.mDT7)
    TextView mDT7;
    @BindView(R.id.mDT8)
    TextView mDT8;
    @BindView(R.id.mDT9)
    TextView mDT9;
    @BindView(R.id.mDT10)
    TextView mDT10;
    @BindView(R.id.mDT11)
    TextView mDT11;
    @BindView(R.id.mDT12)
    TextView mDT12;
    @BindView(R.id.mDT13)
    TextView mDT13;
    @BindView(R.id.mDT14)
    TextView mDT14;
    @BindView(R.id.mDT15)
    TextView mDT15;
    @BindView(R.id.mDT16)
    TextView mDT16;
    @BindView(R.id.mDT17)
    TextView mDT17;
    @BindView(R.id.mDT18)
    TextView mDT18;
    @BindView(R.id.mDT19)
    TextView mDT19;
    @BindView(R.id.mDT20)
    TextView mDT20;


    @BindView(R.id.mDT30)
    TextView mDT30;
    @BindView(R.id.mDT31)
    TextView mDT31;
    @BindView(R.id.mDT32)
    TextView mDT32;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_view_demo;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setText("自定义view");


    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));

        RxView.clicks(mDT1).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineTextViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT2).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineQQStepViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT3).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineColorTrackTextViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT4).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineCircleProgressFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT5).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineRatingBarFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT6).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineLetterSideBarFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(mDT7).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineTagLayoutViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT8).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineTouchViewAndViewGroupFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT9).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(KuGouDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT10).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(QQDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT11).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(DragDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT12).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(LockDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT13).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(MDDemoActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT14).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(MDStatusBarActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT15).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(MDBehaviorActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT16).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(MDRecyclerViewActivity.class);
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT17).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent, DefineLoadingViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT18).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent,  DefineListDataScreenViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT19).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent,  DefineLoadingCircleViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(mDT20).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rlContent,  DefineDragBubbleViewFragment.newInstance())
                    .commitAllowingStateLoss();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogU.d("DefineViewDemoActivity ----onTouchEvent ");
        return super.onTouchEvent(event);
    }
}
