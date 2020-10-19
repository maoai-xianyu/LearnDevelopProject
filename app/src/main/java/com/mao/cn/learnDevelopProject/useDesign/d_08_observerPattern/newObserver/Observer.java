package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver;

/**
 * @author zhangkun
 * @time 2020/10/18 11:16 AM
 * @Description 观察者
 */
public interface Observer<T> {

    // 接收消息
    void onNext(T t);
    // 建立关联，用这个api通知
    void onSubscribe();
    // 处理错误
    void onError(Throwable e);
    // 接收消息完成
    void onComplete();
}
