// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 16:39 下午
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
public class RetrofitShowContentInteractor {

    @Inject
    public RetrofitShowContentInteractor(){

    }

    public void getMovieTop(int start, int count, StringCallback callback) {
        HttpApi httpApi = RestApiAdapter.getStringInstance().create(HttpApi.class);
        Call<String> call = httpApi.getMovieTop(start, count);
        call.enqueue(callback);
    }
}
