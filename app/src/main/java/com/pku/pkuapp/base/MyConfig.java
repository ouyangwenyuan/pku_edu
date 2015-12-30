package com.pku.pkuapp.base;

import com.pku.pkuapp.BuildConfig;

/**
 * Created by admin on 15/12/27.
 */
public class MyConfig {
    public static boolean DEBUG_MODE = BuildConfig.DEBUG;
    public static final String SERVER_URL = "http://isop.pku.edu.cn/svcpub/svc/pub/";


    //class
    public static final String[] classes = {"第一教学楼", "第二教学楼", "第三教学楼", "第四教学楼", "理科教学楼",
            "文史楼", "电教楼", "哲学楼", "地学楼", "国关楼", "政管楼", "化学楼", "电子楼", "电教听力楼"};
    //course
    public static final String weeks[] = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    public static final String times[] = {"08:00", "09:00", "10:10", "11:10", "13:00", "14:00", "15:10", "16:10", "17:10", "18:40", "19:40", "20:40",};
    public static final int courses[][] = {{0, 1, 0, 0, 0, 1, 0}, {0, 1, 1, 0, 1, 0, 1}, {1, 1, 0, 1, 0, 1, 0}, {1, 0, 0, 1, 0, 1, 1}, {0, 0, 1, 1, 0, 1, 0}, {0, 1, 0, 1, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 1}, {0, 1, 1, 0, 1, 1, 0}, {1, 1, 0, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1}, {0, 0, 1, 1, 0, 1, 0}};
    public static final String union_tabs[] = {"研会活动","研会介绍"};
    public static final String internShip_tabs[] = {"实习","招聘","宣讲","职场"};
    public static final String union_depart_btns[] = {"研会简介","研会框架"};
    public static final String union_depart_tabs[] = {"执委会","常代会"};
    public static final String union_depart_tab1[] = {"办公室","宣讲"};
    public static final String union_depart_tab2[] = {};

}

