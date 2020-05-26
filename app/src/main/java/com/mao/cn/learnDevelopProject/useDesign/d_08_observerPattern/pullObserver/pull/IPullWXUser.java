package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.pullObserver.pull;

/**
 * @author zhangkun
 * @time 2020-05-21 14:19
 * @Description 微信公众号  -  微信用户
 */
public interface IPullWXUser {
    /**
     * 把公众号给用户
     * @param pullPublicObservable
     */
    public void pull(PullPublicObservable pullPublicObservable);
}
