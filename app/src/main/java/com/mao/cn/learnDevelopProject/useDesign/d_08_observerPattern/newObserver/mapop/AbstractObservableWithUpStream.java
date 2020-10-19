package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.mapop;

import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.Observable;
import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.ObservableSource;

/**
 * @author zhangkun
 * @time 2020/10/18 10:16 PM
 * @Description 使用装饰者模式扩展 subscribeActual 方法
 *
 * U 是上下游
 */
public abstract class AbstractObservableWithUpStream<T,U>  extends Observable<U> {

    protected final ObservableSource<T> source;

    public AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}
