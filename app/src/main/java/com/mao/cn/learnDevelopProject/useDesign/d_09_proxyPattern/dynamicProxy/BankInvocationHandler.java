package com.mao.cn.learnDevelopProject.useDesign.d_09_proxyPattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhangkun
 * @time 2020-05-26 17:33
 * @Description
 */
public class BankInvocationHandler implements InvocationHandler {


    /**
     * 被代理对象
     */
    private Object mObject;


    public BankInvocationHandler(Object object) {
        mObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 执行方法，目标接口调用的方法都会到这个里面

        System.out.println("methodNamw = " + method.getName());
        System.out.println("开始受理");
        Object invoke = method.invoke(mObject, args);
        System.out.println("受理完毕");
        // 调用被代理的对象的方法
        return invoke;
    }
}
