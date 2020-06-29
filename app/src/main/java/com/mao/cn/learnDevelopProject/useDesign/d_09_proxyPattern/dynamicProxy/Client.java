package com.mao.cn.learnDevelopProject.useDesign.d_09_proxyPattern.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author zhangkun
 * @time 2020-05-26 17:24
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        Man man = new Man("Z");


        // 返回的是 IBank 的实例对象，这个对象是由java给创建的
        IBank proxy = (IBank) Proxy.newProxyInstance(
                IBank.class.getClassLoader(), // ClassLoader
                new Class<?>[]{IBank.class}, // 目标接口
                new BankInvocationHandler(man));

        // 调用这个方法的时候会来到 bankInvocationHandler 的 invoke 方法
        proxy.applyBank();


        proxy.lossBank();
    }
}
