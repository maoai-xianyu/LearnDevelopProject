package com.mao.cn.learnDevelopProject.useDesign.d_14_shareOralPattern;

import java.util.Random;

/**
 * @author zhangkun
 * @time 2020-06-02 22:55
 * @Description 火车票
 */
public class Ticket {

    public String from;
    public String to;

    public Ticket(String from, String to) {
        this.from = from;
        this.to = to;
    }


    public int getPrice() {
        return new Random().nextInt(100) + 20;
    }
}
