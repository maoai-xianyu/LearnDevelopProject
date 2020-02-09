package com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo.proxytrends;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhangkun
 * @time 2020-02-09 09:40
 */
// 代理类
public class ProxySubject implements InvocationHandler {
    private Object target; // 要代理的真是对象

    public ProxySubject(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:" + proxy.getClass().getName());
        System.out.println("before...");
        method.invoke(target, args);
        System.out.println("after...");
        return null;
    }
}
