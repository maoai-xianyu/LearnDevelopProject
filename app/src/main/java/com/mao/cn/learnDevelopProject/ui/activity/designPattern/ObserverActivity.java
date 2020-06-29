package com.mao.cn.learnDevelopProject.ui.activity.designPattern;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer.DatabaseManager;
import com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer.ObserverT;
import com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer.ObserverUserModel;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;


/**
 * 单例设计模式，activity 管理
 */
public class ObserverActivity extends BaseActivity implements ObserverT<ObserverUserModel> {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;
    @BindView(R.id.btnAddJump)
    Button btnAddJump;
    @BindView(R.id.ryv)
    RecyclerView ryv;
    private List<ObserverUserModel> mStrings;
    private ObserverAdapter mObserverAdapter;


    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_observer_design;
    }

    @Override
    public void initView() {
        DatabaseManager.getInstance().register(this);
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setVisibility(View.VISIBLE);
        mDTextView.setText("观察者者模式");

        mStrings = new ArrayList<>();
        ObserverUserModel observerUserModel;
        for (int i = 0; i < 20; i++) {
            observerUserModel = new ObserverUserModel();
            observerUserModel.setName("sss-" + i);
            mStrings.add(observerUserModel);
        }
        mObserverAdapter = new ObserverAdapter(this, mStrings, R.layout.item_observer_adapter);
        ryv.setAdapter(mObserverAdapter);

    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));

        RxView.clicks(btnAddJump).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    startActivity(ObserverAddActivity.class);
                }, throwable -> LogU.e(throwable.toString()));

    }


    @Override
    public void update(ObserverUserModel observerUserModel) {
        mStrings.add(observerUserModel);
        mObserverAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseManager.getInstance().unRegister(this);
    }
}
