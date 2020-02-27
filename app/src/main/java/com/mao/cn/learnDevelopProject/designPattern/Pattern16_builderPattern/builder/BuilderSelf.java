package com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.builder;

import com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.vacation.Vacation;

/**
 * @author zhangkun
 * @time 2020-02-27 23:28
 */
public class BuilderSelf {

    public Vacation mVacation;

    public BuilderSelf(String std) {
        mVacation = new Vacation(std);
    }

    public BuilderSelf addDay() {
        mVacation.addDay();
        return this;
    }

    public BuilderSelf buildDay(int i) {
        mVacation.setVacationDay(i);
        return this;
    }

    public BuilderSelf addHotel(String hotel) {
        mVacation.setHotel(hotel);
        return this;
    }

    public BuilderSelf addTicket(String ticket) {
        mVacation.addTicket(ticket);
        return this;
    }

    public BuilderSelf addEvent(String event) {
        mVacation.addEvent(event);
        return this;
    }

    public Vacation getVacation() {
        return mVacation;
    }
}
