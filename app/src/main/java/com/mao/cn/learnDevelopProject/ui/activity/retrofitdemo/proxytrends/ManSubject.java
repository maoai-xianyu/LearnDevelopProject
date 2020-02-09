package com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo.proxytrends;

/**
 * @author zhangkun
 * @time 2020-02-09 09:38
 */
// 被代理类
public class ManSubject implements Subject {
    @Override
    public void shopping() {
        System.out.println("Mjj 要去买东西");
    }
}
