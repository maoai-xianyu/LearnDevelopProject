// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/28/2017 11:40 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.component;


import com.mao.cn.learnDevelopProject.di.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.di.interactors.DefineViewInteractor;
import com.mao.cn.learnDevelopProject.di.modules.DefineViewModule;
import com.mao.cn.learnDevelopProject.ui.activity.DefineViewActivity;
import com.mao.cn.learnDevelopProject.ui.features.IDefineView;
import com.mao.cn.learnDevelopProject.ui.presenter.DefineViewPresenter;

import dagger.Component;

@ActivityScope

@Component(
        dependencies = AppComponent.class,
        modules = DefineViewModule.class
)
public interface DefineViewComponent {
    void inject(DefineViewActivity instance);

    IDefineView getViewInterface();

    DefineViewPresenter getPresenter();

    DefineViewInteractor getDefineViewInteractor();

}
