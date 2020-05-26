package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.pullObserver.pull;

/**
 * @author zhangkun
 * @time 2020-05-21 14:29
 * @Description 微信公众号  - 具体订阅用户
 */
public class PullWXUser implements IPullWXUser {


    private String name;

    public PullWXUser(String name) {
        this.name = name;
    }

    @Override
    public void pull(PullPublicObservable publicObservable) {
        // 拉模式  - 主动从公众号拉取文章
        PullPullAdvanceObservable pullAdvanceObservable = (PullPullAdvanceObservable) publicObservable;
        System.out.println(name + " 主动拉取一篇文章： " + pullAdvanceObservable.getArticle());

    }
}
