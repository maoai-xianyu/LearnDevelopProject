package com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo.proxytrends;

/**
 * @author zhangkun
 * @time 2020-02-09 10:05
 */
public class Client {
    public static void main(String[] args) {
        Subject manSubject = new ManSubject();
        ProxySubject p = new ProxySubject(manSubject);
        // 通过java.lang,reflect.newProxyInstance(...)方法获得真实对象的代理对象
        Subject subject = (Subject) java.lang.reflect.Proxy.newProxyInstance(
                manSubject.getClass().getClassLoader(),
                manSubject.getClass().getInterfaces(),
                p);

        // 通过代理对象调用真实对象相关接口中实现的方法，这个时候就会跳转到这个代理对象所关联的Handler的invoke()方法
        subject.shopping();
        // 获得真实对象的代理对象所对应的Class对象的名称，用字符串表示
        System.out.println(subject.getClass().getName());


    }
}
