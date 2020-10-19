package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.thread;

import android.os.Handler;
import android.os.Looper;

import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.ObservableSource;
import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.Observer;
import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.mapop.AbstractObservableWithUpStream;

/**
 * @author zhangkun
 * @time 2020/10/18 10:22 PM
 * @Description
 */
public class ObservableObserveOn<T> extends AbstractObservableWithUpStream {


    public ObservableObserveOn(ObservableSource source) {
        super(source);
    }


    @Override
    protected void subscribeActual(Observer observer) {
        final ObserverOnObserver<T> parent = new ObserverOnObserver<>(observer);
        source.subscribeObserver(parent);

    }


    static final class ObserverOnObserver<T> implements Observer<T> {

        final Observer<T> actual;
        private Handler mHandler;


        public ObserverOnObserver(Observer<T> actual) {
            this.actual = actual;
            mHandler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void onNext(T t) {

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onNext(t);
                }
            });


        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();

        }

        @Override
        public void onError(Throwable e) {
            actual.onError(e);

        }

        @Override
        public void onComplete() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onComplete();
                }
            });

        }

    }

}
