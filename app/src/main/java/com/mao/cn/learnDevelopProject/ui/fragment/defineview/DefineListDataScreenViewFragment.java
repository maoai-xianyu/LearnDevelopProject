package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.adapter.define.ListScreenMenuAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.widgets.ListDataScreenView;

import butterknife.BindView;

public class DefineListDataScreenViewFragment extends BaseFragment {

    @BindView(R.id.listDataScreenView)
    ListDataScreenView listDataScreenView;


    private ListScreenMenuAdapter mListScreenMenuAdapter;
    public static DefineListDataScreenViewFragment newInstance() {
        DefineListDataScreenViewFragment fragment = new DefineListDataScreenViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_list_data_screen_view;
    }

    @Override
    public void initView() {
        mListScreenMenuAdapter = new ListScreenMenuAdapter(context);
        listDataScreenView.setAdapter(mListScreenMenuAdapter);
    }


    @Override
    public void setListener() {

       /* RxView.clicks(tv1).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            loadView.startAnimation();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });*/


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
