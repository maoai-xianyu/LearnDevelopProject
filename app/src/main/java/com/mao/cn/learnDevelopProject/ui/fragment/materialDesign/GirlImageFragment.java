package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.model.GirlModel;
import com.mao.cn.learnDevelopProject.ui.adapter.GirlImageAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IGirlImageView;
import com.mao.cn.learnDevelopProject.ui.presenter.GirlImagePresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.GirlImagePresenterImpl;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

public class GirlImageFragment extends BaseFragment implements IGirlImageView {


    @BindView(R.id.rv)
    RecyclerView rv;

    private GirlImagePresenter mGirlImagePresenter;
    private GirlImageAdapter mPullTopAdapter;

    public static GirlImageFragment newInstance() {
        GirlImageFragment fragment = new GirlImageFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_girl_image;
    }

    @Override
    public void initView() {

        mGirlImagePresenter = new GirlImagePresenterImpl(this);
        Observable.interval(0, 5, TimeUnit.SECONDS).compose(timer()).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mGirlImagePresenter.getGirlImage(100, 1);
                        LogU.e("时间 " + aLong);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogU.e(throwable.getMessage());
                    }
                }
        );

        rv.setLayoutManager(new LinearLayoutManager(context));
        mPullTopAdapter = new GirlImageAdapter(context);
        rv.setAdapter(mPullTopAdapter);
    }

    @Override
    public void setListener() {

     /*   RxView.clicks(tvTitle).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            RxBus.get().post(new RefreshMainEvent());
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });*/


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public void showGirlImage(List<GirlModel> girlModels) {
        if (!checkActivityState()) return;
        if (girlModels.size() > 20) {
            mPullTopAdapter.addGirl(girlModels.subList(20, girlModels.size() - 20));
        } else {
            mPullTopAdapter.addGirl(girlModels);

        }
    }
}
