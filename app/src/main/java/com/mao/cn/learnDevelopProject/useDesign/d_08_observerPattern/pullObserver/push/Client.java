package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.pullObserver.push;

/**
 * @author zhangkun
 * @time 2020-05-21 14:31
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        // 微信公众号 - 具体的被观察者
        PushPushWXAdvanceObservable pushWxAdvanceObservable = new PushPushWXAdvanceObservable();
        // 微信公众号 - 具体的观察者
        PushWXUser pushWxUserD =  new PushWXUser("大坤");
        PushWXUser pushWxUserZ =  new PushWXUser("大楠");
        // 微信公众号 - 用户订阅公众号
        pushWxAdvanceObservable.register(pushWxUserD);
        pushWxAdvanceObservable.register(pushWxUserZ);
        // 微信公众号 - 推送文章
        pushWxAdvanceObservable.setArticle("今天是521 要好好爱哦");

        pushWxAdvanceObservable.unregister(pushWxUserZ);

        pushWxAdvanceObservable.setArticle("今天是521 今天又更新了 要好好爱哦");


    }
}
