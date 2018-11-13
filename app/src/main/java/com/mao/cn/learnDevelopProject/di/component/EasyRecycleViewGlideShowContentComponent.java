package com.mao.cn.learnDevelopProject.di.component;

import com.mao.cn.learnDevelopProject.di.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.di.modules.EasyRecycleViewGlideShowContentModule;
import com.mao.cn.learnDevelopProject.ui.activity.EasyRecycleViewGlideShowContentActivity;
import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewGlideShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.EasyRecycleViewGlideShowContentPresenter;

import dagger.Component;

/**
 * author:  zhangkun .
 * date:    on 2018/11/1.
 */

@ActivityScope

@Component(
        dependencies = AppComponent.class,
        modules = EasyRecycleViewGlideShowContentModule.class
)
public interface EasyRecycleViewGlideShowContentComponent {

    void inject(EasyRecycleViewGlideShowContentActivity instance);

    IEasyRecycleViewGlideShowContent getViewInterface();

    EasyRecycleViewGlideShowContentPresenter getPresenter();

}
