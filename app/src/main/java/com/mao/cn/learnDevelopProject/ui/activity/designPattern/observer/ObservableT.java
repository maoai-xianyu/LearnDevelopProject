package com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-25 11:20
 * @Description 被观察者
 */
public class ObservableT<M, T extends ObserverT<M>> {

    private List<T> observables;


    public ObservableT() {

        observables = new ArrayList<>();
    }

    public void register(T observer) {
        observables.add(observer);
    }

    public void unRegister(T observer) {
        observables.remove(observer);
    }


    public void update(M observer) {

        for (T observable : observables) {
            observable.update(observer);
        }
    }


}
