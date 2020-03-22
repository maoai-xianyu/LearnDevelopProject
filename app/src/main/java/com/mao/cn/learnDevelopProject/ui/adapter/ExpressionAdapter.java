package com.mao.cn.learnDevelopProject.ui.adapter;

import androidx.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.mao.cn.learnDevelopProject.ui.commons.GlideApp;

/**
 * author:  zhangkun .
 * date:    on 2018/12/4.
 */
public class ExpressionAdapter {

    @BindingAdapter({"app:imageUri", "app:placeholder"})
    public static void loadImageFromUrl(ImageView view, String url, Drawable drawable) {

        GlideApp.with(view.getContext())
                .load(url)
                .placeholder(drawable)
                .into(view);
    }

}
