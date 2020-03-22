package com.mao.cn.learnDevelopProject.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.event.RefreshMainEvent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class MoviesFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    private MoviesFunFragment moviesFunFragment;

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_movies;
    }

    @Override
    public void initView() {

        switchFragment();

    }

    private void switchFragment() {
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        hideFragments(beginTransaction);
        if (moviesFunFragment == null){
            moviesFunFragment = MoviesFunFragment.newInstance();
            beginTransaction.add(R.id.fl_content, moviesFunFragment);
        }else {
            moviesFunFragment.setText("修改数据");
            beginTransaction.show(moviesFunFragment);
        }
        beginTransaction.commit();
    }

    private void hideFragments(FragmentTransaction beginTransaction) {
        if (moviesFunFragment != null){
            beginTransaction.hide(moviesFunFragment);
        }
    }

    private void changeFragment(){
        Observable.timer(3, TimeUnit.SECONDS).compose(timer()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                switchFragment();
            }
        });
    }


    @Override
    public void setListener() {

        RxView.clicks(tvTitle).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            RxBus.get().post(new RefreshMainEvent());
            changeFragment();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
