package com.mao.cn.learnDevelopProject.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.model.Images;
import com.mao.cn.learnDevelopProject.model.MovieDetail;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;

/**
 * author:  zhangkun .
 * date:    on 2018/11/1.
 */
public class EasyRecycleViewTopHolder extends BaseViewHolder<MovieDetail> {


    ImageView svMovieCover;
    TextView tvMovieTitle;
    TextView tvMovieTitleEng;


    public EasyRecycleViewTopHolder(ViewGroup parent, int res) {
        super(parent, res);
        svMovieCover = $(R.id.sv_movie_cover);
        tvMovieTitle = $(R.id.tv_movie_title);
        tvMovieTitleEng = $(R.id.tv_movie_title_eng);
    }

    @Override
    public void setData(MovieDetail data) {
        super.setData(data);

        tvMovieTitle.setText(data.getTitle());
        tvMovieTitleEng.setText(data.getOriginal_title());

        Images images = data.getImages();
        if (images != null && StringU.isNotEmpty(images.getMedium())) {
            Glide.with(getContext())
                    .load(images.getMedium())
                    .into(svMovieCover);
        } else {
            Glide.with(getContext())
                    .load(R.drawable.image_dafault)
                    .into(svMovieCover);
        }

    }
}
