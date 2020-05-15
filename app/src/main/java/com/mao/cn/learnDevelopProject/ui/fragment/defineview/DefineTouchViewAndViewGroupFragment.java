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
import com.mao.cn.learnDevelopProject.widgets.TouchViewGroup;
import com.mao.cn.learnDevelopProject.widgets.TouchViewInner;

import butterknife.BindView;

public class DefineTouchViewAndViewGroupFragment extends BaseFragment {


    @BindView(R.id.touchView)
    TouchView mTouchView;
    @BindView(R.id.touchViewGroup)
    TouchViewGroup touchViewGroup;
    @BindView(R.id.touchViewInner)
    TouchViewInner touchViewInner;

    public static DefineTouchViewAndViewGroupFragment newInstance() {
        DefineTouchViewAndViewGroupFragment fragment = new DefineTouchViewAndViewGroupFragment();
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




        /*touchViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogU.d("------> touchViewGroup setOnClickListener onClick ");
            }
        });
        touchViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogU.d("------> touchViewGroup setOnTouchListener onTouch "+event.getAction());
                return false;
            }
        });*/

        /*touchViewInner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogU.d("------> touchViewInner setOnClickListener onClick ");
            }
        });*/

        touchViewInner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogU.d("------> touchViewInner setOnTouchListener onTouch "+event.getAction());
                return false;
            }
        });



    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
