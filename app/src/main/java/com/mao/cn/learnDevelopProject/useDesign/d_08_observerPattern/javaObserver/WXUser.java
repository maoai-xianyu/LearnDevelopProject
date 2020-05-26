package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.javaObserver;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zhangkun
 * @time 2020-05-21 14:29
 * @Description 微信公众号  - 具体订阅用户
 */
public class WXUser implements Observer {


    private String name;

    public WXUser(String name) {
        this.name = name;
    }


    /**
     * o 是具体的公众号   WXAdvanceObservable  被观察者
     * arg 是具体推送的文章
     *
     * 推拉模式
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        WXAdvanceObservable wxAdvanceObservable = (WXAdvanceObservable) o;

        System.out.println("文章更新了，赶紧去看吧  拉模式 " + name + "  内容是 " + wxAdvanceObservable.getArticle());

        System.out.println("文章更新了，赶紧去看吧  推模式 " + name + "  内容是 " + arg);
    }
}
