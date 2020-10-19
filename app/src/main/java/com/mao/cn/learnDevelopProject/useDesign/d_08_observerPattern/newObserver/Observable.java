package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver;

import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.mapop.Function;
import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.mapop.ObservableMap;

/**
 * @author zhangkun
 * @time 2020/10/18 8:59 PM
 * @Description 具体的被观察者
 */
public abstract class Observable<T> implements ObservableSource<T> {

    @Override
    public void subscribeObserver(Observer<T> observer) {

        // map  flatmap  concatMap ...
        // observer.next(msg);
        subscribeActual(observer);

    }

    // 实现链式调用
    protected abstract void subscribeActual(Observer<T> observer);

    // 创建具体的被观察者，给程序员用
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<>(source);
    }

    // map操作
    public <U> Observable map(Function<T, U> function) {
        return new ObservableMap(this, function);
    }
    // 创建线程切换

}
