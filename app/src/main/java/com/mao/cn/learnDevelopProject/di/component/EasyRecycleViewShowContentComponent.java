package com.mao.cn.learnDevelopProject.di.component;

import com.mao.cn.learnDevelopProject.di.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.di.interactors.EasyRecycleViewShowContentInteractor;
import com.mao.cn.learnDevelopProject.di.modules.EasyRecycleViewShowContentModule;
import com.mao.cn.learnDevelopProject.ui.activity.EasyRecycleViewShowContentActivity;
import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.EasyRecycleViewShowContentPresenter;

import dagger.Component;

/**
 * author:  zhangkun .
 * date:    on 2018/11/1.
 */

@ActivityScope

@Component(
        dependencies = AppComponent.class,
        modules = EasyRecycleViewShowContentModule.class
)
public interface EasyRecycleViewShowContentComponent {

    void inject(EasyRecycleViewShowContentActivity instance);

    IEasyRecycleViewShowContent getViewInterface();

    EasyRecycleViewShowContentPresenter getPresenter();

    EasyRecycleViewShowContentInteractor getRxJavaLearnInteractor();
}
