package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 */
public class JetPackActivityObserver implements  LifecycleObserver{

    private Lifecycle mLifecycle;

    public JetPackActivityObserver(Lifecycle lifecycle) {
        mLifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateEvent() {

        LogU.i("ON_CREATE");
        LogU.i("当前宿主生命周期 = "+mLifecycle.getCurrentState().name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStartEvent() {
        LogU.i("ON_START");
        LogU.i("当前宿主生命周期 = "+mLifecycle.getCurrentState().name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResumeEvent() {
        LogU.i("ON_RESUME");
        LogU.i("当前宿主生命周期 = "+mLifecycle.getCurrentState().name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPauseEvent() {

        LogU.i("ON_PAUSE");
        LogU.i("当前宿主生命周期 = "+mLifecycle.getCurrentState().name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStopEvent() {

        LogU.i("ON_STOP");
        LogU.i("当前宿主生命周期 = "+mLifecycle.getCurrentState().name());
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestoryEvent() {
        LogU.i("ON_DESTROY");
        LogU.i("当前宿主生命周期 = "+mLifecycle.getCurrentState().name());
    }
}
