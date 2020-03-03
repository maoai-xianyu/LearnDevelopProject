package com.mao.cn.learnDevelopProject.ui.presenterimp;

import com.mao.cn.learnDevelopProject.callBack.GsonCallback;
import com.mao.cn.learnDevelopProject.converter.RetrofitError;
import com.mao.cn.learnDevelopProject.http.HttpApi;
import com.mao.cn.learnDevelopProject.http.RestApiAdapter;
import com.mao.cn.learnDevelopProject.model.BaseEntity;
import com.mao.cn.learnDevelopProject.model.GirlModel;
import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IGirlImageView;
import com.mao.cn.learnDevelopProject.ui.presenter.GirlImagePresenter;

import retrofit2.Call;

/**
 * @author zhangkun
 * @time 2020-03-03 14:44
 */
public class GirlImagePresenterImpl extends BasePresenterImp implements GirlImagePresenter {

    private IGirlImageView mIGirlImageView;

    public GirlImagePresenterImpl(IGirlImageView IGirlImageView) {
        mIGirlImageView = IGirlImageView;
    }

    public void getGirlImageNet(int num, int page, GsonCallback<BaseEntity<GirlModel>> callback) {
        HttpApi httpApi = RestApiAdapter.getGirlImage().create(HttpApi.class);
        Call<BaseEntity<GirlModel>> call = httpApi.getGirls(num, page);
        call.enqueue(callback);
    }

    @Override
    public void getGirlImage(int num, int page) {
        getGirlImageNet(num, page, new GsonCallback<BaseEntity<GirlModel>>() {
            @Override
            public void success(BaseEntity<GirlModel> var1) {
                mIGirlImageView.showGirlImage(var1.getResults());
            }

            @Override
            public void failure(RetrofitError var1) {
                mIGirlImageView.interError(var1);
            }
        });

    }
}
