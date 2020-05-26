package com.mao.cn.learnDevelopProject.ui.activity.designPattern;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer.DatabaseManager;
import com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer.ObserverUserModel;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;


/**
 * 单例设计模式，activity 管理
 */
public class ObserverAddActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.btAdd)
    Button btAdd;

    @Override
    protected void setupComponent(AppComponent appComponent) {




    }


    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_observer_add_design;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setVisibility(View.VISIBLE);
        mDTextView.setText("添加数据更新数据");

    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));

        RxView.clicks(btAdd).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {

                    // 添加数据

                    // 插入数据库

                    DatabaseManager.getInstance().insert(new ObserverUserModel(etName.getText().toString()));

                    // 通知 ObserverActivity 更新
                    // 1. 可以当关闭的时候， onActivityResult
                    // 2. 利用 otto  或者 EventBus
                    // 3. 页面关闭，监听 activity 的生命周期
                    // 4. 用观察者模式更新列表



                }, throwable -> LogU.e(throwable.toString()));
    }

}
