package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.observer;

/**
 * @author zhangkun
 * @time 2020-05-21 14:26
 * @Description 具体的被观察者
 */
public class WXAdvanceObservable extends WXPublicObservable {

    private String article;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
        // 通知更新，推送给微信用户
        update(article);
    }
}
