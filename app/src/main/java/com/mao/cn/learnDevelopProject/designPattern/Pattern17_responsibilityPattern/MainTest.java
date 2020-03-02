package com.mao.cn.learnDevelopProject.designPattern.Pattern17_responsibilityPattern;

/**
 * @author zhangkun
 * @time 2020-03-02 11:29
 */
public class MainTest {

    public static void main(String[] args) {

        Client client = new Client();
        Approver approverGroup = new GroupApprover("T");
        Approver approverDepartment = new DepartmentApprover("k");
        Approver approverVicePresident = new VicePresidentApprover("M");
        Approver approverPresident = new PresidentApprover("P");

        // 线性或者环性，有顺序或者无顺序
        approverGroup.setApprover(approverDepartment);
        approverDepartment.setApprover(approverVicePresident);
        approverVicePresident.setApprover(approverPresident);
        approverPresident.setApprover(approverGroup);

        approverGroup.ProcessRequest(client.setRequest(1,100,40));
        approverGroup.ProcessRequest(client.setRequest(2,200,40));
        approverGroup.ProcessRequest(client.setRequest(3,300,40));
        approverGroup.ProcessRequest(client.setRequest(4,400,140));

    }
}
