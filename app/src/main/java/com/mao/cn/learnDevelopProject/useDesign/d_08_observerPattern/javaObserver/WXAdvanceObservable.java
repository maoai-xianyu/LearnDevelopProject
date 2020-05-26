package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.javaObserver;

import java.util.Observable;

/**
 * @author zhangkun
 * @time 2020-05-21 15:16
 * @Description 具体的被观察者
 */
public class WXAdvanceObservable extends Observable {

    private String article;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
        // 更新文章
        this.setChanged();
        notifyObservers(article);
    }
}
