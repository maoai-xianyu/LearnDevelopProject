package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.TouchView;

import butterknife.BindView;

public class DefineTouchViewFragment extends BaseFragment {


    @BindView(R.id.touchView)
    TouchView mTouchView;

    public static DefineTouchViewFragment newInstance() {
        DefineTouchViewFragment fragment = new DefineTouchViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_touch_view;
    }

    @Override
    public void initView() {
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void setListener() {

        mTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogU.d("------> TouchView setOnClickListener onClick ");
            }
        });

        mTouchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogU.d("------> TouchView setOnTouchListener onTouch "+event.getAction());
                return false;
            }
        });


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
