package com.mao.cn.learnDevelopProject.ui.activity.aspectJ;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo.RetrofitDemoActivity;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class AspectJDemoActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_aspectj_demo;
    }

    @Override

    public void initView() {

        idBack.setVisibility(View.VISIBLE);

    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));


        RxView.clicks(textView2).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {

                    // 点击跳转
                    //jumpR();
                    jump();

                }, throwable -> LogU.e(throwable.toString()));
    }

    // 检测网络，有网就调转
    private void jumpR(){
        if (CheckNetUtil.isNetworkAvailable(this)) {
            startActivity(RetrofitDemoActivity.class);
        }
    }


    @CheckNet
    public void jump() {
        startActivity(RetrofitDemoActivity.class);
    }


    /**
     * 有网请求数据
     */
    @CheckNet
    public void postData(){

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
