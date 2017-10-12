package com.xyauto.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理类
 *
 * @author 赵秀非 E-mail:zhaoxiufei@gmail.com
 * @version 创建时间：2017/3/23 12:05
 */
public class DateUtils {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String YMDHMSS = "yyyyMMddHHmmssSSS";
    public static final String YMD = "yyyy-MM-dd";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String D = "dd";

    /**
     * 默认格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param str str
     * @return date
     */
    public static Date str2Date(String str) {
        return str2Date(str, DEFAULT_PATTERN);
    }

    public static Date str2Date(String str, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(str).toDate();
    }

    /**
     * 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date date
     * @return str
     */
    public static String date2Str(Date date) {
        return date2Str(date, DEFAULT_PATTERN);
    }

    public static String date2Str(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }

    /**
     * 默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @return str
     */
    public static String now() {
        return now(DEFAULT_PATTERN);
    }

    public static String now(String pattern) {
        return DateTime.now().toString(pattern);
    }
    public static boolean isWeekend(Date date){
       /* DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String bDate = "2011-12-31";
        Date bdate = format1.parse(bDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
        {
            out.print("ok");
        }
        else out.print("no");*/
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            return true;
        }
        return false;
    }
    public static boolean isWeekend(String str, String pattern){
        Date date = DateTimeFormat.forPattern(pattern).parseDateTime(str).toDate();
        return isWeekend(date);
    }
}
