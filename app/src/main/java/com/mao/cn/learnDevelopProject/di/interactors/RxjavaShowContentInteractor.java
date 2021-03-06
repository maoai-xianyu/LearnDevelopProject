// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 08/04/2017 16:53 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.di.interactors;

import com.mao.cn.learnDevelopProject.http.HttpApi;
import com.mao.cn.learnDevelopProject.http.RestApiAdapter;

import javax.inject.Inject;

import rx.Observable;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class RxjavaShowContentInteractor {

    @Inject
    public RxjavaShowContentInteractor() {

    }

    public Observable<String> getMovieTop(int start, int count) {
        HttpApi httpApi = RestApiAdapter.getStringInstance().create(HttpApi.class);
        return httpApi.getTodayMovie(start, count);
    }


}
