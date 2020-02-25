package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.dyn;

import java.lang.reflect.Proxy;

/**
 * @author zhangkun
 * @time 2020-02-24 19:19
 */
public class MatchService {

    public MatchService() {
        PersonBean joe = getPersonInfo("joe", "male", "running");

        PersonBean owerProxy = getOwnerProxy(joe);

        System.out.println("name is "+owerProxy.getName());
        System.out.println("interests is "+owerProxy.getInterests());
        owerProxy.setInterests("Bowling");
        System.out.println("interests are "+owerProxy.getInterests());
        owerProxy.setHotOrNotRating(50);
        System.out.println("Rating is "+owerProxy.getHotOrNotRating());
        owerProxy.setHotOrNotRating(40);
        System.out.println("Rating is "+owerProxy.getHotOrNotRating());
        System.out.println("******************");

        PersonBean nowerProxy = getNoOwnerProxy(joe);

        System.out.println("name is "+nowerProxy.getName());
        System.out.println("interests is "+nowerProxy.getInterests());
        nowerProxy.setInterests("haha");
        System.out.println("interests are "+nowerProxy.getInterests());
        nowerProxy.setHotOrNotRating(40);
        System.out.println("Rating is "+nowerProxy.getHotOrNotRating());
    }


    private PersonBean getPersonInfo(String name, String gender, String interest) {
        PersonBean personBean = new PersonBeanImpl();
        personBean.setName(name);
        personBean.setGender(gender);
        personBean.setInterests(interest);
        return personBean;

    }

    PersonBean getOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                personBean.getClass().getInterfaces(), new OwnerInvocationHandler(personBean));

    }

    PersonBean getNoOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                personBean.getClass().getInterfaces(), new NoOwnerInvocationHandler(personBean));

    }
}
