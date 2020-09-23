package com.mao.cn.learnDevelopProject.utils;

import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static final ThreadLocal<DateFormat> DATE_FORMATER = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-M-d");
        }
    };

    public static final ThreadLocal<DateFormat> DATETIME_FORMATER = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-M-d HH:mm");
        }
    };

    public static final ThreadLocal<DateFormat> FORMATER = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };

    public static final ThreadLocal<DateFormat> HOUR_MINUTE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };

    public static final ThreadLocal<DateFormat> MINUTE_SECOND_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }
    };

    public static final ThreadLocal<DateFormat> MONTH_DAY_HOUR_MINUTE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm");
        }
    };

    public static final ThreadLocal<DateFormat> YEAR_MONTH_DAY_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static final ThreadLocal<DateFormat> DATETIME_FORMATER_POINT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy.MM.dd");
        }
    };

    public static String getDateByMillis(long time) {
        return FORMATER.get().format(new Date(time));
    }

    public static String getMinuteSecondFormatDate(long time) {
        return MINUTE_SECOND_FORMAT.get().format(new Date(time));
    }

    public static String getHourMinuteFormatDate(long time) {
        return HOUR_MINUTE_FORMAT.get().format(new Date(time));
    }

    public static String getMonthDayHourMinuteFormatDate(long time) {
        return MONTH_DAY_HOUR_MINUTE_FORMAT.get().format(new Date(time));
    }

    public static Date getDateTime(String time) {
        try {
            return FORMATER.get().parse(time);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String getYearMonthDayFormatDate(String date) {
        Date d;
        try {
            d = DATE_FORMATER.get().parse(date);
        } catch (ParseException e) {
            d = new Date();
        }
        return YEAR_MONTH_DAY_FORMAT.get().format(d);
    }

    public static long getTimeInMillis(String time) {
        return getDateTime(time).getTime();
    }


    public static String getDatePoint(long time) {
        return DATETIME_FORMATER_POINT.get().format(new Date(time));
    }

    public static String getDate(long time) {
        return DATE_FORMATER.get().format(new Date(time));
    }

    /* 截取日期 */
    public static Calendar getDate(Date when) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(when);
        int year = canlendar.get(Calendar.YEAR);
        int month = canlendar.get(Calendar.MONTH);
        int date = canlendar.get(Calendar.DATE);

        canlendar.clear();
        canlendar.set(year, month, date);
        return canlendar;
    }

    public static Date getTime(String time) {
        try {
            return DATETIME_FORMATER.get().parse(time);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 获取今天凌晨时间
     *
     * @return 当日凌晨的时间，以毫秒为单位
     */
    public static Calendar getToday() {
        return getDate(new Date());
    }

    public static boolean isToday(long time) {
        return DateUtils.isToday(time);
    }


    public static String dateFormat(String[] date) {
        if (date[1].length() == 1) {
            date[1] = "0" + date[1];
        }
        if (date[2].length() == 1) {
            date[2] = "0" + date[2];
        }
        StringBuffer result = new StringBuffer();
        result.append(date[0]);
        result.append("-");
        result.append(date[1]);
        result.append("-");
        result.append(date[2]);
        return result.toString();
    }

    public static boolean isCurrentWeek(String time) {
        Calendar calendar = getToday();
        int dayofweek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7 + 1;
        long sundayTime = System.currentTimeMillis() + (7 - dayofweek) * 24
            * 60 * 60 * 1000l;
        long mondayTime = System.currentTimeMillis() - (dayofweek - 1) * 24
            * 60 * 60 * 1000l;
        String sunday = DATE_FORMATER.get().format(sundayTime);
        String monday = DATE_FORMATER.get().format(mondayTime);
        String[] sundayDate = sunday.split("-");
        String[] mondayDate = monday.split("-");
        sunday = dateFormat(sundayDate);
        monday = dateFormat(mondayDate);
        if (time.compareTo(monday) >= 0 && time.compareTo(sunday) <= 0) {
            return true;
        }
        return false;
    }
}