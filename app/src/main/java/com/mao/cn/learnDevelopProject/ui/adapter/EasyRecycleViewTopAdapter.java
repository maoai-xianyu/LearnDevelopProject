package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.model.MovieDetail;

/**
 * author:  zhangkun .
 * date:    on 2017/8/3.
 */

public class EasyRecycleViewTopAdapter extends RecyclerArrayAdapter<MovieDetail> {


    public EasyRecycleViewTopAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new EasyRecycleViewTopHolder(parent,R.layout.item_easy_movie_top_detail);
    }
}
