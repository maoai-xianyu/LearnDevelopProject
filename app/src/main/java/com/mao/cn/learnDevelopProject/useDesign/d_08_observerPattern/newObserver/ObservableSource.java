package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver;

/**
 * @author zhangkun
 * @time 2020/10/18 11:17 AM
 * @Description 被观察者
 */
public interface ObservableSource<T> {
    // 绑定Observable和 Observer的联系  订阅 add(Observer)

    void subscribeObserver(Observer<T> observer);

}
