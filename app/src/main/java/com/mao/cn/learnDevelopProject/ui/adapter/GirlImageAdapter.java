package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.model.GirlModel;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:  zhangkun .
 * date:    on 2017/8/3.
 */

public class GirlImageAdapter extends RecyclerView.Adapter<GirlImageAdapter.ViewHolder> {

    private Context context;
    private List<GirlModel> girlModels;

    public GirlImageAdapter(Context context) {
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

//        ViewGroup.LayoutParams layoutParams = holder.imageViewItemGirl.getLayoutParams();

        /*GlideApp.with(context)
                .load(girlModel)
                .placeholder(R.drawable.demo)
                .into(holder.imageViewItemGirl);*/


        holder.imageViewItemGirl.setTag(R.id.imageViewItemGirl,position);
        RequestOptions requestOptions = new RequestOptions()
                .skipMemoryCache(false)
                .dontAnimate()
                .centerCrop()
                .placeholder(R.drawable.demo)
                .error(R.drawable.demo);
        Glide.with(context)
                .load(istrurl)
                .apply(requestOptions)
                .into(new ImageViewTarget<Drawable>(holder.imageViewItemGirl) {
                    @Override
                    protected void setResource(@Nullable Drawable resource) {
                        if (position != (int)holder.imageViewItemGirl.getTag(R.id.imageViewItemGirl)) {
                            return;
                        }
                        holder.imageViewItemGirl.setImageDrawable(resource);

                    }
                });
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
