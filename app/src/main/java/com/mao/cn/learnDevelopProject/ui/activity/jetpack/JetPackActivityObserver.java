package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 */
public class JetPackActivityObserver implements LifecycleObserver {

    private String TAG = this.getClass().getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateEvent() {
        LogU.i(TAG + "Observer ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStartEvent() {
        LogU.i(TAG + "Observer ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResumeEvent() {
        LogU.i(TAG + "Observer ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPauseEvent() {
        LogU.i(TAG + "Observer ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStopEvent() {
        LogU.i(TAG + "Observer ON_STOP");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestoryEvent() {
        LogU.i(TAG + "Observer ON_DESTROY");
    }
}
