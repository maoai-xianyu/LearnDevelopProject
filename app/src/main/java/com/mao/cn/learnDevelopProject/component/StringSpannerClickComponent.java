// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 10/19/2017 15:57 下午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.component;


import com.mao.cn.learnDevelopProject.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.interactors.StringSpannerClickInteractor;
import com.mao.cn.learnDevelopProject.modules.StringSpannerClickModule;
import com.mao.cn.learnDevelopProject.ui.activity.StringSpannerClickActivity;
import com.mao.cn.learnDevelopProject.ui.features.IStringSpannerClick;
import com.mao.cn.learnDevelopProject.ui.presenter.StringSpannerClickPresenter;

import dagger.Component;

@ActivityScope

@Component(
        dependencies = AppComponent.class,
        modules = StringSpannerClickModule.class
)
public interface StringSpannerClickComponent {
    void inject(StringSpannerClickActivity instance);

    IStringSpannerClick getViewInterface();

    StringSpannerClickPresenter getPresenter();

    StringSpannerClickInteractor getStringSpannerClickInteractor();

}
