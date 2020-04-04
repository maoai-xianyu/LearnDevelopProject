package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign;

import android.os.Bundle;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.adapter.PullTopAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.widgets.mdrecycelerview.MDWrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddHeaderFooterFragment extends BaseFragment {


    @BindView(R.id.rv)
    MDWrapRecyclerView rv;

    private PullTopAdapter mPullTopAdapter;

    public static AddHeaderFooterFragment newInstance() {
        AddHeaderFooterFragment fragment = new AddHeaderFooterFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_footer;
    }

    @Override
    public void initView() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            strings.add("item" + i);
        }

        TextView textView = new TextView(context);
        textView.setText("header");
        textView.setTextColor(ContextCompat.getColor(context,R.color.c_ff4D30));
        textView.setTextSize(getResources().getDimensionPixelOffset(R.dimen.text_size_18));
        rv.addHeaderView(textView);
        rv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        mPullTopAdapter = new PullTopAdapter(context);
        mPullTopAdapter.addMovieList(strings);
        rv.setAdapter(mPullTopAdapter);
    }

    @Override
    public void setListener() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
