// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 10/19/2017 15:57 下午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.presenterimp;

import com.mao.cn.learnDevelopProject.interactors.StringSpannerClickInteractor;
import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IStringSpannerClick;
import com.mao.cn.learnDevelopProject.ui.presenter.StringSpannerClickPresenter;

/**
* DESC   :
* AUTHOR : Xabad
*/
public class StringSpannerClickPresenterImp extends BasePresenterImp implements StringSpannerClickPresenter {
    StringSpannerClickInteractor interactor;
    IStringSpannerClick viewInterface;
    public StringSpannerClickPresenterImp(IStringSpannerClick viewInterface,StringSpannerClickInteractor stringSpannerClickInteractor) {
        super();
        this.viewInterface = viewInterface;
        this.interactor = stringSpannerClickInteractor;
    }
}
