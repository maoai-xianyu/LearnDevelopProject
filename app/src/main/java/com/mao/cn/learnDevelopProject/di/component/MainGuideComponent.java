// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/11/2017 11:53 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.component;


import com.mao.cn.learnDevelopProject.di.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.di.interactors.MainGuideInteractor;
import com.mao.cn.learnDevelopProject.di.modules.MainGuideModule;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.ui.fragment.MainGuideFragment;
import com.mao.cn.learnDevelopProject.ui.presenter.MainGuidePresenter;

import dagger.Component;

@ActivityScope

@Component(
        dependencies = AppComponent.class,
        modules = MainGuideModule.class
)
public interface MainGuideComponent {
    void inject(MainGuideFragment instance);

    IMainGuide getViewInterface();

    MainGuidePresenter getPresenter();

    MainGuideInteractor getMainGuideInteractor();

}
