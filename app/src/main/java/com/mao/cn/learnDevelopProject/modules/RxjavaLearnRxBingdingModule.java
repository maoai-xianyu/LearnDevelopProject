// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/16/2017 15:27 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.modules;


import com.mao.cn.learnDevelopProject.interactors.RxjavaLearnRxBingdingInteractor;
import com.mao.cn.learnDevelopProject.ui.features.IRxjavaLearnRxBingding;
import com.mao.cn.learnDevelopProject.ui.presenter.RxjavaLearnRxBingdingPresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.RxjavaLearnRxBingdingPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class RxjavaLearnRxBingdingModule {

    private IRxjavaLearnRxBingding viewInterface;

    public RxjavaLearnRxBingdingModule(IRxjavaLearnRxBingding viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public IRxjavaLearnRxBingding getViewInterface() {
        return viewInterface;
    }
    @Provides
    public RxjavaLearnRxBingdingPresenter getPresenter(IRxjavaLearnRxBingding viewInterface,RxjavaLearnRxBingdingInteractor rxjavaLearnRxBingdingInteractor){
        return new RxjavaLearnRxBingdingPresenterImp(viewInterface,rxjavaLearnRxBingdingInteractor);
    }
}