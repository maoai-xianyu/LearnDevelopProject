package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.pullObserver.pull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-21 14:17
 * @Description 微信公众号
 * 微信公众号  多个人去订阅我们的公众号
 */
public class PullPublicObservable {

    // 所有的订阅用户
    public List<IPullWXUser> mIPullWXUsers;

    public PullPublicObservable() {
        mIPullWXUsers = new ArrayList<>();
    }

    /**
     * 订阅
     *
     * @param IPullWXUser
     */
    public void register(IPullWXUser IPullWXUser) {
        mIPullWXUsers.add(IPullWXUser);
    }


    /**
     * 取消订阅
     *
     * @param IPullWXUser
     */
    public void unregister(IPullWXUser IPullWXUser) {
        if (mIPullWXUsers.contains(IPullWXUser)) {
            mIPullWXUsers.remove(IPullWXUser);
        }
    }


    /**
     * 更新文章
     *
     * @param article
     */
    public void update(String article) {
        for (IPullWXUser IPullWXUser : mIPullWXUsers) {
            IPullWXUser.pull(this);
        }
    }
}
