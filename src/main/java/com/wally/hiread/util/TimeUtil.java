package com.wally.hiread.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static SimpleDateFormat dtf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 一天的00:00:00
    public static Date getDayStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return cal.getTime();

    }
    //一天的23:59:59End
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return cal.getTime();
    }
    // 本周一00:00:00
    public static Date getWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date result=null;
        try {
            result=dtf.parse(dtf.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 本周日23:59:59
    public static Date getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getWeekStart());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        cal.add(Calendar.SECOND,-1);
        Date result=null;
        try {
            result=dtf.parse(dtf.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    //是否在本周
    public static boolean inWeek(Date date){
        Date start = getWeekStart();
        Date end = getWeekEnd();
        if(date.getTime()>=start.getTime()&&date.getTime()<=end.getTime()){
            return true;
        }
        return false;
    }
    //几天前的日期
    public static Date getDaysBefore(Date current,int daysBefore){
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(current);
        calendarNow.set(Calendar.DATE, calendarNow.get(Calendar.DATE) - daysBefore);
        return calendarNow.getTime();
    }
	public static int toSec(String min,String sec){

        try {
            int minI = Integer.parseInt(min);
            int secI = Integer.parseInt(sec);
            return minI*60+secI;
        } catch (NumberFormatException e) {
            return -1;
        }

    }


    public static void main(String[] args) {
        int i = TimeUtil.toSec("09", "01");
        System.out.println(i);

    }
}
