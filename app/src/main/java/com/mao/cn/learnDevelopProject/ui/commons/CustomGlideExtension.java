package com.mao.cn.learnDevelopProject.ui.commons;

import android.annotation.SuppressLint;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;

/**
 * author:  zhangkun .
 * date:    on 2018/11/5.
 */
@GlideExtension
public class CustomGlideExtension {


    //GlideOption用来添加自定义的方法，

    //缩略图的最小尺寸，单位：px
    private static final int MINI_THUMB_SIZE = 100;


    /**
     * 将构造方法设为私有，作为工具类使用
     */
    private CustomGlideExtension() {
    }


    /**
     * 1.自己新增的方法的第一个参数必须是RequestOptions options
     * 2.方法必须是静态的
     *
     * @param options
     */
    @SuppressLint("CheckResult")
    @GlideOption
    static void cacheSource(RequestOptions options) {
        options.diskCacheStrategy(DiskCacheStrategy.DATA);
    }

    @SuppressLint("CheckResult")
    @GlideOption
    static void cacheSourceNone(RequestOptions options) {
        options.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

    }

    @SuppressLint("CheckResult")
    @GlideOption
    static void miniThumb(RequestOptions options) {
        options.fitCenter().override(MINI_THUMB_SIZE);
    }

    //GlideType用来支持新的格式。

    private static final RequestOptions DECODE_TYPE_GIF = GlideOptions.decodeTypeOf(GifDrawable.class).lock();

    @SuppressLint("CheckResult")
    @GlideType(GifDrawable.class)
    static void asGIF(RequestBuilder<GifDrawable> requestBuilder) {
        requestBuilder.transition(new DrawableTransitionOptions())
                .apply(DECODE_TYPE_GIF);
    }

}
