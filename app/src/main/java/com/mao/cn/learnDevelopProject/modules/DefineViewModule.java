// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/28/2017 11:40 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.modules;


import com.mao.cn.learnDevelopProject.interactors.DefineViewInteractor;
import com.mao.cn.learnDevelopProject.ui.features.IDefineView;
import com.mao.cn.learnDevelopProject.ui.presenter.DefineViewPresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.DefineViewPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class DefineViewModule {

    private IDefineView viewInterface;

    public DefineViewModule(IDefineView viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public IDefineView getViewInterface() {
        return viewInterface;
    }
    @Provides
    public DefineViewPresenter getPresenter(IDefineView viewInterface, DefineViewInteractor defineViewInteractor){
        return new DefineViewPresenterImp(viewInterface,defineViewInteractor);
    }
}