// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:36 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.modules;


import com.mao.cn.learnDevelopProject.interactors.LoadingInteractor;
import com.mao.cn.learnDevelopProject.ui.features.ILoading;
import com.mao.cn.learnDevelopProject.ui.presenter.LoadingPresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.LoadingPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class LoadingModule {

    private ILoading viewInterface;

    public LoadingModule(ILoading viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public ILoading getViewInterface() {
        return viewInterface;
    }
    @Provides
    public LoadingPresenter getPresenter(ILoading viewInterface,LoadingInteractor loadingInteractor){
        return new LoadingPresenterImp(viewInterface,loadingInteractor);
    }
}