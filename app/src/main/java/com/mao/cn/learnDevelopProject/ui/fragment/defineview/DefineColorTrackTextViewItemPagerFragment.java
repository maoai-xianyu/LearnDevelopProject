package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

import butterknife.BindView;

public class DefineColorTrackTextViewItemPagerFragment extends BaseFragment {


    @BindView(R.id.tvShow)
    TextView tvShow;


    private String str;


    public static DefineColorTrackTextViewItemPagerFragment newInstance(String title) {
        DefineColorTrackTextViewItemPagerFragment fragment = new DefineColorTrackTextViewItemPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {
        str = bundle.getString("title", "");
    }

    @Override
    protected int setView() {
        return R.layout.frg_define_color_track_text_view_item;
    }

    @Override
    public void initView() {
        tvShow.setText(str);
    }

    @Override
    public void setListener() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
