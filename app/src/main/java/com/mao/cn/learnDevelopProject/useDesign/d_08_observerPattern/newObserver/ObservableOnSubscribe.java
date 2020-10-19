package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver;

/**
 * @author zhangkun
 * @time 2020/10/18 9:27 PM
 * @Description 用来绑定发射器   如果被观察者需要绑定发射器，使用这个接口就可以实现
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);
}
