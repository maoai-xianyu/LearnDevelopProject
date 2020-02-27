package com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.builder;

/**
 * @author zhangkun
 * @time 2020-02-27 14:15
 */
public class Builder4d extends  AbsBuilder {

    public Builder4d(String std) {
        super(std);
    }

    @Override
    public void buildVacation() {
        addTicket("Plane Ticket");
        addEvent("fly to destination");
        addEvent("supper");
        addEvent("dancing");
        addHotel("Four Sessions");

        mVacation.addDay();
        addTicket("Theme park");
        addEvent("Bus to park");
        addEvent("lunch");
        addHotel("Four Sessions");

        mVacation.addDay();
        addTicket("Water Ticket");
        addEvent("Swim");
        addHotel("Home in");

        mVacation.addDay();
        addTicket("Plane Ticket");
        addEvent("City Tour");
        addEvent("Fly to Home");

    }

    @Override
    public void buildDay(int i) {
        mVacation.setVacationDay(i);

    }

    @Override
    public void addHotel(String hotel) {
        mVacation.setHotel(hotel);
    }

    @Override
    public void addTicket(String ticket) {
        mVacation.addTicket(ticket);
    }

    @Override
    public void addEvent(String event) {
        mVacation.addEvent(event);
    }
}
