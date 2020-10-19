package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver;

import com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.mapop.Function;

/**
 * @author zhangkun
 * @time 2020/10/18 9:49 PM
 * @Description
 */
public class MainTest {
    public static void main(String[] args) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("大家好");
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String t) {
                // 上游是 t
                // 下游是 U String

                System.out.println("t  是上游");
                String u = t + "下游1";

                return u;
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String t) {
                // 上游是 t
                // 下游是 U String

                System.out.println("t  是上游");
                String u = t + "下游2";
                return u;
            }
        }).subscribeObserver(new Observer<String>() {
            @Override
            public void onNext(String s) {

                System.out.println("sss " + s);

            }

            @Override
            public void onSubscribe() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
