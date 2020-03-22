package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.adapter.PullTopAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PullFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv;

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
        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        PullTopAdapter pullTopAdapter = new PullTopAdapter(context);
        pullTopAdapter.addMovieList(strings);
        rv.setAdapter(pullTopAdapter);
    }

    @Override
    public void setListener() {

     /*   RxView.clicks(tvTitle).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            RxBus.get().post(new RefreshMainEvent());
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });*/


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
