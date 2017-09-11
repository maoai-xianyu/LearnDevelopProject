// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/11/2017 11:53 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.modules;


import com.mao.cn.learnDevelopProject.interactors.MainGuideInteractor;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.ui.presenter.MainGuidePresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.MainGuidePresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class MainGuideModule {

    private IMainGuide viewInterface;

    public MainGuideModule(IMainGuide viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public IMainGuide getViewInterface() {
        return viewInterface;
    }
    @Provides
    public MainGuidePresenter getPresenter(IMainGuide viewInterface, MainGuideInteractor mainGuideInteractor){
        return new MainGuidePresenterImp(viewInterface,mainGuideInteractor);
    }
}