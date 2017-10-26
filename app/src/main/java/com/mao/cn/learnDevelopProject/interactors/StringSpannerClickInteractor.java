// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 10/19/2017 15:57 下午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.interactors;

import com.mao.cn.learnDevelopProject.callBack.StringCallback;
import com.mao.cn.learnDevelopProject.http.HttpApi;
import com.mao.cn.learnDevelopProject.http.RestApiAdapter;
import com.mao.cn.learnDevelopProject.utils.tools.utils.MD5;

import java.util.UUID;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class StringSpannerClickInteractor {

    @Inject
    public StringSpannerClickInteractor() {

    }

    public void getWordTranslate(String query, String from, String to, StringCallback callback) {
        String appid = "20171025000090792";
        String salt = UUID.randomUUID().toString();
        String key = "MOu3QBe92fHEyEX8P1Tk";
        String sign = MD5.GetMD5Code(appid + query + salt + key);
        HttpApi httpApi = RestApiAdapter.getBaiduDictionary().create(HttpApi.class);
        Call<String> call = httpApi.getWordTranslateFromBaidu(query, from, to, appid, salt, sign);
        call.enqueue(callback);
    }
}
