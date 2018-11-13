package com.mao.cn.learnDevelopProject.di.modules;

import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewGlideShowContent;
import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.EasyRecycleViewGlideShowContentPresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.EasyRecycleViewShowGlideContentPresenterImp;

import dagger.Module;
import dagger.Provides;

/**
 * author:  zhangkun .
 * date:    on 2018/11/13.
 */
@Module
public class EasyRecycleViewGlideShowContentModule {

    private IEasyRecycleViewGlideShowContent viewInterface;

    public EasyRecycleViewGlideShowContentModule(IEasyRecycleViewGlideShowContent viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public IEasyRecycleViewGlideShowContent getViewInterface() {
        return viewInterface;
    }

    @Provides
    public EasyRecycleViewGlideShowContentPresenter getPresenter(IEasyRecycleViewGlideShowContent viewInterface) {
        return new EasyRecycleViewShowGlideContentPresenterImp(viewInterface);
    }
}
