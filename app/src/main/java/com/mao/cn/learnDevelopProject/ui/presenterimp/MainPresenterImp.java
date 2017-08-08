// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:17 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.presenterimp;

import com.google.gson.JsonSyntaxException;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.callBack.StringCallback;
import com.mao.cn.learnDevelopProject.converter.RetrofitError;
import com.mao.cn.learnDevelopProject.interactors.MainInteractor;
import com.mao.cn.learnDevelopProject.model.Movie;
import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.ui.presenter.MainPresenter;
import com.mao.cn.learnDevelopProject.utils.network.NetworkUtils;
import com.mao.cn.learnDevelopProject.utils.tools.GsonU;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;
import com.orhanobut.logger.Logger;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class MainPresenterImp extends BasePresenterImp implements MainPresenter {
    MainInteractor interactor;
    IMain viewInterface;

    public MainPresenterImp(IMain viewInterface, MainInteractor mainInteractor) {
        super();
        this.viewInterface = viewInterface;
        this.interactor = mainInteractor;
    }
}
