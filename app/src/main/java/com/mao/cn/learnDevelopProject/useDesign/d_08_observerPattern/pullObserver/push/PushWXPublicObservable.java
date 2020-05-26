package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.pullObserver.push;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-21 14:17
 * @Description 微信公众号
 * 微信公众号  多个人去订阅我们的公众号
 */
public class PushWXPublicObservable {

    // 所有的订阅用户
    public List<IPushWXUser> mIPushWXUsers;

    public PushWXPublicObservable() {
        mIPushWXUsers = new ArrayList<>();
    }

    /**
     * 订阅
     *
     * @param IPushWXUser
     */
    public void register(IPushWXUser IPushWXUser) {
        mIPushWXUsers.add(IPushWXUser);
    }


    /**
     * 取消订阅
     *
     * @param IPushWXUser
     */
    public void unregister(IPushWXUser IPushWXUser) {
        if (mIPushWXUsers.contains(IPushWXUser)) {
            mIPushWXUsers.remove(IPushWXUser);
        }
    }


    /**
     * 更新文章
     *
     * @param article
     */
    public void update(String article) {
        for (IPushWXUser IPushWXUser : mIPushWXUsers) {
            IPushWXUser.push(article);
        }
    }
}
