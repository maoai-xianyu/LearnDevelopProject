package com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo;

import android.os.Bundle;
import android.widget.ImageButton;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class RetrofitDemoActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_retrofit_demo;
    }

    @Override
    public void initView() {
        retrofitC();

    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    private void retrofitC() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/") // 设置网路请求地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        Call<List<Repo>> octocat = gitHubService.listRepos("octocat");

        octocat.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                LogU.d("response" + response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                LogU.e("请求失败" + t);

            }
        });

    }
}
