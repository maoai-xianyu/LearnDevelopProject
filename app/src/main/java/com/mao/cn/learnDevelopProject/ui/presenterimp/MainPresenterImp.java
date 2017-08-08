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


    @Override
    public void getMovieTop(int start, int count) {
        if (!NetworkUtils.isConnected(context)) {
            viewInterface.onTip(context.getString(R.string.no_connect_net));
            return;
        }
        viewInterface.showLoadingDialog("");
        interactor.getMovieTop(start, count, new StringCallback() {
            @Override
            public void success(String response) {
                viewInterface.hideLoadingDialog();
                Logger.i(response);
                Movie convert = null;
                try {
                    convert = GsonU.convert(response, Movie.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                if (convert != null && StringU.isNotEmpty(convert.getTitle()) && ListU.notEmpty(convert.getSubjects())) {
                    viewInterface.showTopMovie(convert.getSubjects(), convert.getTitle());
                } else {
                    viewInterface.showTopMovie(null, "");
                }
            }

            @Override
            public void failure(RetrofitError var1) {
                viewInterface.hideLoadingDialog();
                viewInterface.interError(var1);
                viewInterface.showTopMovie(null, "");
            }
        });

    }
}
