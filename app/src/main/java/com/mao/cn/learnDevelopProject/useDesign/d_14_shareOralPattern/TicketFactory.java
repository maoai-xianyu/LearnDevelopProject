package com.mao.cn.learnDevelopProject.useDesign.d_14_shareOralPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangkun
 * @time 2020-06-02 22:55
 * @Description 火车票的查询工厂
 */
public class TicketFactory {

    // 做一个缓存
    static Map<String, Ticket> stringTicketMap = new HashMap<>();

    public static Ticket getTicket(String from, String to) {
        String key = from + "-" + to;
        Ticket ticket = stringTicketMap.get(key);

        if (ticket != null) {
            return ticket;
        }
        ticket = new Ticket(from, to);
        stringTicketMap.put(key, ticket);
        return ticket;
    }

}
