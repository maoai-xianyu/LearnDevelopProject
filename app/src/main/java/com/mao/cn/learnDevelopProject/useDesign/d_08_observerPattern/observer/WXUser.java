package com.mao.cn.learnDevelopProject.useDesign.d_08_observerPattern.observer;

/**
 * @author zhangkun
 * @time 2020-05-21 14:29
 * @Description 微信公众号  - 具体订阅用户
 */
public class WXUser implements IWXUser {


    private String name;

    public WXUser(String name) {
        this.name = name;
    }

    @Override
    public void push(String article) {
        System.out.println("文章更新了，赶紧去看吧 "+ name+"  内容是 " + article);
    }
}
