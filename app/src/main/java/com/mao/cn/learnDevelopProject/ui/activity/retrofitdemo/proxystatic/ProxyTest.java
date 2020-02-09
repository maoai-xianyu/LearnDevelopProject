package com.mao.cn.learnDevelopProject.ui.activity.retrofitdemo.proxystatic;

/**
 * @author zhangkun
 * @time 2020-02-08 18:15
 */
public class ProxyTest {
    public static void main(String[] args) {
        AbstractObject proxyObject = new ProxyObject(new RealObject());

        proxyObject.operation();
    }
}
