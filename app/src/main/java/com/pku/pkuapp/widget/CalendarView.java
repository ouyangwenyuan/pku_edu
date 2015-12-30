package com.pku.pkuapp.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.pku.pkuapp.R;
import com.pku.pkuapp.utils.DateCommonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xita on 2015/6/29.
 */
public class CalendarView extends ViewFlipper
        implements GestureDetector.OnGestureListener {

    public static final int COLOR_TX_OTHER_MONTH_DAY = Color.parseColor("#ffcccccc"); // 其他月日历数字颜色
    public static final int COLOR_TX_THIS_DAY = Color.parseColor("#ff008000"); // 当天日历数字颜色
    public static final int COLOR_BG_THIS_DAY = Color.parseColor("#ffcccccc"); // 当天日历背景颜色
    public static final int COLOR_BG_WEEK_TITLE = Color.parseColor("#c7b4bc"); // 星期标题背景颜色
    public static final int COLOR_TX_WEEK_TITLE = Color.parseColor("#be2477"); // 星期标题文字颜色
    public static final int COLOR_TX_THIS_MONTH_DAY = Color.parseColor("#ff564b4b"); // 当前月日历数字颜色
    public static final int COLOR_BG_CALENDAR = Color.WHITE; // 日历背景色

    private GestureDetector gd; // 手势监听器
    private Animation push_left_in; // 动画-左进
    private Animation push_left_out; // 动画-左出
    private Animation push_right_in; // 动画-右进
    private Animation push_right_out; // 动画-右出

    private int ROWS_TOTAL = 6; // 日历的行数
    private int COLS_TOTAL = 7; // 日历的列数
    private String[][] dates = new String[6][7]; // 当前日历日期
    private float tb;

    private OnCalendarClickListener onCalendarClickListener; // 日历翻页回调
    private OnCalendarDateChangedListener onCalendarDateChangedListener; // 日历点击回调

    private String[] weekday = new String[]{"日", "一", "二", "三", "四", "五", "六"}; // 星期标题

    private int calendarYear; // 日历年份
    private int calendarMonth; // 日历月份
    private int calendarDate; // select date
    private Date thisday = new Date(); // 今天
    private Date calendarday; // 日历这个月第一天(1号)

    private LinearLayout firstCalendar; // 第一个日历
    private LinearLayout secondCalendar; // 第二个日历
    private LinearLayout currentCalendar; // 当前显示的日历
    //    private Map<String, Integer> marksMap = new HashMap<String, Integer>(); // 储存某个日子被标注(Integer-> resId)
    private Map<String, Integer> dayBgColorMap = new HashMap<String, Integer>(); // 储存某个日子的背景色
    private List<String> mentrualDays = new ArrayList<String>();


    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context) {
        super(context);
        init();

    }


    Map<String, Integer> datas = new HashMap<String, Integer>();


    private void init() {
//        setBackgroundColor(COLOR_BG_CALENDAR);
        setBackgroundResource(R.drawable.show_picture_0);
        //实例化手势监听器
        gd = new GestureDetector(this);
        //初始化日历翻动动画
        push_left_in = AnimationUtils.loadAnimation(getContext(), R.anim.push_left_in);
        push_left_out = AnimationUtils.loadAnimation(getContext(), R.anim.push_left_out);
        push_right_in = AnimationUtils.loadAnimation(getContext(), R.anim.push_right_in);
        push_right_out = AnimationUtils.loadAnimation(getContext(), R.anim.push_right_out);
        push_left_in.setDuration(300);
        push_left_out.setDuration(300);
        push_right_in.setDuration(300);
        push_right_out.setDuration(300);
        //初始化第一个日历
        firstCalendar = new LinearLayout(getContext());
        firstCalendar.setOrientation(LinearLayout.VERTICAL);
        firstCalendar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        // 初始化第二个日历
        secondCalendar = new LinearLayout(getContext());
        secondCalendar.setOrientation(LinearLayout.VERTICAL);
        secondCalendar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        // 设置默认日历为第一个日历
        currentCalendar = firstCalendar;
        // 加入ViewFlipper
        addView(firstCalendar);
        addView(secondCalendar);
        //绘制线条框架
        drawFrame(firstCalendar);
        drawFrame(secondCalendar);
        //设置日历上的日子（1号）
        calendarYear = thisday.getYear() + 1900;
        calendarMonth = thisday.getMonth();
        calendarDate = thisday.getDate();
        calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
        //填充展示日历
        setCalendarDate();
    }

    private void drawFrame(LinearLayout oneCalendar) {

//        // 添加周末线性布局
//        LinearLayout title = new LinearLayout(getContext());
//        title.setBackgroundColor(COLOR_BG_WEEK_TITLE);
//        title.setOrientation(LinearLayout.HORIZONTAL);
//        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(-1, 0,
//                0.5f);
//        Resources res = getResources();
//        tb = res.getDimension(R.dimen.eight_dip);
//        layout.setMargins(0, 0, 0, (int) (res.getDimension(R.dimen.five_dip)));
//        title.setLayoutParams(layout);
//        oneCalendar.addView(title);
//
//        // 添加周末TextView
//        for (int i = 0; i < COLS_TOTAL; i++) {
//            TextView view = new TextView(getContext());
//            view.setGravity(Gravity.CENTER);
//            view.setText(weekday[i]);
//            view.setTextColor(COLOR_TX_WEEK_TITLE);
//            view.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1));
//            title.addView(view);
//            ImageView recordView = new ImageView(getContext());
//            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
//                    (int) getResources().getDimension(R.dimen.three_dip), (int) getResources().getDimension(R.dimen.three_dip));
//            recordView.setLayoutParams(params1);
//            params1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
//            title.addView(recordView);
//        }
        // 添加日期布局
        LinearLayout content = new LinearLayout(getContext());
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 7f));
        oneCalendar.addView(content);

        // 添加日期TextView
        for (int i = 0; i < ROWS_TOTAL; i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
            content.addView(row);
            // 绘制日历上的列
            for (int j = 0; j < COLS_TOTAL; j++) {
                RelativeLayout col = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.calendar_per_day_item, null);
                col.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
                col.setGravity(Gravity.CENTER);
                row.addView(col);
                // 给每一个日子加上监听
                col.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewGroup parent = (ViewGroup) v.getParent();
                        int row = 0, col = 0;

                        // 获取列坐标
                        for (int i = 0; i < parent.getChildCount(); i++) {
                            if (v.equals(parent.getChildAt(i))) {
                                col = i;
                                break;
                            }
                        }
                        // 获取行坐标
                        ViewGroup pparent = (ViewGroup) parent.getParent();
                        for (int i = 0; i < pparent.getChildCount(); i++) {
                            if (parent.equals(pparent.getChildAt(i))) {
                                row = i;
                                break;
                            }
                        }
                        if (onCalendarClickListener != null) {
                            int state = -1;
                            if (datas.containsKey(dates[row][col])) {
                                state = datas.get(dates[row][col]);
                            }
                            onCalendarClickListener.onCalendarClick(row, col,
                                    dates[row][col], state);
                        }
                    }
                });
            }
        }
    }

    /**
     * 填充日历(包含日期、标记、背景等)
     */
    public void setCalendarDate() {
        // 根据日历的日子获取这一天是星期几
        int weekday = calendarday.getDay();
        // 每个月第一天
        int firstDay = 1;
        // 每个月中间号,根据循环会自动++
        int day = firstDay;
        // 每个月的最后一天
        int lastDay = getDateNum(calendarday.getYear(), calendarday.getMonth());
        // 下个月第一天
        int nextMonthDay = 1;
        int lastMonthDay = 1;

        // 填充每一个空格
        for (int i = 0; i < ROWS_TOTAL; i++) {
            for (int j = 0; j < COLS_TOTAL; j++) {
                RelativeLayout group = getDateView(i, j);
                RelativeLayout rl_calendar = (RelativeLayout) group.findViewById(R.id.rl_calendar);
                TextView dateView = (TextView) group.findViewById(R.id.tv_calendar_date);
                TextView mentrualView = (TextView) group.findViewById(R.id.tv_calendar_mentrual_date);
                ImageView firstTag = (ImageView) group.findViewById(R.id.record_status_manterl_start);
                ImageView thirdTag = (ImageView) group.findViewById(R.id.record_status_night);
                firstTag.setVisibility(GONE);
                thirdTag.setVisibility(GONE);
                group.setBackgroundColor(Color.TRANSPARENT);
                dateView.setBackgroundColor(Color.TRANSPARENT);

                // 这个月第一天不是礼拜天,则需要绘制上个月的剩余几天
                if (i == 0 && j == 0 && weekday != 0) {
                    int year = 0;
                    int month = 0;
                    int lastMonthDays = 0;
                    // 如果这个月是1月，上一个月就是去年的12月
                    if (calendarday.getMonth() == 0) {
                        year = calendarday.getYear() - 1;
                        month = java.util.Calendar.DECEMBER;
                    } else {
                        year = calendarday.getYear();
                        month = calendarday.getMonth() - 1;
                    }

                    lastMonthDays = getDateNum(year, month);
                    int firstShowDay = lastMonthDays - weekday + 1;

                    for (int k = 0; k < weekday; k++) {
                        lastMonthDay = firstShowDay + k;
                        RelativeLayout lastMonGroup = getDateView(0, k);
                        TextView dataView = (TextView) lastMonGroup.findViewById(R.id.tv_calendar_date);
                        dataView.setText(Integer.toString(lastMonthDay));
                        dataView.setTextColor(COLOR_TX_OTHER_MONTH_DAY);
//                        dataView.setBackgroundResource(R.drawable.records_calendar_white_bottom);
                        if (calendarday.getMonth() == java.util.Calendar.DECEMBER) {
                            dates[0][k] = format(new Date(
                                    calendarday.getYear() - 1,
                                    java.util.Calendar.JANUARY, lastMonthDay));
                        } else {
                            dates[0][k] = format(new Date(
                                    calendarday.getYear(),
                                    calendarday.getMonth() - 1, lastMonthDay));
                        }
//                        rl_calendar.setBackgroundResource(R.drawable.records_calendar_white_bottom);
                        //dateView.setBackgroundResource(R.drawable.calendar_today_bottom);
                    }
                    j = weekday - 1;
                } else {
                    // 本月
                    if (day <= lastDay) {
                        dates[i][j] = DateCommonUtils.getFormat().format(new Date(calendarday.getYear(),
                                calendarday.getMonth(), day));
                        // MyLog.i("calendar = " + dates[i][j]);
                        dateView.setText(Integer.toString(day));
                        dateView.setTextColor(COLOR_TX_THIS_MONTH_DAY);
                        //当天
                        if (thisday.getDate() == day
                                && thisday.getMonth() == calendarday.getMonth()
                                && thisday.getYear() == calendarday.getYear()) {
                            dateView.setText("今天");
                            dateView.setTextSize(12);
                        }

                        //select day background
                        if (dayBgColorMap != null && dayBgColorMap.containsKey(dates[i][j])) {
                            dateView.setBackgroundResource(dayBgColorMap.get(dates[i][j]));
                        } else {
                            dateView.setBackgroundColor(Color.TRANSPARENT);
                        }

                        // 当天
                        day++;
                        // 下个月
                    } else {
                        if (calendarday.getMonth() == java.util.Calendar.DECEMBER) {
                            dates[i][j] = format(new Date(
                                    calendarday.getYear() + 1,
                                    java.util.Calendar.JANUARY, nextMonthDay));
                        } else {
                            dates[i][j] = format(new Date(
                                    calendarday.getYear(),
                                    calendarday.getMonth() + 1, nextMonthDay));
                        }

                        dateView.setText(Integer.toString(nextMonthDay));
                        dateView.setTextColor(COLOR_TX_OTHER_MONTH_DAY);
                        //dateView.setBackgroundResource(R.drawable.records_calendar_white_bottom);
                        nextMonthDay++;
                    }
                }
            }
        }
    }

    private String format(Date date) {
        return DateCommonUtils.getFormat().format(date);
    }


    /**
     * onClick接口回调
     */
    public interface OnCalendarClickListener {
        void onCalendarClick(int row, int col, String dateFormat, int state);
    }

    /**
     * ondateChange接口回调
     */
    public interface OnCalendarDateChangedListener {
        void onCalendarDateChanged(int year, int month, int date);
    }

    /**
     * 根据具体的某年某月，展示一个日历
     *
     * @param year
     * @param month
     */

    public void showCalendar(int year, int month) {
        calendarYear = year;
        calendarMonth = month - 1;
        calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
        setCalendarDate();
    }

    /**
     * 根据当前月，展示一个日历
     */
    public void showCalendar() {
        Date now = new Date();
        calendarYear = now.getYear() + 1900;
        calendarMonth = now.getMonth();
        calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
        setCalendarDate();
    }

    /**
     * 下一月日历
     */
    public synchronized void nextMonth() {
        // 改变日历上下顺序
        if (currentCalendar == firstCalendar) {
            currentCalendar = secondCalendar;
        } else {
            currentCalendar = firstCalendar;
        }
        currentCalendar.removeAllViews();
        drawFrame(currentCalendar);
        // 设置动画
        setInAnimation(push_left_in);
        setOutAnimation(push_left_out);
        // 改变日历日期
        if (calendarMonth == java.util.Calendar.DECEMBER) {
            calendarYear++;
            calendarMonth = java.util.Calendar.JANUARY;
        } else {
            calendarMonth++;
        }
        calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
        // 填充日历
        setCalendarDate();
        // 下翻到下一月
        showNext();
        // 回调
        if (onCalendarDateChangedListener != null) {
            onCalendarDateChangedListener.onCalendarDateChanged(calendarYear,
                    calendarMonth + 1, calendarDate);
        }
    }

    public synchronized void setDates(int year, int month) {
        // 改变日历上下顺序
//        if (currentCalendar == firstCalendar) {
//            currentCalendar = secondCalendar;
//        } else {
//            currentCalendar = firstCalendar;
//        }
        currentCalendar.removeAllViews();
        drawFrame(currentCalendar);
        // 设置动画
        //setInAnimation(push_left_in);
        //setOutAnimation(push_left_out);
        // 改变日历日期
        calendarYear = year;
        calendarMonth = month;
        calendarDate = thisday.getDate();
        calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
        // 填充日历
        setCalendarDate();
        // 下翻到下一月
        // showNext();
        // 回调
        if (onCalendarDateChangedListener != null) {
            onCalendarDateChangedListener.onCalendarDateChanged(calendarYear,
                    calendarMonth + 1, calendarDate);
        }
    }

    /**
     * 上一月日历
     */
    public synchronized void lastMonth() {
        if (currentCalendar == firstCalendar) {
            currentCalendar = secondCalendar;
        } else {
            currentCalendar = firstCalendar;
        }
        currentCalendar.removeAllViews();
        drawFrame(currentCalendar);
        setInAnimation(push_right_in);
        setOutAnimation(push_right_out);
        if (calendarMonth == java.util.Calendar.JANUARY) {
            calendarYear--;
            calendarMonth = java.util.Calendar.DECEMBER;
        } else {
            calendarMonth--;
        }
        calendarday = new Date(calendarYear - 1900, calendarMonth, 1);
        setCalendarDate();
        showPrevious();
        if (onCalendarDateChangedListener != null) {
            onCalendarDateChangedListener.onCalendarDateChanged(calendarYear,
                    calendarMonth + 1, calendarDate);
        }
    }

