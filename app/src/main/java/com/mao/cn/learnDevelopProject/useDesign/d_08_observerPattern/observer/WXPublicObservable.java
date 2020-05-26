package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-21 14:17
 * @Description 微信公众号
 * 微信公众号  多个人去订阅我们的公众号
 */
public class WXPublicObservable {

    // 所有的订阅用户
    public List<IWXUser> mIWXUsers;

    public WXPublicObservable() {
        mIWXUsers = new ArrayList<>();
    }

    /**
     * 订阅
     *
     * @param iwxUser
     */
    public void register(IWXUser iwxUser) {
        mIWXUsers.add(iwxUser);
    }


    /**
     * 取消订阅
     *
     * @param iwxUser
     */
    public void unregister(IWXUser iwxUser) {
        if (mIWXUsers.contains(iwxUser)) {
            mIWXUsers.remove(iwxUser);
        }
    }


    /**
     * 更新文章
     *
     * @param article
     */
    public void update(String article) {
        for (IWXUser iwxUser : mIWXUsers) {
            iwxUser.push(article);
        }
    }
}
