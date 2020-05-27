package com.mao.cn.learnDevelopProject.useDesign.d_09_proxyPattern.staticProxy;

/**
 * @author zhangkun
 * @time 2020-05-26 17:18
 * @Description 银行办理业务 - 银行的业务员 代理对象
 */
public class BankWorker implements IBank {

    private IBank mIBank;


    /**
     * 持有代理对象
     * @param iBank
     */
    public BankWorker(IBank iBank) {
        mIBank = iBank;
    }

    @Override
    public void applyBank() {

        System.out.println("银行业务员 代理对象 给 被代理对象办卡  start");

        mIBank.applyBank();

        System.out.println("银行业务员 代理对象 给 被代理对象办卡  ebd");
    }
}
