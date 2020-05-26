package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.pullObserver.pull;

/**
 * @author zhangkun
 * @time 2020-05-21 14:31
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        // 微信公众号 - 具体的被观察者
        PullPullAdvanceObservable pullWxAdvanceObservable = new PullPullAdvanceObservable();
        // 微信公众号 - 具体的观察者
        PullWXUser pullWxUserD =  new PullWXUser("大坤");
        PullWXUser pullWxUserZ =  new PullWXUser("大楠");
        // 微信公众号 - 用户订阅公众号
        pullWxAdvanceObservable.register(pullWxUserD);
        pullWxAdvanceObservable.register(pullWxUserZ);
        // 微信公众号 - 推送文章
        pullWxAdvanceObservable.setArticle("今天是521 要好好爱哦");

        pullWxAdvanceObservable.unregister(pullWxUserZ);

        pullWxAdvanceObservable.setArticle("今天是521 今天又更新了 要好好爱哦");


    }
}