//    public synchronized void backToday() {
//        if (calendarday.getTime() > thisday.getTime()) {
//            if (currentCalendar == firstCalendar) {
//                currentCalendar = secondCalendar;
//            } else {
//                currentCalendar = firstCalendar;
//            }
//            setInAnimation(push_right_in);
//            setOutAnimation(push_right_out);
//            calendarday = thisday;
//
//            setCalendarDate();
//            showNext();
//        } else {
//            if (currentCalendar == firstCalendar) {
//                currentCalendar = secondCalendar;
//            } else {
//                currentCalendar = firstCalendar;
//            }
//            // 设置动画
//            setInAnimation(push_left_in);
//            setOutAnimation(push_left_out);
//            calendarday = thisday;
//            setCalendarDate();
//            showPrevious();
//        }
//        calendarMonth = thisday.getMonth();
//        if (onCalendarDateChangedListener != null) {
//            onCalendarDateChangedListener.onCalendarDateChanged(calendarYear,
//                    calendarMonth + 1);
//        }
//
//    }

    /**
     * 获取日历当前年份
     */
    public int getCalendarYear() {
        return calendarday.getYear() + 1900;
    }

    /**
     * 获取日历当前月份
     */
    public int getCalendarMonth() {
        return calendarday.getMonth() + 1;
    }

    public int getCalendarDate() {
        return calendarday.getDay();
    }

    /**
     * 在日历上做一个标记
     *
     * @param date 日期
     * @param id   bitmap res id
     */
    public void addMark(Date date, int id) {
        addMark(DateCommonUtils.getFormat().format(date), id);
    }

    /**
     * 在日历上做一个标记
     *
     * @param date 日期
     * @param id   bitmap res id
     */
    void addMark(String date, int id) {
        // marksMap.put(date, id);
        setCalendarDate();
    }

    /**
     * 在日历上做一组标记
     *
     * @param date 日期
     * @param id   bitmap res id
     */
    public void addMarks(Date[] date, int id) {
        for (int i = 0; i < date.length; i++) {
            // marksMap.put(DateCommonUtils.getFormat().format(date[i]), id);
        }
        setCalendarDate();
    }

    /**
     * 在日历上做一组标记
     *
     * @param date 日期
     * @param id   bitmap res id
     */
    public void addMarks(List<String> date, int id) {
        for (int i = 0; i < date.size(); i++) {
            //marksMap.put(date.get(i), id);
        }
        setCalendarDate();
    }

    /**
     * 移除日历上的标记
     */
    public void removeMark(Date date) {
        removeMark(DateCommonUtils.getFormat().format(date));
    }

    /**
     * 移除日历上的标记
     */
    public void removeMark(String date) {
        //marksMap.remove(date);
        setCalendarDate();
    }

    /**
     * 移除日历上的所有标记
     */
    public void removeAllMarks() {
        //marksMap.clear();
        setCalendarDate();
    }

    /**
     * 设置日历具体某个日期的背景色
     *
     * @param date
     * @param color
     */
    public void setCalendarDayBgColor(Date date, int color) {
        setCalendarDayBgColor(DateCommonUtils.getFormat().format(date), color);
    }

    /**
     * 设置日历具体某个日期的背景色
     *
     * @param date
     * @param color
     */
    public void setCalendarDayBgColor(String date, int color) {
        dayBgColorMap.put(date, color);
        calendarDate = DateCommonUtils.getDateFromString(date).getDate();
        setCalendarDate();
    }

    /**
     * 设置日历一组日期的背景色
     *
     * @param date
     * @param color
     */
    public void setCalendarDaysBgColor(List<String> date, int color) {
        for (int i = 0; i < date.size(); i++) {
            dayBgColorMap.put(date.get(i), color);
        }
        setCalendarDate();
    }

    /**
     * 设置日历一组日期的背景色
     *
     * @param date
     * @param color
     */
    public void setCalendarDayBgColor(String[] date, int color) {
        for (int i = 0; i < date.length; i++) {
            dayBgColorMap.put(date[i], color);
        }
        setCalendarDate();
    }

    /**
     * 移除日历具体某个日期的背景色
     *
     * @param date
     */
    public void removeCalendarDayBgColor(Date date) {
        removeCalendarDayBgColor(DateCommonUtils.getFormat().format(date));
    }

    /**
     * 移除日历具体某个日期的背景色
     *
     * @param date
     */
    public void removeCalendarDayBgColor(String date) {
        dayBgColorMap.remove(date);
        setCalendarDate();
    }

    /**
     * 移除日历具体某个日期的背景色
     */
    public void removeAllBgColor() {
        dayBgColorMap.clear();
        setCalendarDate();
    }

    /**
     * 根据行列号获得包装每一个日子的LinearLayout
     *
     * @param row
     * @param col
     * @return
     */
    public String getDate(int row, int col) {
        return dates[row][col];
    }

    /**
     * 某天是否被标记了
     *
     * @return
     */
