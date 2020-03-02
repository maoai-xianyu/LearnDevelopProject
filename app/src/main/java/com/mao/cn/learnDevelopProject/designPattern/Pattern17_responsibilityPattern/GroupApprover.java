package com.mao.cn.learnDevelopProject.designPattern.Pattern17_responsibilityPattern;

/**
 * @author zhangkun
 * @time 2020-03-02 11:36
 */
public class GroupApprover extends Approver {

    public GroupApprover(String name) {
        super(name + "Group Leader");
    }

    @Override
    public void ProcessRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getSum() < 5000) {
            System.out.println("**this request**" + purchaseRequest.getID() + " will be handled by " + this.name + "**");
        }else {
            mApprover.ProcessRequest(purchaseRequest);
        }

    }
}
