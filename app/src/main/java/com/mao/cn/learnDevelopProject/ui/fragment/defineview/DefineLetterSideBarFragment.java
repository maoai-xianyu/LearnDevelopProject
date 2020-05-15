package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.widgets.LetterSideBar;

import butterknife.BindView;

public class DefineLetterSideBarFragment extends BaseFragment {


    @BindView(R.id.letterSideBar)
    LetterSideBar letterSideBar;
    @BindView(R.id.tvShow)
    TextView tvShow;

    public static DefineLetterSideBarFragment newInstance() {
        DefineLetterSideBarFragment fragment = new DefineLetterSideBarFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_letter_side_bar;
    }

    @Override
    public void initView() {


    }

    @Override
    public void setListener() {

        letterSideBar.setOnSideBarTouchListener(new LetterSideBar.SideBarTouchListener() {
            @Override
            public void onTouch(String letter, boolean isTouch) {
                    tvShow.setText(letter);
                    tvShow.setVisibility(isTouch?View.VISIBLE:View.GONE);
            }
        });


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
