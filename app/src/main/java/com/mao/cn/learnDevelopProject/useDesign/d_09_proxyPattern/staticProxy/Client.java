package com.mao.cn.learnDevelopProject.useDesign.d_09_proxyPattern.staticProxy;

/**
 * @author zhangkun
 * @time 2020-05-26 17:24
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        Man man = new Man("Z");
        BankWorker bankWorker = new BankWorker(man);
        bankWorker.applyBank();
    }
}
