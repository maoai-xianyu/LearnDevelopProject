package com.mao.cn.learnDevelopProject.useDesign.d_09_proxyPattern.staticProxy;

/**
 * @author zhangkun
 * @time 2020-05-26 17:19
 * @Description 银行办理业务 - 被代理对象
 */
public class Man implements IBank {

    private String name;

    public Man(String name) {
        this.name = name;
    }

    @Override
    public void applyBank() {

        System.out.println(name + "被代理对象 我 去银行 办理业务");

    }
}
