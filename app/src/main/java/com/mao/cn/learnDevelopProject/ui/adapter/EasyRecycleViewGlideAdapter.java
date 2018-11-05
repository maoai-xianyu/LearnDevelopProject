package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mao.cn.learnDevelopProject.R;

/**
 * author:  zhangkun .
 * date:    on 2018/11/5.
 */
public class EasyRecycleViewGlideAdapter extends RecyclerArrayAdapter<EasyRecycleViewGlideHolder.Type> {

    public EasyRecycleViewGlideAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new EasyRecycleViewGlideHolder(parent,R.layout.item_easy_glide_view);
    }
}
