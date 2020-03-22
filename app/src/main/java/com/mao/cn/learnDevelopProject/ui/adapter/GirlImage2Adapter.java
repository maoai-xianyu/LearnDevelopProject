package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.model.GirlModel;
import com.mao.cn.learnDevelopProject.ui.commons.GlideApp;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:  zhangkun .
 * date:    on 2017/8/3.
 */

public class GirlImage2Adapter extends RecyclerView.Adapter<GirlImage2Adapter.ViewHolder> {

    private Context context;
    private List<GirlModel> girlModels;

    public GirlImage2Adapter(Context context) {
        this.context = context;
        this.girlModels = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_girl, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GirlModel girlModel = girlModels.get(position);
        String istrurl = girlModel.getUrl();
        if (null == holder || null == istrurl || istrurl.equals("")) {
            return;
        }
        GlideApp.with(context)
                .load(istrurl)
                .placeholder(R.drawable.demo)
                .into(holder.imageViewItemGirl);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        if (holder != null) {
            GlideApp.get(context).clearDiskCache();
            GlideApp.get(context).clearMemory();

            holder.imageViewItemGirl.setImageResource(R.drawable.demo);

        }
        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        return ListU.isEmpty(girlModels) ? 0 : girlModels.size();
    }

    public void addGirl(List<GirlModel> girlModels) {
        this.girlModels.clear();
        this.girlModels.addAll(girlModels);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewItemGirl)
        ImageView imageViewItemGirl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
