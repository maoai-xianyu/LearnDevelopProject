// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 16:39 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.component;

import com.mao.cn.learnDevelopProject.di.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.di.interactors.RetrofitShowContentInteractor;
import com.mao.cn.learnDevelopProject.di.modules.RetrofitShowContentModule;
import com.mao.cn.learnDevelopProject.ui.activity.RetrofitShowContentActivity;
import com.mao.cn.learnDevelopProject.ui.features.IRetrofitShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.RetrofitShowContentPresenter;

import dagger.Component;

@ActivityScope

@Component(
    dependencies = AppComponent.class,
    modules = RetrofitShowContentModule.class
)
public interface RetrofitShowContentComponent {
    void inject(RetrofitShowContentActivity instance);

    IRetrofitShowContent getViewInterface();

    RetrofitShowContentPresenter getPresenter();

    RetrofitShowContentInteractor getRetrofitShowContentInteractor();

}
