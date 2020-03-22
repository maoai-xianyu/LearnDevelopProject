package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.DensityUtil;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.SupportRSBlurTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.bumptech.glide.request.RequestOptions.overrideOf;

/**
 * author:  zhangkun .
 * date:    on 2018/11/5.
 */
public class EasyRecycleViewGlideHolder extends BaseViewHolder<EasyRecycleViewGlideHolder.Type> {

    ImageView imageView;
    TextView textView;
    Context context;

    public enum Type {
        Mask,
        NinePatchMask,
        CropTop,
        CropCenter,
        CropBottom,
        CropSquare,
        CropCircle,
        ColorFilter,
        Grayscale,
        RoundedCorners,
        Blur,
        SupportRSBlur,
        Toon,
        Sepia,
        Contrast,
        Invert,
        Pixel,
        Sketch,
        Swirl,
        Brightness,
        Kuawahara,
        Vignette,
        Placeholder,
        PlaceholderFade,
        ImageSize,
        ImageGif,
        ImageGifStatic,
        ImageDrawable,
        ImageInto,
        ImageTransforms
    }


    public EasyRecycleViewGlideHolder(ViewGroup parent, int res) {
        super(parent, res);
        context = parent.getContext();
        imageView = $(R.id.image);
        textView = $(R.id.title);

    }

    @Override
    public void setData(Type data) {
        super.setData(data);

        switch (data) {
            case Mask: {
                int width = DensityUtil.dip2px(context, 266.66f);
                int height = DensityUtil.dip2px(context, 252.66f);
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(overrideOf(width, height))
                        .apply(bitmapTransform(new MultiTransformation<>(new CenterCrop(),
                                new MaskTransformation(R.drawable.mask_starfish))))
                        .into(imageView);
                break;
            }
            case NinePatchMask: {
                int width = DensityUtil.dip2px(context, 300.0f);
                int height = DensityUtil.dip2px(context, 200.0f);
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(overrideOf(width, height))
                        .apply(bitmapTransform(new MultiTransformation<>(new CenterCrop(),
                                new MaskTransformation(R.drawable.mask_chat_right))))
                        .into(imageView);
                break;
            }
            case CropTop:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(
                                new CropTransformation(DensityUtil.dip2px(context, 300), DensityUtil.dip2px(context, 100),
                                        CropTransformation.CropType.TOP)))
                        .into(imageView);
                break;
            case CropCenter:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(
                                new CropTransformation(DensityUtil.dip2px(context, 300), DensityUtil.dip2px(context, 100), CropTransformation.CropType.CENTER)))
                        .into(imageView);
                break;
            case CropBottom:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(
                                new CropTransformation(DensityUtil.dip2px(context, 300), DensityUtil.dip2px(context, 100),
                                        CropTransformation.CropType.BOTTOM)))
                        .into(imageView);

                break;
            case CropSquare:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new CropSquareTransformation()))
                        .into(imageView);
                break;
            case CropCircle:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new CropCircleTransformation()))
                        .into(imageView);
                break;
            case ColorFilter:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0))))
                        .into(imageView);
                break;
            case Grayscale:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new GrayscaleTransformation()))
                        .into(imageView);
                break;
            case RoundedCorners:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new RoundedCornersTransformation(45, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM)))
                        .into(imageView);
                break;
            case Blur:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new BlurTransformation(25)))
                        .into(imageView);
                break;
            case SupportRSBlur:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new SupportRSBlurTransformation(25, 10)))
                        .into(imageView);
                break;
            case Toon:
                Glide.with(context)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new ToonFilterTransformation()))
                        .into(imageView);
                break;
            case Sepia:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new SepiaFilterTransformation()))
                        .into(imageView);
                break;
            case Contrast:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new ContrastFilterTransformation(2.0f)))
                        .into(imageView);
                break;
            case Invert:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new InvertFilterTransformation()))
                        .into(imageView);
                break;
            case Pixel:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new PixelationFilterTransformation(20)))
                        .into(imageView);
                break;
            case Sketch:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new SketchFilterTransformation()))
                        .into(imageView);
                break;
            case Swirl:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(
                                new SwirlFilterTransformation(0.5f, 1.0f, new PointF(0.5f, 0.5f))).dontAnimate())
                        .into(imageView);
                break;
            case Brightness:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new BrightnessFilterTransformation(0.5f)).dontAnimate())
                        .into(imageView);
                break;
            case Kuawahara:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new KuwaharaFilterTransformation(25)).dontAnimate())
                        .into(imageView);
                break;
            case Vignette:
                Glide.with(context)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f)).dontAnimate())
                        .into(imageView);

                break;
            case Placeholder:
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.demo);
                requestOptions.error(R.drawable.check);
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE); //禁止使用缓存功能
                Glide.with(context)
                        .load("http://guolin.tech/book.png")
                        .apply(requestOptions)
                        .into(imageView);

                break;
            case PlaceholderFade:
                Glide.with(context)
                        .load(R.drawable.check)
                        .transition(withCrossFade())
                        .into(imageView);

                break;

            case ImageSize:
                RequestOptions options = new RequestOptions();
                options.override(100, 100);
                Glide.with(context)
                        .load("http://guolin.tech/book.png")
                        .apply(options)
                        .into(imageView);

                break;
            case ImageGif:
                Glide.with(context)
                        .asGif() // 强制加载gif
                        .load("http://guolin.tech/test.gif")
                        .into(imageView);
                break;

            case ImageGifStatic:
                Glide.with(context)
                        .asBitmap() // 取Gif的第一帧
                        .load("http://guolin.tech/test.gif")
                        .into(imageView);
                break;
            case ImageDrawable:
                RequestOptions dOptions = new RequestOptions();
                dOptions.centerCrop();
                dOptions.error(R.drawable.check);
                dOptions.placeholder(R.drawable.demo);
                dOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                Glide.with(context)
                        .asDrawable()
                        .load(R.drawable.bg_loading)
                        .apply(dOptions)
                        .into(imageView);
                break;
            case ImageInto:
                SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                };
                Glide.with(context)
                        .asDrawable()
                        .load(R.drawable.bg_loading)
                        .into(simpleTarget);
                break;
            case ImageTransforms:
                RequestOptions toptions = new RequestOptions()
                        .circleCrop();
                Glide.with(context)
                        .load("http://guolin.tech/book.png")
                        .apply(toptions)
                        .into(imageView);
                break;

        }

        textView.setText(data.name());
    }
}