package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.mapop;

import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.Observer;

/**
 * @author zhangkun
 * @time 2020/10/18 10:32 PM
 * @Descriptionr  U 下游
 */
public abstract class BaseFuseableObserver<T, U> implements Observer<T> {

    protected final Observer<U> actual;

    // 参数 actual 就是下游的 Observer
    public BaseFuseableObserver(Observer<U> actual) {
        this.actual = actual;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
