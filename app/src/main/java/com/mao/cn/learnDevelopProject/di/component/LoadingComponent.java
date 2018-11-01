// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:36 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.component;

import com.mao.cn.learnDevelopProject.di.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.di.interactors.LoadingInteractor;
import com.mao.cn.learnDevelopProject.di.modules.LoadingModule;
import com.mao.cn.learnDevelopProject.ui.activity.LoadingActivity;
import com.mao.cn.learnDevelopProject.ui.features.ILoading;
import com.mao.cn.learnDevelopProject.ui.presenter.LoadingPresenter;

import dagger.Component;

@ActivityScope

@Component(
    dependencies = AppComponent.class,
    modules = LoadingModule.class
)
public interface LoadingComponent {
    void inject(LoadingActivity instance);

    ILoading getViewInterface();

    LoadingPresenter getPresenter();

    LoadingInteractor getLoadingInteractor();

}
