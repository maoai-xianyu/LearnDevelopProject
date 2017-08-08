package com.mao.cn.learnDevelopProject.ui.commons;

import android.content.Context;

import com.mao.cn.learnDevelopProject.LearnDevelopApplication;
import com.mao.cn.learnDevelopProject.utils.tools.PreferenceU;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


// +----------------------------------------------------------------------
// | CreateTime: 15/8/12 
// +----------------------------------------------------------------------
// | Author:     xab(http://www.xueyong.net.cn)
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
public abstract class BasePresenterImp implements BasePresenter {
    protected Context context;
    protected PreferenceU preferenceU;

    public BasePresenterImp() {
        this.context = LearnDevelopApplication.context();
        this.preferenceU = PreferenceU.getInstance(LearnDevelopApplication.context());

    }

    protected <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
