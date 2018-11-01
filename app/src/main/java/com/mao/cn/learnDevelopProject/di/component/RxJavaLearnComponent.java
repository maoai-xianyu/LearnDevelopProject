// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 18:35 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.component;

import com.mao.cn.learnDevelopProject.di.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.di.interactors.RxJavaLearnInteractor;
import com.mao.cn.learnDevelopProject.di.modules.RxJavaLearnModule;
import com.mao.cn.learnDevelopProject.ui.activity.RxJavaLearnActivity;
import com.mao.cn.learnDevelopProject.ui.features.IRxJavaLearn;
import com.mao.cn.learnDevelopProject.ui.presenter.RxJavaLearnPresenter;

import dagger.Component;

@ActivityScope

@Component(
    dependencies = AppComponent.class,
    modules = RxJavaLearnModule.class
)
public interface RxJavaLearnComponent {
    void inject(RxJavaLearnActivity instance);

    IRxJavaLearn getViewInterface();

    RxJavaLearnPresenter getPresenter();

    RxJavaLearnInteractor getRxJavaLearnInteractor();

}
