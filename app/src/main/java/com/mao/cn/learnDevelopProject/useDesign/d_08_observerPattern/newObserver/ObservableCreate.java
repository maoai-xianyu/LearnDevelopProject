package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver;

/**
 * @author zhangkun
 * @time 2020/10/18 8:59 PM
 * @Description 具体的被观察者
 */
public class ObservableCreate<T> extends Observable<T> {

    private ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        observer.onSubscribe();
        CreateEmitter emitter = new CreateEmitter(observer);
        source.subscribe(emitter);

    }

    /**
     * 发射器的实现类
     */

    static final class CreateEmitter<T> implements Emitter<T> {
        final Observer<T> observer;

        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {// Emitter Update
            observer.onNext(t); // Observer Update

        }

        @Override
        public void onError(Throwable t) {
            observer.onError(t);
        }

        @Override
        public void onComplete() {
            observer.onComplete();

        }
    }


}
