// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:17 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.interactors;

import com.mao.cn.learnDevelopProject.callBack.StringCallback;
import com.mao.cn.learnDevelopProject.http.HttpApi;
import com.mao.cn.learnDevelopProject.http.RestApiAdapter;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class MainInteractor {

    @Inject
    public MainInteractor() {

    }

    public void getMovieTop(int start, int count, StringCallback callback) {
        HttpApi httpApi = RestApiAdapter.getStringInstance().create(HttpApi.class);
        Call<String> call = httpApi.getMovieTop(start, count);
        call.enqueue(callback);
    }
}
