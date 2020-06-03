package com.mao.cn.learnDevelopProject.useDesign.d_14_shareOralPattern;

/**
 * @author zhangkun
 * @time 2020-06-02 23:00
 * @Description  享元设计模式
 */
public class Client {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {

            Ticket ticket = TicketFactory.getTicket("北京", "大同");
            ticket.getPrice();

        }
    }
}
