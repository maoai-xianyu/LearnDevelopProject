// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 16:39 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jakewharton.rxbinding.view.RxView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.model.MovieDetail;
import com.mao.cn.learnDevelopProject.ui.adapter.EasyRecycleViewGlideAdapter;
import com.mao.cn.learnDevelopProject.ui.adapter.EasyRecycleViewGlideHolder.Type;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.commons.GlideApp;
import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewShowContent;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class EasyRecycleViewGlideShowContentActivity extends BaseActivity implements IEasyRecycleViewShowContent {


    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.AImageBtn)
    Button aimageBtn;
    @BindView(R.id.BImageBtn)
    Button bimageBtn;
    @BindView(R.id.CImageBtn)
    Button cimageBtn;
    @BindView(R.id.DImageBtn)
    Button dimageBtn;
    @BindView(R.id.EImageBtn)
    Button eImageBtn;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.rvData)
    EasyRecyclerView rvData;

    private RecyclerArrayAdapter<Type> adapter;


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_easy_recycle_view_glide_show_content;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutCourse = new LinearLayoutManager(context);
        linearLayoutCourse.setOrientation(LinearLayoutManager.VERTICAL);
        rvData.setLayoutManager(linearLayoutCourse);

        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(this, 1f), Util.dip2px(this, 0), 0);
        itemDecoration.setDrawLastItem(false);
        rvData.addItemDecoration(itemDecoration);
        adapter = new EasyRecycleViewGlideAdapter(this);
        rvData.setAdapterWithProgress(adapter);

        List<Type> dataSet = new ArrayList<>();
        dataSet.add(Type.Mask);
        dataSet.add(Type.NinePatchMask);
        dataSet.add(Type.CropTop);
        dataSet.add(Type.CropCenter);
        dataSet.add(Type.CropBottom);
        dataSet.add(Type.CropSquare);
        dataSet.add(Type.CropCircle);
        dataSet.add(Type.ColorFilter);
        dataSet.add(Type.Grayscale);
        dataSet.add(Type.RoundedCorners);
        dataSet.add(Type.Blur);
        dataSet.add(Type.SupportRSBlur);
        dataSet.add(Type.Toon);
        dataSet.add(Type.Sepia);
        dataSet.add(Type.Contrast);
        dataSet.add(Type.Invert);
        dataSet.add(Type.Pixel);
        dataSet.add(Type.Sketch);
        dataSet.add(Type.Swirl);
        dataSet.add(Type.Brightness);
        dataSet.add(Type.Kuawahara);
        dataSet.add(Type.Vignette);
        dataSet.add(Type.Placeholder);
        dataSet.add(Type.PlaceholderFade);
        dataSet.add(Type.ImageSize);
        dataSet.add(Type.ImageGif);
        dataSet.add(Type.ImageGifStatic);
        dataSet.add(Type.ImageDrawable);
        dataSet.add(Type.ImageInto);
        dataSet.add(Type.ImageTransforms);

        adapter.addAll(dataSet);
    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(aimageBtn).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            downloadImage();

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(bimageBtn).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            glideListener();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(cimageBtn).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            glideApp();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(dimageBtn).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            glideAppGif();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(eImageBtn).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            glideAppAssets();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public void showTopMovie(List<MovieDetail> subjects, String title) {
        if (!checkActivityState()) return;
    }

    @Override
    public void showErrorMovie() {
        if (!checkActivityState()) return;
        rvData.showError();
    }


    /**
     * 当调用了submit()方法后会立即返回一个FutureTarget对象，然后Glide会在后台开始下载图片文件
     * FutureTarget的get()方法就可以去获取下载好的图片文件了，如果此时图片还没有下载完，那么get()方法就会阻塞住，一直等到图片下载完成才会有值返回。
     */
    public void downloadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://www.guolin.tech/book.png";
                    final Context context = getApplicationContext();
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url)
                            .submit();
                    final File imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogU.d("image " + imageFile.getPath());
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void glideListener() {
        Glide.with(this)
                .load("http://www.guolin.tech/book.png")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        LogU.e("图片加载失败 " + (e != null ? e.getLocalizedMessage() : ""));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        LogU.d("图片加载成功 ");
                        return false;
                    }
                })
                .into(imageView);
    }

    private void glideApp() {
        GlideApp.with(this)
                .load("http://www.guolin.tech/book.png")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        if (dataSource == DataSource.MEMORY_CACHE) {
                            //当图片位于内存缓存时，glide默认不会加载动画
                            imageView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.fade_in));
                        }
                        return false;
                    }
                })
                .placeholder(R.drawable.demo)
                .error(R.drawable.check)
                .fitCenter()
                .transition(GenericTransitionOptions.with(R.anim.fade_in))
                //.transition(withCrossFade())
                //.cacheSouceNone()
                .cacheSource()
                .override(Target.SIZE_ORIGINAL)
                .into(imageView);

    }



    private void glideAppGif() {
        GlideApp.with(this)
                .asGIF()
                .load("http://www.guolin.tech/test.gif")
                .placeholder(R.drawable.demo)
                .error(R.drawable.check)
                .override(Target.SIZE_ORIGINAL)
                .circleCrop()
                .into(imageView);
    }

    private void glideAppAssets() {
        GlideApp.with(this)
                .load(ValueMaps.ImagePath.IMAGE_RES_ASSETS+"files/images/image_art.png")
                .placeholder(R.drawable.demo)
                .error(R.drawable.check)
                .override(Target.SIZE_ORIGINAL)
                .circleCrop()
                .into(imageView);
    }
}