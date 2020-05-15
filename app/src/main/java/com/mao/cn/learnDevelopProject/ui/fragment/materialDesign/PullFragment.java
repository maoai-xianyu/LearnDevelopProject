package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.adapter.PullTopAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class PullFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tvChange)
    TextView tvChange;
    @BindView(R.id.tvADD)
    TextView tvADD;
    @BindView(R.id.tvRemove)
    TextView tvRemove;

    private boolean isChange;
    private PullTopAdapter mPullTopAdapter;

    public static PullFragment newInstance() {
        PullFragment fragment = new PullFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_pull;
    }

    @Override
    public void initView() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            strings.add("item" + i);
        }
        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        //rv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        mPullTopAdapter = new PullTopAdapter(context);
        mPullTopAdapter.addMovieList(strings);
        rv.setAdapter(mPullTopAdapter);
    }

    @Override
    public void setListener() {

        RxView.clicks(tvChange).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            if (!isChange) {
                rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            } else {
                rv.setLayoutManager(new LinearLayoutManager(context));
            }
            isChange = !isChange;

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvADD).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            mPullTopAdapter.addItem(0);

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvRemove).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            mPullTopAdapter.removeItem(0);

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        mPullTopAdapter.setOnClickListener(new PullTopAdapter.OnClickListener() {
            @Override
            public void showOnClickItem(String str, int position) {
                ToastUtils.show("str "+str);
            }
        });


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
