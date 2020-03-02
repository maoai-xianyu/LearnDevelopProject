package com.mao.cn.learnDevelopProject.designPattern.Pattern17_responsibilityPattern;

/**
 * @author zhangkun
 * @time 2020-03-02 11:36
 */
public class DepartmentApprover extends Approver {

    public DepartmentApprover(String name) {
        super(name + "Department Leader");
    }

    @Override
    public void ProcessRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getSum() >= 5000 && purchaseRequest.getSum() < 10000) {
            System.out.println("**this request**" + purchaseRequest.getID() + " will be handled by " + this.name + "**");
        } else {
            mApprover.ProcessRequest(purchaseRequest);
        }

    }
}
