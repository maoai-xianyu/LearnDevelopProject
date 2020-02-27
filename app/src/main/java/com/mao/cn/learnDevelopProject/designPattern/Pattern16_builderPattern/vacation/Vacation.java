package com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhangkun
 * @time 2020-02-27 13:59
 */
public class Vacation {
    private ArrayList<VacationDay> mVacationDays;
    private Date mStDate;
    private int mDays = 0;
    private VacationDay mVacationDay;

    public Vacation(String std) {
        mVacationDays = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            mStDate = sdf.parse(std);
            mVacationDay = new VacationDay(mStDate);
            mVacationDays.add(mVacationDay);
            mDays++;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setStDate(String std) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            mStDate = sdf.parse(std);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getStDate() {
        return mStDate;
    }

    public void addDay() {
        mVacationDay = new VacationDay(nextDate(mDays));
        mVacationDays.add(mVacationDay);
        mDays++;
    }

    public boolean setVacationDay(int i) {
        if ((i > 0) && (i < mVacationDays.size())) {
            mVacationDay = mVacationDays.get(i);
            return true;
        }
        mVacationDay = null;
        return false;
    }

    public void setHotel(String mHotels) {
        mVacationDay.setHotels(mHotels);
    }

    public void addTicket(String ticket) {
        mVacationDay.addTicket(ticket);
    }

    public void addEvent(String event) {
        mVacationDay.addEvent(event);
    }

    public void showInfo() {
        for (int i = 0; i < mVacationDays.size(); i++) {
            System.out.println("***" + (i + 1) + "  day***");
            System.out.println(mVacationDays.get(i).showInfo());
        }

    }

    private Date nextDate(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mStDate);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }
}
