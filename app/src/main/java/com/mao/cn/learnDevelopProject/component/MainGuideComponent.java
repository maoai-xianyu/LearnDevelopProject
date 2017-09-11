// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/11/2017 11:53 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.component;


import com.mao.cn.learnDevelopProject.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.interactors.MainGuideInteractor;
import com.mao.cn.learnDevelopProject.modules.MainGuideModule;
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
