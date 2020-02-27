package com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.vacation;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author zhangkun
 * @time 2020-02-27 13:48
 */
public class VacationDay {
    private Date mDate;
    private String mHotels;
    private ArrayList<String> mTickets = null;
    private ArrayList<String> mEvents = null;

    public VacationDay(Date date) {
        mDate = date;
        mTickets = new ArrayList<>();
        mEvents = new ArrayList<>();
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setHotels(String hotels) {
        mHotels = hotels;
    }

    public void addTicket(String ticket) {
        mTickets.add(ticket);
    }

    public void addEvent(String event) {
        mEvents.add(event);
    }

    public String showInfo() {
        StringBuilder stb = new StringBuilder();
        stb.append("Date: ").append(mDate.toString()).append("\n");
        stb.append("Hotel: ").append(mHotels).append("\n");
        stb.append("mTickets: ").append(mTickets.toString()).append("\n");
        stb.append("mEvents: ").append(mEvents.toString()).append("\n");
        return stb.toString();
    }
}
