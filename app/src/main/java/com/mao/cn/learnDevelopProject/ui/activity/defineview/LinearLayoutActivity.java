package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class LinearLayoutActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;
    @BindView(R.id.llAdd)
    LinearLayout tabsContainer;

    @BindView(R.id.llAdd1)
    LinearLayout tabsContainer1;
    @BindView(R.id.llAdd2)
    LinearLayout tabsContainer2;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    private LinearLayout.LayoutParams expandedTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams1;
    private LinearLayout.LayoutParams expandedTabLayoutParams2;
    private LinearLayout.LayoutParams expandedTabLayoutParams3;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_linearlayput_add;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setText("添加");
        expandedTabLayoutParams = new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 1.0f);
        expandedTabLayoutParams1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        expandedTabLayoutParams1.gravity = Gravity.CENTER;
        expandedTabLayoutParams2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        expandedTabLayoutParams3 = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);

        addUpdateTab(0, "第一个", "");
        addUpdateTab(1, "第二个", "20");


        addUpdateTab2(0, "第一个", "2000");
        addUpdateTab2(1, "第二个", "100");

        addUpdateTab3(0, "第一个", "10000");
        addUpdateTab3(1, "第二个", "100");

    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LogU.d("seekbar   滑动");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                LogU.d("seekbar   触摸开始");
                seekbar.setThumb(ContextCompat.getDrawable(LinearLayoutActivity.this, R.drawable.shape_seekbar_circle));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                LogU.d("seekbar   触摸离开");
                Observable.timer(3000,TimeUnit.MILLISECONDS).compose(timer()).subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        seekbar.setThumb(ContextCompat.getDrawable(LinearLayoutActivity.this, R.color.transparent));

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogU.e(throwable.getMessage());
                    }
                });
            }
        });
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    private void addUpdateTab1(final int position, String title, String num) { // 更新角标
        LinearLayout tab = new LinearLayout(this);
        tab.setOrientation(LinearLayout.HORIZONTAL);
        tab.setGravity(Gravity.CENTER);

        TextView tabTitle = new TextView(this);
        tabTitle.setText(title);
        tabTitle.getPaint().setFakeBoldText(true);
        tabTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        tabTitle.setSingleLine();

        TextView updateView = new TextView(this);
        updateView.setIncludeFontPadding(false);
        updateView.setTextColor(Color.RED);
        updateView.setGravity(Gravity.TOP);
        updateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        updateView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        updateView.setText(num);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        updateView.setLayoutParams(layoutParams);

        tab.addView(tabTitle);
        tab.addView(updateView);

        addTab(position, tab);

    }

    private void addUpdateTab(final int position, String title, String num) { // 更新角标

        RelativeLayout tab = new RelativeLayout(this);
        tab.setGravity(Gravity.CENTER);

        TextView tabTitle = new TextView(this);
        tabTitle.setId(position + 1);
        tabTitle.setText(title);
        tabTitle.getPaint().setFakeBoldText(true);
        tabTitle.setGravity(Gravity.CENTER);
        tabTitle.setSingleLine();

        TextView updateView = new TextView(this);
        updateView.setTextColor(Color.RED);
        updateView.setGravity(Gravity.CENTER);
        updateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        updateView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        updateView.setText(num);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(RelativeLayout.END_OF, position + 1);
        layoutParams.setMargins(10, 0, 0, 0);
        updateView.setLayoutParams(layoutParams);

        tab.addView(tabTitle);
        tab.addView(updateView);

        addTab(position, tab);

    }

    private void addTab(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tabsContainer.addView(tab, position, expandedTabLayoutParams);
    }


    private void addUpdateTab2(final int position, String title, String num) { // 更新角标

        RelativeLayout tab = new RelativeLayout(this);

        TextView tabTitle = new TextView(this);
        tabTitle.setId(position + 1);
        tabTitle.setText(title);
        tabTitle.getPaint().setFakeBoldText(true);
        tabTitle.setGravity(Gravity.CENTER);
        tabTitle.setSingleLine();

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tabTitle.setLayoutParams(params);

        TextView updateView = new TextView(this);
        updateView.setTextColor(Color.RED);
        updateView.setGravity(Gravity.CENTER);
        updateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        updateView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        updateView.setText(num);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.END_OF, position + 1);
        layoutParams.leftMargin = 10;
        updateView.setLayoutParams(layoutParams);

        tab.addView(tabTitle);
        tab.addView(updateView);

        addTab2(position, tab);

    }

    private void addTab2(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tabsContainer1.addView(tab, position, expandedTabLayoutParams1);
    }


    private void addUpdateTab3(final int position, String title, String num) { // 更新角标

        ConstraintLayout tab = new ConstraintLayout(this);


        TextView tabTitle = new TextView(this);
        tabTitle.setId(R.id.parallax_tag_1 + position);
        tabTitle.setText(title);
        tabTitle.getPaint().setFakeBoldText(true);
        tabTitle.setGravity(Gravity.CENTER);
        tabTitle.setSingleLine();

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topToTop = ConstraintSet.PARENT_ID;
        layoutParams.startToStart = ConstraintSet.PARENT_ID;
        layoutParams.endToEnd = ConstraintSet.PARENT_ID;
        layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
        tabTitle.setLayoutParams(layoutParams);


        TextView updateView = new TextView(this);
        updateView.setId(R.id.parallax_tag_2 + position);
        updateView.setTextColor(Color.RED);
        updateView.setGravity(Gravity.CENTER);
        updateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        updateView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        updateView.setText(num);

        layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topToTop = tabTitle.getId();
        layoutParams.startToEnd = tabTitle.getId();
        layoutParams.leftMargin = 10;
        updateView.setLayoutParams(layoutParams);

        tab.addView(tabTitle);
        tab.addView(updateView);

        addTab3(position, tab);

    }

    private void addTab3(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tabsContainer2.addView(tab, position, expandedTabLayoutParams3);
        //tabsContainer2.addView(tab, position, expandedTabLayoutParams2);
    }

}
