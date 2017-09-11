// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/11/2017 11:53 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.presenterimp;

import com.mao.cn.learnDevelopProject.interactors.MainGuideInteractor;
import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.ui.presenter.MainGuidePresenter;

/**
* DESC   :
* AUTHOR : Xabad
*/
public class MainGuidePresenterImp extends BasePresenterImp implements MainGuidePresenter {
    MainGuideInteractor interactor;
    IMainGuide viewInterface;
    public MainGuidePresenterImp(IMainGuide viewInterface,MainGuideInteractor mainGuideInteractor) {
        super();
        this.viewInterface = viewInterface;
        this.interactor = mainGuideInteractor;
    }
}
