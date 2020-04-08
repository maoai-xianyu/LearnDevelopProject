package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.widgets.MessageBubbleView;

import butterknife.BindView;

public class DefineDragBubbleViewFragment extends BaseFragment {

    @BindView(R.id.bubbleView)
    MessageBubbleView loadView;


    public static DefineDragBubbleViewFragment newInstance() {
        DefineDragBubbleViewFragment fragment = new DefineDragBubbleViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_drag_bubble_view;
    }

    @Override
    public void initView() {

    }


    @Override
    public void setListener() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
