package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.newObserver.mapop;

/**
 * @author zhangkun
 * @time 2020/10/18 10:23 PM
 * @Description  事件变换  泛型 上游和下游
 */
public interface Function<T,R> {

    R apply(T t);
}
