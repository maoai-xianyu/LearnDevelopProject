// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/16/2017 15:27 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.presenterimp;

import com.mao.cn.learnDevelopProject.interactors.RxjavaLearnRxBingdingInteractor;
import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IRxjavaLearnRxBingding;
import com.mao.cn.learnDevelopProject.ui.presenter.RxjavaLearnRxBingdingPresenter;

/**
* DESC   :
* AUTHOR : Xabad
*/
public class RxjavaLearnRxBingdingPresenterImp extends BasePresenterImp implements RxjavaLearnRxBingdingPresenter {
    RxjavaLearnRxBingdingInteractor interactor;
    IRxjavaLearnRxBingding viewInterface;
    public RxjavaLearnRxBingdingPresenterImp(IRxjavaLearnRxBingding viewInterface,RxjavaLearnRxBingdingInteractor rxjavaLearnRxBingdingInteractor) {
        super();
        this.viewInterface = viewInterface;
        this.interactor = rxjavaLearnRxBingdingInteractor;
    }
}
