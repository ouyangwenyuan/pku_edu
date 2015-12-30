package com.pku.pkuapp.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCommonUtils {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDateString(Date date) {
        return simpleDateFormat.format(date);
    }

    public static String getTodayStr() {
        return getDateString(getToday());
    }

    public static Date getToday() {
        return new Date();
    }

    public static Date getDateFromString(String format) {
        try {
            return simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  getToday();
    }

    public static SimpleDateFormat getFormat() {
        return simpleDateFormat;
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFirstDayOfMonth() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        return simpleDateFormat.format(lastDate.getTime());
    }

    /**
     * 计算当月最后一天,返回字符串
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDefaultDay() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        return simpleDateFormat.format(lastDate.getTime());
    }

    public static String combineDate(int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.MONTH, month - 1);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取当前日期指定前或者后几天
     *
     * @param number 往前或者往后推的时间
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getOneDay(int number) {
        return getOneDay(number, getToday());
    }

    /**
     * 获取制定日期指定前或者后几天
     *
     * @param number 往前或者往后推的时间
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getOneDay(int number, String dateString) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            date =  getToday();
        }
        return getOneDay(number, date);
    }

    /**
     * @param number
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getOneDay(int number, Date date) {
        // 定义日期格式化

        // 得到日历
        Calendar calendar = Calendar.getInstance();

        // 把时间赋值给日历
        calendar.setTime(date);
        // 设置日历时间
        calendar.add(Calendar.DAY_OF_MONTH, number);

        // 得到日期
        return simpleDateFormat.format(calendar.getTime());
    }

    public static Date getAnyDayFromToday(int number) {
        return getAnyDayFromDay(number, getToday());
    }

    public static Date getAnyDayFromDay(int number, Date date) {

        // 得到日历
        Calendar calendar = Calendar.getInstance();

        // 把时间赋值给日历
        calendar.setTime(date);
        // 设置日历时间
        calendar.add(Calendar.DAY_OF_MONTH, number);

        // 得到日期
        return calendar.getTime();
    }

    //static SimpleDateFormat sdf = new SimpleDateFormat("E");
    static String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};

    public static String oneDayWeekDay(int number, Date date) {
        // 得到日历
        Calendar calendar = Calendar.getInstance();
        // 把时间赋值给日历
        calendar.setTime(date);
        // 设置日历时间
        calendar.add(Calendar.DAY_OF_MONTH, number);
        return weeks[calendar.get(Calendar.DAY_OF_WEEK) - 1];//String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
    }
    public static String dateToWeek(int day){
        return weeks[day];
    }

    public static Date getMonthofDay(int number, Date date) {
        // 得到日历
        Calendar calendar = Calendar.getInstance();
        // 把时间赋值给日历
        calendar.setTime(date);
        // 设置日历时间
        calendar.add(Calendar.DAY_OF_MONTH, number);
        return calendar.getTime();
    }

    /**
     * 计算时间相差几天
     *
     * @param stTime  时间一,开始时间
     * @param endTime 时间二,结束时间
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static int timeDifference(String stTime, String endTime) {
        try {
            Date begin = simpleDateFormat.parse(stTime);
            Date end = simpleDateFormat.parse(endTime);
            return daysBetween(begin, end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取两个日期之间的差
     *
     * @param early
     * @param late
     * @return
     */
    public static int daysBetween(Date early, Date late) {

        Calendar earlyCal = Calendar.getInstance();
        Calendar lateCal = Calendar.getInstance();
        earlyCal.setTime(early);
        lateCal.setTime(late);
        //设置时间为0时
        earlyCal.set(Calendar.HOUR_OF_DAY, 0);
        earlyCal.set(Calendar.MINUTE, 0);
        earlyCal.set(Calendar.SECOND, 0);
        lateCal.set(Calendar.HOUR_OF_DAY, 0);
        lateCal.set(Calendar.MINUTE, 0);
        lateCal.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (lateCal.getTime().getTime() / 1000) - (int) (earlyCal
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }


    /**
     * 获取某个日期与当前日期的时间差（天数）
     *
     * @param late
     * @return
     */
    public static final int getDaysDiffToday(Date late) {
        return daysBetween(getToday(), late);
    }

    /**
     * 判断是否为合法的日期时间字符串
     *
     * @param str_input
     * @param str_input
     * @return boolean;符合为true,不符合为false
     */
    public static boolean isDate(String str_input, String rDateFormat) {
        if (!isNull(str_input)) {
            SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
            formatter.setLenient(false);
            try {
                formatter.format(formatter.parse(str_input));
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isNull(String str) {
        if (str == null)
            return true;
        else
            return false;
    }
}

