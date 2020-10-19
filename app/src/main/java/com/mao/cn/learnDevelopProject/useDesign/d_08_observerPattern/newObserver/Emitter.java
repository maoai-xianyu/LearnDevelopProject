package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver;

/**
 * @author zhangkun
 * @time 2020/10/18 9:18 PM
 * @Description   // 抽离发送消息
 * 给observer发送消息用的,和被观察者绑定在一起使用
 */
public interface Emitter<T>{

    void onNext(T t);

    void onError(Throwable t);

    void onComplete();
}