//    public boolean hasMarked(String date) {
//        return marksMap.get(date) == null ? false : true;
//    }

    /**
     * 清除所有标记以及背景
     */
    public void clearAll() {
        //marksMap.clear();
        dayBgColorMap.clear();
    }

    /***********************************************
     * private methods
     **********************************************/
    // 设置标记
    private void setMarker(RelativeLayout group, int i, int j) {
//        int childCount = group.getChildCount();
//        if (marksMap.containsKey(dates[i][j])) {
//            if (childCount < 2) {
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) getResources().getDimension(R.dimen.eight_dip), (int) getResources().getDimension(R.dimen.eight_dip));
//                TextView view = new TextView(getContext());
//                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
//                params.addRule(RelativeLayout.CENTER_HORIZONTAL, -1);
//                view.setLayoutParams(params);
//                view.setBackgroundResource(R.drawable.circle_record_state);
//                group.addView(view);
//            }
//        } else {
//            if (childCount > 1) {
//                group.removeView(group.getChildAt(1));
//            }
//        }

    }

    /**
     * 计算某年某月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    private int getDateNum(int year, int month) {
        java.util.Calendar time = java.util.Calendar.getInstance();
        time.clear();
        time.set(java.util.Calendar.YEAR, year + 1900);
        time.set(java.util.Calendar.MONTH, month);
        return time.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据行列号获得包装每一个日子的LinearLayout
     *
     * @param row
     * @param col
     * @return
     */
    private RelativeLayout getDateView(int row, int col) {
        return (RelativeLayout) ((LinearLayout) ((LinearLayout) currentCalendar
                .getChildAt(0)).getChildAt(row)).getChildAt(col);
    }


    /***********************************************
     * Override methods
     **********************************************/
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (gd != null) {
            if (gd.onTouchEvent(ev))
                return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.gd.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // 向左/上滑动
        if (e1.getX() - e2.getX() > 20) {
            nextMonth();
        }
        // 向右/下滑动
        else if (e1.getX() - e2.getX() < -20) {
            lastMonth();
        }

        return false;
    }

    /***********************************************
     * get/set methods
     **********************************************/


    public void setOnCalendarClickListener(
            OnCalendarClickListener onCalendarClickListener) {
        this.onCalendarClickListener = onCalendarClickListener;
    }

    public void setOnCalendarDateChangedListener(
            OnCalendarDateChangedListener onCalendarDateChangedListener) {
        this.onCalendarDateChangedListener = onCalendarDateChangedListener;
    }


    public Map<String, Integer> getDayBgColorMap() {
        return dayBgColorMap;
    }

    public void setDayBgColorMap(Map<String, Integer> dayBgColorMap) {
        this.dayBgColorMap = dayBgColorMap;
    }

}
