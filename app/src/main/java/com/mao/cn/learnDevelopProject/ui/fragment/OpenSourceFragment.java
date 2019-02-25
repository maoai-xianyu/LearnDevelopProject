package com.mao.cn.learnDevelopProject.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dasu.blur.BlurConfig;
import com.dasu.blur.DBlur;
import com.dasu.blur.OnBlurListener;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.KeyMaps;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.activity.DataBindingActivity;
import com.mao.cn.learnDevelopProject.ui.activity.DataBindingAnimationActivity;
import com.mao.cn.learnDevelopProject.ui.activity.DataBindingExpressionActivity;
import com.mao.cn.learnDevelopProject.ui.activity.DataBindingLambdaActivity;
import com.mao.cn.learnDevelopProject.ui.activity.DataBindingListActivity;
import com.mao.cn.learnDevelopProject.ui.activity.EasyRecycleViewGlideShowContentActivity;
import com.mao.cn.learnDevelopProject.ui.activity.EasyRecycleViewShowContentActivity;
import com.mao.cn.learnDevelopProject.ui.activity.ExpendTextViewContentActivity;
import com.mao.cn.learnDevelopProject.ui.activity.HtmlActivity;
import com.mao.cn.learnDevelopProject.ui.activity.HtmlQbActivity;
import com.mao.cn.learnDevelopProject.ui.activity.jetpack.JetPackActivity;
import com.mao.cn.learnDevelopProject.ui.activity.NotificationActivity;
import com.mao.cn.learnDevelopProject.ui.activity.jetpack.JetPackViewModelActivity;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class OpenSourceFragment extends BaseFragment {

    @BindView(R.id.ivFirst)
    ImageView ivFirst;
    @BindView(R.id.ivGlide)
    ImageView ivGlide;
    @BindView(R.id.tv_easyRy)
    TextView tvEasyRy;
    @BindView(R.id.tv_easyRyGlide)
    TextView tvEasyRyGlide;
    @BindView(R.id.tv_expend)
    TextView tvExpend;
    @BindView(R.id.tv_databinding)
    TextView tvDatabinding;
    @BindView(R.id.tv_databinding_list)
    TextView tvDatabindinglist;
    @BindView(R.id.tv_databinding_expression)
    TextView tvDatabindingExpression;
    @BindView(R.id.tv_databinding_lambda)
    TextView tvDatabindinglambda;
    @BindView(R.id.tv_databinding_anim)
    TextView tvDatabindinganim;
    @BindView(R.id.tv_notification)
    TextView tvNotification;
    @BindView(R.id.tv_html)
    TextView tvHtml;
    @BindView(R.id.tv_html_qb)
    TextView tvHtmlQb;
    @BindView(R.id.tv_JetPack)
    TextView tvJetPack;
    @BindView(R.id.tv_JetPack_v)
    TextView tvJetPackV;

    public static OpenSourceFragment newInstance() {
        OpenSourceFragment fragment = new OpenSourceFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_open_source;
    }

    @Override
    public void initView() {

        //第三方高斯模糊
        //异步模糊，将drawable资源文件中的图片以 NATIVE 方式进行模糊，注册回调
        DBlur.source(activity, R.drawable.image_dafault).mode(BlurConfig.MODE_NATIVE).build()
                .doBlur(new OnBlurListener() {
                    @Override
                    public void onBlurSuccess(Bitmap bitmap) {
                        ivFirst.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBlurFailed() {
                        //do something
                    }
                });


        // glide
        Glide.with(this).load(R.drawable.image_dafault)
                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                .into(ivGlide);


    }

    @Override
    public void setListener() {

        RxView.clicks(tvEasyRy).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(EasyRecycleViewShowContentActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvEasyRyGlide).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(EasyRecycleViewGlideShowContentActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvExpend).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(ExpendTextViewContentActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvDatabinding).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(DataBindingActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvDatabindinglist).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(DataBindingListActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvDatabindingExpression).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(DataBindingExpressionActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvDatabindinglambda).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(DataBindingLambdaActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvDatabindinganim).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(DataBindingAnimationActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvNotification).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            startActivity(NotificationActivity.class);

        }, throwable -> LogU.e(throwable.getMessage()));


        RxView.clicks(tvHtml).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            Bundle bundle = new Bundle();
            bundle.putInt(KeyMaps.HTML_TYPE, KeyMaps.HtmlType.HTML_TYPE_FOREIGN_TEACHER_LESSON_DETAIL);
            startActivity(HtmlActivity.class, bundle);
        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvHtmlQb).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            Bundle bundle = new Bundle();
            bundle.putInt(KeyMaps.HTML_TYPE, KeyMaps.HtmlType.HTML_TYPE_FOREIGN_TEACHER_LESSON_DETAIL);
            startActivity(HtmlQbActivity.class, bundle);
        }, throwable -> LogU.e(throwable.getMessage()));


        RxView.clicks(tvJetPack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(JetPackActivity.class);
        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvJetPackV).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            startActivity(JetPackViewModelActivity.class);
        }, throwable -> LogU.e(throwable.getMessage()));

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
