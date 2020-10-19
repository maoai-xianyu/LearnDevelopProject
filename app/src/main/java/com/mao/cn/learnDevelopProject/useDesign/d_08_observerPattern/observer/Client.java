package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.observer;

/**
 * @author zhangkun
 * @time 2020-05-21 14:31
 * @Description  被观察者绑定观察者，被观察者发生改变，通知观察者更新
 */
public class Client {

    public static void main(String[] args) {

        // 微信公众号 - 具体的被观察者
        WXAdvanceObservable wxAdvanceObservable = new WXAdvanceObservable();
        // 微信公众号 - 具体的观察者
        WXUser wxUserD =  new WXUser("大坤");
        WXUser wxUserZ =  new WXUser("大楠");
        // 微信公众号 - 用户订阅公众号
        wxAdvanceObservable.register(wxUserD);
        wxAdvanceObservable.register(wxUserZ);
        // 微信公众号 - 推送文章
        wxAdvanceObservable.setArticle("今天是521 要好好爱哦");

        wxAdvanceObservable.unregister(wxUserZ);

        wxAdvanceObservable.setArticle("今天是521 今天又更新了 要好好爱哦");


    }
}
