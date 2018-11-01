// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 16:39 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.modules;


import com.mao.cn.learnDevelopProject.di.interactors.RetrofitShowContentInteractor;
import com.mao.cn.learnDevelopProject.ui.features.IRetrofitShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.RetrofitShowContentPresenter;
import com.mao.cn.learnDevelopProject.ui.presenterimp.RetrofitShowContentPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitShowContentModule {

    private IRetrofitShowContent viewInterface;

    public RetrofitShowContentModule(IRetrofitShowContent viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Provides
    public IRetrofitShowContent getViewInterface() {
        return viewInterface;
    }
    @Provides
    public RetrofitShowContentPresenter getPresenter(IRetrofitShowContent viewInterface,RetrofitShowContentInteractor retrofitShowContentInteractor){
        return new RetrofitShowContentPresenterImp(viewInterface,retrofitShowContentInteractor);
    }
}