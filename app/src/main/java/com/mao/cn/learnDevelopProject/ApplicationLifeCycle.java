package com.mao.cn.learnDevelopProject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * @author zhangkun
 * @time 2021/3/6 6:15 PM
 * @Description
 */
public class ApplicationLifeCycle implements LifecycleObserver {

    /**
     * 应用程序的整个生命周期中只调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        LogU.d("应用 onCreate 应用程序的整个生命周期中只调用一次");
    }

    /**
     * 当应用程序在前台出现时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        LogU.d("应用 onStart 当应用在前台出现是被调用");

    }

    /**
     * 当应用程序在前台出现时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        LogU.d("应用 onResume 当应用程序在前台出现时被调用");
    }

    /**
     * 当应用程序退出到后台时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        LogU.d("应用 onPause 当应用程序退出到后台时被调用");
    }


    /**
     * 当应用程序退出到后台时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        LogU.d("应用 onStop 当应用程序退出到后台时被调用");
    }

    /**
     * 永远不会调用，系统不会分发调用 ON_DESTROY 事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        LogU.d("应用 onStop 永远不会调用，系统不会分发调用 ON_DESTROY 事件");
    }

}
