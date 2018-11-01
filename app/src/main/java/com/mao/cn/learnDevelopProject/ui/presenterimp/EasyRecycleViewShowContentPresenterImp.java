package com.mao.cn.learnDevelopProject.ui.presenterimp;

import com.google.gson.JsonSyntaxException;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.callBack.StringCallback;
import com.mao.cn.learnDevelopProject.converter.RetrofitError;
import com.mao.cn.learnDevelopProject.di.interactors.EasyRecycleViewShowContentInteractor;
import com.mao.cn.learnDevelopProject.model.Movie;
import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.EasyRecycleViewShowContentPresenter;
import com.mao.cn.learnDevelopProject.utils.network.NetworkUtils;
import com.mao.cn.learnDevelopProject.utils.tools.GsonU;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;

/**
 * author:  zhangkun .
 * date:    on 2018/11/1.
 */
public class EasyRecycleViewShowContentPresenterImp extends BasePresenterImp implements EasyRecycleViewShowContentPresenter {


    private IEasyRecycleViewShowContent viewInterface;
    private EasyRecycleViewShowContentInteractor interactor;

    public EasyRecycleViewShowContentPresenterImp(IEasyRecycleViewShowContent viewInterface, EasyRecycleViewShowContentInteractor retrofitShowContentInteractor) {
        super();
        this.viewInterface = viewInterface;
        this.interactor = retrofitShowContentInteractor;
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
                LogU.i(response);
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
                viewInterface.showErrorMovie();
            }
        });

    }
}
