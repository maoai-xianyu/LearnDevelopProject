// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/28/2017 11:40 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.presenterimp;

import com.mao.cn.learnDevelopProject.di.interactors.DefineViewInteractor;
import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IDefineView;
import com.mao.cn.learnDevelopProject.ui.presenter.DefineViewPresenter;

/**
* DESC   :
* AUTHOR : Xabad
*/
public class DefineViewPresenterImp extends BasePresenterImp implements DefineViewPresenter {
    DefineViewInteractor interactor;
    IDefineView viewInterface;
    public DefineViewPresenterImp(IDefineView viewInterface, DefineViewInteractor defineViewInteractor) {
        super();
        this.viewInterface = viewInterface;
        this.interactor = defineViewInteractor;
    }
}
