package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:  zhangkun .
 * date:    on 2017/8/3.
 */

public class PullTopDecorationAdapter extends RecyclerView.Adapter<PullTopDecorationAdapter.ViewHolder> {

    private Context context;
    private List<String> mStrings;
    private List<Integer> heights;

    public PullTopDecorationAdapter(Context context) {
        this.context = context;
        this.mStrings = new ArrayList<>();
        this.heights = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            heights.add((int) (200 * Math.random()));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 都会报错 simple_list_item_1 textview 没有上层父控件
        View view = LayoutInflater.from(context).inflate(R.layout.item_pull_decoration, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMovieTitle.setText(mStrings.get(position));
    }

    @Override
    public int getItemCount() {
        return ListU.isEmpty(mStrings) ? 0 : mStrings.size();
    }

    public void addMovieList(List<String> movieDetails) {
        this.mStrings.clear();
        this.mStrings.addAll(movieDetails);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text1)
        TextView tvMovieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
