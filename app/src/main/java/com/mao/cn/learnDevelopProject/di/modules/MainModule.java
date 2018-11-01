// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:17 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.modules;


import com.mao.cn.learnDevelopProject.di.interactors.MainInteractor;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.ui.presenter.MainPresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.MainPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private IMain viewInterface;

    public MainModule(IMain viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public IMain getViewInterface() {
        return viewInterface;
    }
    @Provides
    public MainPresenter getPresenter(IMain viewInterface,MainInteractor mainInteractor){
        return new MainPresenterImp(viewInterface,mainInteractor);
    }
}