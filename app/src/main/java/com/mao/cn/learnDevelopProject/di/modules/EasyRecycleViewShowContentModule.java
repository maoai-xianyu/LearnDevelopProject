package com.mao.cn.learnDevelopProject.di.modules;

import com.mao.cn.learnDevelopProject.di.interactors.EasyRecycleViewShowContentInteractor;
import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.EasyRecycleViewShowContentPresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.EasyRecycleViewShowContentPresenterImp;

import dagger.Module;
import dagger.Provides;

/**
 * author:  zhangkun .
 * date:    on 2018/11/1.
 */
@Module
public class EasyRecycleViewShowContentModule {

    private IEasyRecycleViewShowContent viewInterface;

    public EasyRecycleViewShowContentModule(IEasyRecycleViewShowContent viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public IEasyRecycleViewShowContent getViewInterface() {
        return viewInterface;
    }
    @Provides
    public EasyRecycleViewShowContentPresenter getPresenter(IEasyRecycleViewShowContent viewInterface, EasyRecycleViewShowContentInteractor retrofitShowContentInteractor){
        return new EasyRecycleViewShowContentPresenterImp(viewInterface,retrofitShowContentInteractor);
    }
}
