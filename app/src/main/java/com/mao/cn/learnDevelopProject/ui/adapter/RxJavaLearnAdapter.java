package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:  zhangkun .
 * date:    on 2017/8/3.
 */

public class RxJavaLearnAdapter extends RecyclerView.Adapter<RxJavaLearnAdapter.ViewHolder> {

    private Context context;
    private List<String> strings;
    private BtnClickListener listener;

    public RxJavaLearnAdapter(Context context) {
        this.context = context;
        this.strings = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rxjava_learn_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String str = strings.get(position);

        holder.tvShow.setText(str);

        RxView.clicks(holder.tvShow).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            listener.showResult(str);
        }, throwable -> {
            Logger.e(throwable.getMessage());
        });

    }

    @Override
    public int getItemCount() {
        return ListU.isEmpty(strings) ? 0 : strings.size();
    }

    public void addStringList(List<String> stringList) {
        this.strings.clear();
        this.strings.addAll(stringList);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_show)
        TextView tvShow;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void addListener(BtnClickListener listener) {
        this.listener = listener;
    }

    public interface BtnClickListener {
        void showResult(String str);
    }
}
