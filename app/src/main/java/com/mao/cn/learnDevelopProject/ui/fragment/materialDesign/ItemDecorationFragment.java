package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign;

import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.adapter.PullTopDecorationAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.wedget.materialDesign.DividerItemGridDecorationDefine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ItemDecorationFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv;

    public static ItemDecorationFragment newInstance() {
        ItemDecorationFragment fragment = new ItemDecorationFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_item_decoration;
    }

    @Override
    public void initView() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            strings.add("item" + i);
        }

        PullTopDecorationAdapter pullTopAdapter = new PullTopDecorationAdapter(context);
        pullTopAdapter.addMovieList(strings);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(2000);
        animator.setRemoveDuration(2000);

        /*rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new DividerItemDecorationDefine(context,
                LinearLayoutManager.VERTICAL));*/

        rv.setLayoutManager(new GridLayoutManager(context, 3));
        rv.addItemDecoration(new DividerItemGridDecorationDefine(context));
        rv.setItemAnimator(animator);
        rv.setAdapter(pullTopAdapter);


    }

    @Override
    public void setListener() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
