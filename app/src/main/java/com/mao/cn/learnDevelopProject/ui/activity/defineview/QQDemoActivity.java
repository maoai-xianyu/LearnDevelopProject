package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.CircleImageView;
import com.mao.cn.learnDevelopProject.widgets.QQSlidingMenu;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class QQDemoActivity extends BaseActivity {


    @BindView(R.id.user_head)
    CircleImageView userHead;

    @BindView(R.id.qqSlidingMenu)
    QQSlidingMenu mQQSlidingMenu;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_qq;
    }

    @Override
    public void initView() {


    }

    @Override
    public void setListener() {

        RxView.clicks(userHead).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mQQSlidingMenu.toggleMenu();

            }
        }, throwable -> LogU.e(throwable.getMessage()));

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
