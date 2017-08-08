// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 08/04/2017 16:53 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.component;

import com.mao.cn.learnDevelopProject.component.commons.ActivityScope;
import com.mao.cn.learnDevelopProject.interactors.OkhttpShowContentInteractor;
import com.mao.cn.learnDevelopProject.modules.OkhttpShowContentModule;
import com.mao.cn.learnDevelopProject.ui.activity.OkhttpShowContentActivity;
import com.mao.cn.learnDevelopProject.ui.features.IOkhttpShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.OkhttpShowContentPresenter;

import dagger.Component;

@ActivityScope

@Component(
    dependencies = AppComponent.class,
    modules = OkhttpShowContentModule.class
)
public interface OkhttpShowContentComponent {
    void inject(OkhttpShowContentActivity instance);

    IOkhttpShowContent getViewInterface();

    OkhttpShowContentPresenter getPresenter();

    OkhttpShowContentInteractor getOkhttpShowContentInteractor();

}
