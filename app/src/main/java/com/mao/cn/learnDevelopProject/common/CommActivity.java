package com.mao.cn.learnDevelopProject.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hwangjr.rxbus.RxBus;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by zhangkun on 2017/6/9.
 */

public abstract class CommActivity extends RxAppCompatActivity {

    public Context context;
    public Activity activity;

    public CommActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getArgs(this.getIntent().getExtras());
        this.setContentView(this.setView());
        init();
    }

    public void init() {
        this.activity = this;
        this.context = this;
        this.setting();
        this.initView();
        this.setListener();
    }

    public abstract void getArgs(Bundle var1);

    public abstract int setView();

    public abstract void initView();

    public abstract void setting();

    public abstract void setListener();

    public void onBackPressed() {
        this.finish();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        System.gc();
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    protected void startActivity(Class cls) {
        this.startActivity(cls, (Bundle) null, false);
    }

    protected void startActivity(Class cls, boolean isfinish) {
        this.startActivity(cls, (Bundle) null, isfinish);
    }

    protected void startActivity(Class cls, Bundle bundle) {
        this.startActivity(cls, bundle, false);
    }

    protected void startActivity(Class cls, Bundle bundle, boolean isfinish) {
        if (cls != null && this.activity != null && !this.activity.isFinishing()) {
            Intent intent = new Intent();
            if (bundle != null) {
                intent.putExtras(bundle);
            }

            intent.setClass(this.activity, cls);
            this.startActivity(intent);
            if (isfinish) {
                this.finish();
            }

        }
    }
}
