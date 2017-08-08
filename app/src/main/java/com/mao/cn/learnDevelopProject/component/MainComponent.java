// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:16 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.component;

import com.mao.cn.learnDevelopProject.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.interactors.MainInteractor;
import com.mao.cn.learnDevelopProject.modules.MainModule;
import com.mao.cn.learnDevelopProject.ui.activity.MainActivity;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.ui.presenter.MainPresenter;

import dagger.Component;

@ActivityScope

@Component(
    dependencies = AppComponent.class,
    modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity instance);

    IMain getViewInterface();

    MainPresenter getPresenter();

    MainInteractor getMainInteractor();

}
