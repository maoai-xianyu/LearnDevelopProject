// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 10/19/2017 15:57 下午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.modules;


import com.mao.cn.learnDevelopProject.di.interactors.StringSpannerClickInteractor;
import com.mao.cn.learnDevelopProject.ui.features.IStringSpannerClick;
import com.mao.cn.learnDevelopProject.ui.presenter.StringSpannerClickPresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.StringSpannerClickPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class StringSpannerClickModule {

    private IStringSpannerClick viewInterface;

    public StringSpannerClickModule(IStringSpannerClick viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public IStringSpannerClick getViewInterface() {
        return viewInterface;
    }
    @Provides
    public StringSpannerClickPresenter getPresenter(IStringSpannerClick viewInterface, StringSpannerClickInteractor stringSpannerClickInteractor){
        return new StringSpannerClickPresenterImp(viewInterface,stringSpannerClickInteractor);
    }
}