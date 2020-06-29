package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.javaObserver;


/**
 * @author zhangkun
 * @time 2020-05-21 14:31
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        // 微信公众号 - 具体的被观察者
        WXAdvanceObservable wxAdvanceObservable = new WXAdvanceObservable();
        // 微信公众号 - 具体的观察者
        WXUser wxUserD =  new WXUser("大坤");
        WXUser wxUserZ =  new WXUser("大楠");
        // 微信公众号 - 用户订阅公众号
        wxAdvanceObservable.addObserver(wxUserD);
        wxAdvanceObservable.addObserver(wxUserZ);
        // 微信公众号 - 推送文章
        wxAdvanceObservable.setArticle("今天是521 要好好爱哦");

        wxAdvanceObservable.deleteObserver(wxUserZ);

        wxAdvanceObservable.setArticle("今天是521 今天又更新了 要好好爱哦");


    }
}
