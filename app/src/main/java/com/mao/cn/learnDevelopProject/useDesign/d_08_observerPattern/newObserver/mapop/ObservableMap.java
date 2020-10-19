package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.mapop;

import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.ObservableSource;
import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.Observer;

/**
 * @author zhangkun
 * @time 2020/10/18 10:22 PM
 * @Description
 */
public class ObservableMap<T,U> extends AbstractObservableWithUpStream<T,U> {

    final Function<T,U> function;

    public ObservableMap(ObservableSource<T> source, Function<T, U> function) {
        super(source);
        this.function = function;
    }


    // 真实的功能
    @Override
    protected void subscribeActual(Observer observer) {
        // 被观察者绑定一个观察者
        //source.subscribeObserver(observer);
        source.subscribeObserver(new MapObserver<>(observer,function));
    }

    static final class MapObserver<T,U> extends BaseFuseableObserver<T,U>{

        final Function<T,U> mapper;

        public MapObserver(Observer<U> actual, Function<T, U> mapper) {
            super(actual);
            this.mapper = mapper;
        }

        @Override
        public void onNext(T t) {
            U apply = mapper.apply(t);
            actual.onNext(apply);
        }
    }



}
