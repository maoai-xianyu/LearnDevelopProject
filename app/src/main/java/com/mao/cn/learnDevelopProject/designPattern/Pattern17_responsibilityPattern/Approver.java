package com.mao.cn.learnDevelopProject.designPattern.Pattern17_responsibilityPattern;

/**
 * @author zhangkun
 * @time 2020-03-02 11:33
 */
public abstract class Approver {

    Approver mApprover;
    String name;

    public Approver(String name) {
        this.name = name;
    }

    public abstract void ProcessRequest(PurchaseRequest purchaseRequest);


    public void setApprover(Approver approver) {
        mApprover = approver;
    }
}
