package com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.builder;

import com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.vacation.Vacation;

/**
 * @author zhangkun
 * @time 2020-02-27 14:12
 */
public abstract class AbsBuilder {
    public Vacation mVacation;

    public AbsBuilder(String std) {
        mVacation = new Vacation(std);
    }

    public abstract void buildVacation();

    public abstract void buildDay(int i);

    public abstract void addHotel(String hotel);

    public abstract void addTicket(String ticket);

    public abstract void addEvent(String event);

    public Vacation getVacation() {
        return mVacation;
    }
}
