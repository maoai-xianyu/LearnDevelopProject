package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhangkun
 * @time 2020-02-24 16:18
 */
public class NoOwnerInvocationHandler implements InvocationHandler {

    PersonBean mPersonBean;

    public NoOwnerInvocationHandler(PersonBean personBean) {
        mPersonBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            return method.invoke(mPersonBean, args);
        } else if (method.getName().equals("setHotOrNotRating")) {
            return method.invoke(mPersonBean, args);
        } else if (method.getName().startsWith("set")) {
            return new IllegalAccessException();
        }
        return null;
    }
}
