package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:  zhangkun .
 * date:    on 2017/8/3.
 */

public class PullTopAdapter extends RecyclerView.Adapter<PullTopAdapter.ViewHolder> {

    private Context context;
    private List<String> mStrings;
    private List<Integer> heights;
    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public PullTopAdapter(Context context) {
        this.context = context;
        this.mStrings = new ArrayList<>();
        this.heights = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            heights.add((int) (200 * Math.random()));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 这个不可以，直接报错，因为给 ViewGroup 又添加了一次子控件
        //ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)
        //View view = View.inflate(context,android.R.layout.simple_list_item_1, parent);

        // 这个不可可以的，因为在 onBindViewHolder 获取 getLayoutParams() 会报错 不能获取父容器，因为没有上层控件,
        // java.lang.NullPointerException: Attempt to write to field 'int android.view.ViewGroup$LayoutParams.height' on a null object reference
        // 如果用于显示，不获取父容器的属性，是可以用的
        //View view = View.inflate(context,android.R.layout.simple_list_item_1, null);

        //这个是可以的，可以 获取 getLayoutParams()
        //View view = View.inflate(context, R.layout.item_pull, null);

        // 可以，RecyclerView/ListView会自动将child添加到它里面去
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.tvMovieTitle.getLayoutParams();
        layoutParams.height = heights.get(position);
        holder.tvMovieTitle.setLayoutParams(layoutParams);
        holder.tvMovieTitle.setBackgroundColor(Color.rgb(100, (int) (Math.random() * 255), (int) (Math.random() * 255)));
        holder.tvMovieTitle.setText(mStrings.get(position));

        RxView.clicks(holder.tvMovieTitle).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
                    if (mOnClickListener != null){
                        mOnClickListener.showOnClickItem(mStrings.get(position),position);
                    }
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
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


    // 添加数据
    public void addItem(int position){
        mStrings.add(position,"addItem "+position);
        heights.add(position,(int) (200 * Math.random()));
        notifyItemChanged(position);
    }

    // 移除数据
    public void removeItem(int position){
        mStrings.remove(position);
        heights.remove(position);
        notifyItemRemoved(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(android.R.id.text1)
        //@BindView(R.id.text1)
        TextView tvMovieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener{
        void showOnClickItem(String str,int position);
    }
}
